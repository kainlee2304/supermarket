package Model;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Controller.Update_TP;
import DB.Database;
import View.Menu;

public class Thucpham extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame manaFrame;
	private JPanel dataPanel, fucntionPanel;
	private JButton them,xoa,sua,timkiem,quaylui;
	private JTextField tftimkiem;
	private DefaultTableModel DT;
	private JScrollPane scroll;
	private JTable table;
	public Vector<Vector<String>> vData = new Vector<Vector<String>>();
	private Vector<String> vTitle = new Vector<>();
	private int select = -1;
	
	public Thucpham(String title) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		manaFrame = new JFrame("Quản lý thực phẩm");
		them = new JButton("Thêm");
		them.addActionListener(this);
		xoa = new JButton("Xóa");
		xoa.addActionListener(this);
		sua = new JButton("Sửa");
		sua.addActionListener(this);
		tftimkiem = new JTextField("Tìm kiếm");
		
		quaylui = new JButton("Quay lại");
		quaylui.addActionListener(this);
		
		setDataTable();
		DT = new DefaultTableModel(vData, vTitle);
		table = new JTable(DT);
		scroll = new JScrollPane(table);
		
		dataPanel = new JPanel();
		dataPanel.add(scroll);
		
		fucntionPanel = new JPanel();
		fucntionPanel.add(them);
		fucntionPanel.add(xoa);
		fucntionPanel.add(sua);
		fucntionPanel.add(quaylui);
		fucntionPanel.add(tftimkiem);
		
		manaFrame.add(dataPanel, "North");
		manaFrame.add(fucntionPanel, "South");
		manaFrame.setVisible(true);
		manaFrame.setSize(520,520);
		manaFrame.setLocationRelativeTo(null);
		manaFrame.setResizable(false);	
		 TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(DT); //tm is my table model
		 int[] row = {0,1,2,3,4};
	        tftimkiem.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) {
					 String text = tftimkiem.getText();
					 filter(text);
				}
				private void filter(String text) {
					 RowFilter<TableModel, Object> rf;
					    try {
					        rf = RowFilter.regexFilter(text, row);
					    } catch (java.util.regex.PatternSyntaxException ee) {
					        return;
					    }
					    sorter.setRowFilter(rf);
						table.setRowSorter(sorter);
					
				}
				@Override
				public void removeUpdate(DocumentEvent e) {
					String text = tftimkiem.getText();
					 filter(text);
				}
				@Override
				public void changedUpdate(DocumentEvent e) {
					String text = tftimkiem.getText();
					 filter(text);
				}
	          });
	}
		
	
		public void setDataTable(){
	    	try {
	    		Connection conn = Database.getConnection();
	            
	            Statement statement = conn.createStatement();
	            ResultSet rs = statement.executeQuery("Select * from thucpham");
	            ResultSetMetaData rsmd = rs.getMetaData();
	            
	            while(rs.next()) {
	                Vector<String> temp = new Vector<>();
	                        
	                for(int i = 1; i <= rsmd.getColumnCount(); i++) {
	                    temp.add(rs.getString(i));
	                }
	                
	                vData.add(temp);
	            }
	            
	            vTitle.add(rsmd.getColumnName(1));
	            vTitle.add(rsmd.getColumnName(2));
	            vTitle.add(rsmd.getColumnName(3));
	            vTitle.add(rsmd.getColumnName(4));
	            vTitle.add(rsmd.getColumnName(5));
	            conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	    }
		public void setDoDaiCot() {
			table.getColumnModel().getColumn(0).setPreferredWidth(150);
			table.getColumnModel().getColumn(1).setPreferredWidth(150);
			table.getColumnModel().getColumn(2).setPreferredWidth(30);
			table.getColumnModel().getColumn(3).setPreferredWidth(100);
			table.getColumnModel().getColumn(4).setPreferredWidth(80);
		}
		public void xoa() {
			if(vData.size() == 0) JOptionPane.showMessageDialog(manaFrame, "Bang khong co du lieu");
			else if(select == -1) JOptionPane.showMessageDialog(manaFrame, "Bạn cần chọn một hàng");
			else {
				try {
		    		Connection conn = Database.getConnection();
		            
		            Statement statement = conn.createStatement();
		            String query = "Delete From thucpham where ma_sp = " + vData.elementAt(select).elementAt(0);
		            
		            PreparedStatement PS = conn.prepareStatement(query);
		            PS.executeUpdate();
		            conn.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				vData.remove(select);
				DT.fireTableDataChanged();
				select = -1;
			}
		
		}
		public void them(String ma_sp, String ten_tp, String so_luong, String gia, String noi_sx) {
			try {
				Connection conn = Database.getConnection();
            
				Statement statement = conn.createStatement();
				String query = "Insert into thucpham values(" + ma_sp + ", N'" + ten_tp + "', " + so_luong + ", N'" + gia + "', N'" + noi_sx + "')";
				JOptionPane.showConfirmDialog(null,"xác nhận!");
				PreparedStatement PS = conn.prepareStatement(query);
				PS.executeUpdate();
				conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			Vector<String> s = new Vector<>();
			s.add(ma_sp);
			s.add(ten_tp);
			s.add(so_luong);
			s.add(gia);
			s.add(noi_sx);
			vData.add(s);
			DT.fireTableDataChanged();
	}
		public void sua(String ma_sp, String ten_sp, String so_luong, String gia, String noi_sx) {
				try {
				Connection conn = Database.getConnection();
	        
				Statement statement = conn.createStatement();
				String query = "Update thucpham set ten_sp= '" + ten_sp + "', so_luong = '" + so_luong + "', gia = '" + gia + "', noi_sx= N'"+ noi_sx + "' where ma_sp = " + ma_sp;
	        
				PreparedStatement PS = conn.prepareStatement(query);
				PS.executeUpdate();
				conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			Vector<String> s = new Vector<>();
			s.add(ma_sp);
			s.add(ten_sp);
			s.add(so_luong);
			s.add(gia);
			s.add(noi_sx);
			vData.remove(select);
			vData.add(select, s);
			DT.fireTableDataChanged();
		}
		public void timkiem(String ma_tp, String ten_tp, String so_luong, String gia, String noi_sx) throws Exception  {
			try {
				int select = table.getSelectedRow();
				String input = JOptionPane.showInputDialog(null,"Nhập nội dung muốn tìm(để trống nếu muốn tất cả):");
				String sqlseach = "select * from thucpham where Tên sản phẩm like N'%"+input+"%' " ;
				sqlseach += " or Số lượng like N'%"+input+"%' " ;
				sqlseach += " or Giá like N'%"+input+"%' " ; 
				sqlseach += " or Nơi sản xuất like N'%"+input+"%' " ;   
				
				Connection conn = Database.getConnection();
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(sqlseach);
				ResultSetMetaData rsmd = resultSet.getMetaData();
				int colNo = rsmd.getColumnCount();
				Vector vtColumn = new Vector(colNo);
				String[] tableTitle =  {"Tên sản phẩm","Số lượng","Giá","Nơi sản xuất"};
				for (String s : tableTitle) {
					vtColumn.add(s);
				}
				Vector vtData = new Vector();
				Vector vtRows = new Vector();
				while(resultSet.next()){
					vtRows = new Vector(colNo);
		            for(int i=0;i<colNo;i++){
		                vtRows.add(resultSet.getString(i+1));
		            }
		            vtData.add(vtRows);           
		        } 
				table = new JTable(vtData,vtColumn);
				table.setBackground(new Color(255, 255, 255));
				table.setFont(new Font("Arial", Font.PLAIN, 14));
				scroll.setViewportView(table);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == xoa) {
			select = table.getSelectedRow();
			System.out.println(select);
			xoa();
		}
		if(e.getSource() == them) {
			new Update_TP(this, "Update Form", "", "", "", "","");
		}
		if(e.getSource() == sua)  {
			select = table.getSelectedRow();
			if(select != -1) {
				Vector<String> v = vData.elementAt(select);
			new Update_TP(this, "Edit Form", v.elementAt(0), v.elementAt(1), v.elementAt(2), v.elementAt(3),v.elementAt(4));
			}
		}
		if(e.getSource() == timkiem) {
			
		}
		if(e.getSource() == quaylui) {
			new Menu();
			manaFrame.dispose();
		}
	}
}



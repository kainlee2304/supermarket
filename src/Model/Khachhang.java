package Model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.w3c.dom.events.DocumentEvent;

import com.mysql.cj.jdbc.DatabaseMetaData;

import Controller.Timkiem;
import Controller.Update;
import DB.Database;
import View.Menu;



public class Khachhang implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private JFrame manaFrame;
	private JPanel dataPanel, fucntionPanel;
	private JButton them,xoa,sua,quaylui;
	private JTextField tftimkiem;
	private static DefaultTableModel DT;
	private static JScrollPane scroll;
	private static JTable table;
	public static Vector<Vector<String>> vData = new Vector<Vector<String>>();
	private Vector<String> vTitle = new Vector<>();
	private int select = -1;
	
	public Khachhang() {
		try {
    		for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
    		if ("Nimbus".equals(info.getName())) {
    		javax.swing.UIManager.setLookAndFeel(info.getClassName());
    		break;
    		}
    		}
    		} catch (ClassNotFoundException ex) {
    		java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    		} catch (InstantiationException ex) {
    		java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    		} catch (IllegalAccessException ex) {
    		java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
    		java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    		}    
		manaFrame = new JFrame("Quản lý thông tin khách hàng");
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
		 TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(DT); 
		 int[] row = {0,1,2,3};
	        tftimkiem.getDocument().addDocumentListener(new DocumentListener() {
			
				private void filter(String text) {
					 RowFilter<TableModel, Object> rf = null;
					    try {
					        rf = RowFilter.regexFilter(text, row);
					    } catch (java.util.regex.PatternSyntaxException e) {
					      
					    }
					    sorter.setRowFilter(rf);
						table.setRowSorter(sorter);
					
				}
				
				@Override
				public void insertUpdate(javax.swing.event.DocumentEvent e) {
					String text = tftimkiem.getText();
					 filter(text);
					
				}
				@Override
				public void removeUpdate(javax.swing.event.DocumentEvent e) {
					String text = tftimkiem.getText();
					 filter(text);
					
				}
				@Override
				public void changedUpdate(javax.swing.event.DocumentEvent e) {
					String text = tftimkiem.getText();
					 filter(text);
					
				}
	          });
	}
		
		public void setDataTable(){
	    	try {
	    		Connection conn = Database.getConnection();
	            
	            Statement statement = conn.createStatement();
	            ResultSet rs = statement.executeQuery("Select * from khachhang");
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
	           
	            conn.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}
	    }	
		
		public void setDoDaiCot() {
			table.getColumnModel().getColumn(0).setPreferredWidth(30);
			table.getColumnModel().getColumn(1).setPreferredWidth(150);
			table.getColumnModel().getColumn(2).setPreferredWidth(30);
			table.getColumnModel().getColumn(3).setPreferredWidth(100);
		}
		
		public void xoa() {
			 	
			if(vData.size() == 0) JOptionPane.showMessageDialog(manaFrame, "Bang khong co du lieu");
			else if(select == -1) JOptionPane.showMessageDialog(manaFrame, "Bạn cần chọn một hàng");
			else {
				try {
		    		Connection conn = Database.getConnection();
		            Statement statement = conn.createStatement();
		            String query = "Delete From khachhang where ID = " + vData.elementAt(select).elementAt(0);
		            
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
		public void them(String id, String name, String diachi,String sdt) {
			try {
				Connection conn = Database.getConnection();
            
				Statement statement = conn.createStatement();
				String query = "Insert into khachhang values(" + id + ", N'" + name + "',N'" + diachi + "', " + sdt +")";
				JOptionPane.showConfirmDialog(null,"xác nhận!");
				PreparedStatement PS = conn.prepareStatement(query);
				PS.executeUpdate();
				conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			Vector<String> s = new Vector<>();
			s.add(id);
			s.add(name);
			s.add(diachi);
			s.add(sdt);
			vData.add(s);
			DT.fireTableDataChanged();
	}
		public void sua(String id, String name, String diachi, String sdt) {
				try {
				Connection conn = Database.getConnection();
	        
				Statement statement = conn.createStatement();
				String query = "Update khachhang set name= '" + name + "', diachi = N'" + diachi + "', sdt= '" + sdt + "' where id = " + id;
	        
				PreparedStatement PS = conn.prepareStatement(query);
				PS.executeUpdate();
				conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			Vector<String> s = new Vector<>();
			s.add(id);
			s.add(name);
			s.add(diachi);
			s.add(sdt);
			vData.remove(select);
			vData.add(select, s);
			DT.fireTableDataChanged();
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
			new Update(this, "Update Form", "", "", "", "");
		}
		if(e.getSource() == sua)  {
			select = table.getSelectedRow();
			if(select != -1) {
				Vector<String> v = vData.elementAt(select);
				new Update(this, "Edit Form", v.elementAt(0), v.elementAt(1), v.elementAt(2), v.elementAt(3));
			}
		}
		if(e.getSource() == quaylui) {
			new Menu();
			manaFrame.dispose();
		}
	}
	
}
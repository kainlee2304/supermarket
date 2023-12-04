package Model;


import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.LogManager;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import DB.Database;

import View.Menu;

public class Donhang extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel c;
	private JTextField tfKH;
	private JTextField tfSP;
	private JTextField tfDH;
	private JTextField tfSL;
	private JTextField tfGT;
	private JTextField tftongtien;
	DefaultTableModel model;
	JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	JScrollPane s;
	Statement stmt = null;
	ResultSet rs = null;
	Connection conn ;
	JLabel label;
    SimpleDateFormat sdf = new SimpleDateFormat( "dd MMM yyyy hh:mm" );
	public Donhang() {
		setTitle("Quản lý đơn hàng");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Administrator\\Downloads\\Bai_cuoi_ky\\src\\icon\\donhang.png"));
		try {
			conn = Database.getConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel lblTD = new JLabel("QUẢN LÝ ĐƠN HÀNG");
		JLabel lblKH = new JLabel("Mã khách hàng : ");
		JLabel lblSP = new JLabel("Mã sản phẩm : ");
		JLabel lblDH = new JLabel("Mã đơn hàng : ");
		JLabel lblSL = new JLabel("Số lượng : ");
		JLabel lblGT = new JLabel("Giá Tiền : ");
		JLabel lblTTien = new JLabel("Tổng Tiền : ");
		JButton btnThem = new JButton("Thêm");
		JButton btnXoa = new JButton("Xoá");
		JButton btnSua = new JButton("Sửa");
		JButton btnPrint = new JButton("Print");
		JButton btnLui = new JButton("");
		JTextField tfTK = new JTextField("Tìm Kiếm ");
		JButton btnExport = new JButton("Xuất file Excel ");
		 s = new JScrollPane();
		
		setBounds(100, 100, 693, 620);
		c = new JPanel();
		c.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(c);
		c.setLayout(null);
		s.setBounds(0, 322, 679, 190);
		c.add(s);
		
		lblTD.setBounds(227, 10, 266, 58);
		c.add(lblTD);
		
		lblKH.setBounds(0, 61, 122, 33);
		c.add(lblKH);
		
		tfKH = new JTextField();
		tfKH.setBounds(121, 65, 372, 26);
		c.add(tfKH);
		
		tfKH.setColumns(10);
		lblSP.setBounds(0, 100, 122, 33);
		c.add(lblSP);
		
		tfSP = new JTextField();
		tfSP.setBounds(121, 104, 372, 26);
		tfSP.setColumns(10);
		c.add(tfSP);
		
		tfDH = new JTextField();
		tfDH.setBounds(121, 140, 372, 26);
		tfDH.setColumns(10);
		c.add(tfDH);
		
		tfSL = new JTextField();
		tfSL.setBounds(121, 176, 372, 26);
		tfSL.setColumns(10);
		c.add(tfSL);
		
		tfGT = new JTextField();
		tfGT.setBounds(121, 218, 372, 26);
		tfGT.setColumns(10);
		c.add(tfGT);
		
		tftongtien= new JTextField();
		tftongtien.setBounds(121, 258, 372, 26);
		tftongtien.setColumns(10);
		c.add(tftongtien);
		
		lblDH.setBounds(0, 136, 122, 33);
		c.add(lblDH);
		
		lblSL.setBounds(0, 176, 122, 33);
		c.add(lblSL);
		
	
		lblGT.setBounds(0, 214, 122, 33);
		c.add(lblGT);
		
		lblTTien.setBounds(0, 255, 122, 33);
		c.add(lblTTien);
		
		Object[] row = new Object[6]; 
		
		//bảng
		loadKhachHang(); 
		scrollPane = new JScrollPane();
		s.setColumnHeaderView(scrollPane);
		scrollPane.setViewportView(table);
		
				
		btnThem.setBounds(30, 530, 106, 38);
		Icon icon_them = new ImageIcon("C:\\Users\\Administrator\\Downloads\\Bai_cuoi_ky\\src\\icon\\icon_them.png");
       	btnThem.setFont(new Font("Tahoma", Font.PLAIN, 15));
       	btnThem.setForeground(Color.black);
       	btnThem.setBackground(Color.white);
       	btnThem.setIcon(icon_them);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						if(tfKH.getText().equals("")||tfSP.getText().equals("")||tfDH.getText().equals("")||tfSL.getText().equals("")||tfGT.getText().equals("")||tftongtien.getText().equals("")) {
							JOptionPane.showMessageDialog(null,"vui lòng nhập dữ liệu vào!!!!!");
						}else {
							String I-idkh = tfKH.getText();
							String ma_sp = tfSP.getText();
							String ma_don_hang = tfDH.getText();
							double so_luong =Double.parseDouble(tfSL.getText());
							double gt = Double.parseDouble(tfGT.getText());
							double tt =0;
							tt = so_luong*gt;
							String sqlInsert = "Insert into donhang values(N'" + id + "','" + ma_sp
									+ "',N'" + ma_don_hang + "',N'" + so_luong + "'," + gt +","+tt+")";
							tfKH.setText("");
							tfDH.setText("");
							tfSL.setText("");
							tftongtien.setText("");
							tfGT.setText("");
							tfSP.setText("");
							JOptionPane.showConfirmDialog(null,"xác nhận!");
							stmt = conn.createStatement();
							stmt.execute(sqlInsert);
							loadKhachHang();
							JOptionPane.showMessageDialog(null, "Lưu thông tin thành công");	
						}
					} catch (Exception g) {
						JOptionPane.showMessageDialog(null, g.getMessage());
						g.printStackTrace();
					}
				btnThem.setText(String.valueOf("Thêm"));
			}

		});
		c.add(btnThem);
		//xoá
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = table.getSelectedRow();
				model = (DefaultTableModel) table.getModel();
				String id = model.getValueAt(i, 0).toString();
				String sqlxoa = "DELETE FROM donhang where id = N'" + id + "'"; 
				if (JOptionPane.showConfirmDialog(null, "bạn có chắc chắn xoá khách hàng đó không là "
						+ model.getValueAt(i, 0) + " không ? ") == JOptionPane.YES_NO_OPTION) {
					// yes option
					try {
						stmt = conn.createStatement();
						boolean countdelete = stmt.execute(sqlxoa);
						loadKhachHang();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					// no option 
				}		
			}
		});
		btnXoa.setBounds(215, 530, 91, 38);
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Icon icon_xoa = new ImageIcon("C:\\Users\\Administrator\\Downloads\\Bai_cuoi_ky\\src\\icon\\icon_xoa.png");
       	btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
       	btnXoa.setForeground(Color.black);
       	btnXoa.setBackground(Color.white);
       	btnXoa.setIcon(icon_xoa);
		c.add(btnXoa);
		// sửa
		btnSua.setBounds(382, 530, 90, 38);
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Icon icon_sua = new ImageIcon("C:\\Users\\Administrator\\Downloads\\Bai_cuoi_ky\\src\\icon\\icon_sua.png");
       	btnSua.setFont(new Font("Tahoma", Font.PLAIN, 15));
       	btnSua.setForeground(Color.black);
       	btnSua.setBackground(Color.white);
       	btnSua.setIcon(icon_sua);
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=table.getSelectedRow();
				String id = tfKH.getText();
				String ma_sp = tfSP.getText();
				String ma_don_hang = tfDH.getText();
				double so_luong =Double.parseDouble(tfSL.getText());
				double gt = Double.parseDouble(tfGT.getText());
				double tt =0;
				tt = so_luong*gt;
				if(id.equals(""))
				{	
					JOptionPane.showMessageDialog(null, "Phải chọn một vật liệu trước khi sửa!");
					return;
				}
				String sqlUpdate = "update donhang set ma_sp= "+ma_sp +", ma_don_hang = N'"+ma_don_hang+ "', So_luong = N'"+so_luong+ "',gia= "
				+gt+", tong_tien= "+tt+" where id=N'"+id+"'";
				try {
					stmt = conn.createStatement();
					boolean countdelete = stmt.execute(sqlUpdate);
					loadKhachHang();
				} catch (SQLException g) {
					// TODO Auto-generated catch block
					g.printStackTrace();
				}

			}
		});
		c.add(btnSua);
		//quay lùi 
		btnLui.setBounds(0, 0, 55, 36);
		btnLui.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Icon icon_quaylai = new ImageIcon("C:\\Users\\Administrator\\Downloads\\Bai_cuoi_ky\\src\\icon\\icon_quaylai.png");
       	btnLui.setFont(new Font("Tahoma", Font.PLAIN, 15));
       	btnLui.setForeground(Color.black);
       	btnLui.setBackground(Color.white);
       	btnLui.setIcon(icon_quaylai);
		btnLui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Menu();
			}
		});
		c.add(btnLui);
		// Print
		btnPrint.setBounds(580, 0, 97, 36);
		btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Icon icon_print = new ImageIcon("C:\\Users\\Administrator\\Downloads\\Bai_cuoi_ky\\src\\icon\\icon_print.png");
       	btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 15));
       	btnPrint.setForeground(Color.black);
       	btnPrint.setBackground(Color.white);
       	btnPrint.setIcon(icon_print);
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				print();
			}
		});
		c.add(btnPrint);
		// tìm kiếm 
		tfTK.setBounds(530, 530, 106, 38);
		c.add(tfTK);
		model = (DefaultTableModel) table.getModel();
		 TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model); 
		 int[] row1 = {0,1,2,3,4,5};
	        tfTK.getDocument().addDocumentListener(new DocumentListener() {
			
				private void filter(String text) {
					 RowFilter<TableModel, Object> rf = null;
					    try {
					        rf = RowFilter.regexFilter(text, row1);
					    } catch (java.util.regex.PatternSyntaxException e) {
					      
					    }
					    sorter.setRowFilter(rf);
						table.setRowSorter(sorter);
					
				}
				
				@Override
				public void insertUpdate(javax.swing.event.DocumentEvent e) {
					String text = tfTK.getText();
					 filter(text);
					
				}
				@Override
				public void removeUpdate(javax.swing.event.DocumentEvent e) {
					String text = tfTK.getText();
					 filter(text);
					
				}
				@Override
				public void changedUpdate(javax.swing.event.DocumentEvent e) {
					String text = tfTK.getText();
					 filter(text);
					
				}
	          });

		
		
		//xuất file Excel 
		btnExport.setBounds(415, 0, 160, 36);
		btnExport.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Icon icon_excel = new ImageIcon("C:\\Users\\Administrator\\Downloads\\Bai_cuoi_ky\\src\\icon	\\icon_excel.png");
       	btnExport.setFont(new Font("Tahoma", Font.PLAIN, 15));
       	btnExport.setForeground(Color.black);
       	btnExport.setBackground(Color.white);
       	btnExport.setIcon(icon_excel);
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Đã xuất file excel!!!");
				export();
			}
		});
		c.add(btnExport);
		label = new JLabel( sdf.format( new GregorianCalendar().getTime()));
	    label.setHorizontalAlignment( JLabel.RIGHT );
	    label.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    label.setBounds(350, 30, 300, 40);
	    Timer timer = new Timer(100, this );
	    timer.start();
        c.add(label);
		setVisible(true);
	}
	private void loadKhachHang() {
		try {
		String SQL = "SELECT * FROM donhang";
		 stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		ResultSetMetaData rsmd = rs.getMetaData();
		int colNo = rsmd.getColumnCount();
		Vector<String> vtColumn = new Vector<String>(colNo);
		String[] tableTitle =  {"Mã Khách Hàng","Mã Sản Phẩm","Mã Đơn Hàng","Số Lượng","Giá tiền","Tổng Tiền"};
		for (String s : tableTitle) {
			vtColumn.add(s);
		}
		Vector<Vector<String>> vtData = new Vector<Vector<String>>();
		Vector<String> vtRows = new Vector<String>();
		while(rs.next()){
			vtRows = new Vector<String>(colNo);
            for(int i=0;i<colNo;i++){
                vtRows.add(rs.getString(i+1));
            }
            vtData.add(vtRows);           
        } 
		table = new JTable(vtData,vtColumn);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (!e.getValueIsAdjusting()) {
					model = (DefaultTableModel) table.getModel(); 
					int i= table.getSelectedRow();
					tfKH.setText(model.getValueAt(i, 0).toString());
					tfSP.setText(model.getValueAt(i, 1).toString());
					tfDH.setText(model.getValueAt(i, 2).toString());
					tfSL.setText(model.getValueAt(i, 3).toString()); 
					tfGT.setText(model.getValueAt(i, 4).toString());
					tftongtien.setText(model.getValueAt(i, 5).toString());
				}
			}
		});
		
		scrollPane.setViewportView(table);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
	}
	 public void export() {
	        String excelFilePath = "Đơn hàng.xlsx";
	 try {
		 Connection connection = Database.getConnection();
		 String sql = "SELECT * FROM donhang";
		 Statement statement = connection.createStatement();
		 ResultSet result = statement.executeQuery(sql);
		 
		 XSSFWorkbook workbook = new XSSFWorkbook();
		 XSSFSheet sheet = workbook.createSheet("Đơn hàng");
		 
		 writeHeaderLine(sheet);
		 writeDataLines(result, workbook, sheet);
		 
		 FileOutputStream outputStream = new FileOutputStream(excelFilePath);
         workbook.write(outputStream);
         workbook.close();
         statement.close();
	} catch (Exception e) {
		// TODO: handle exception
	}
	    }
	    private void writeHeaderLine(XSSFSheet sheet) {
	 
	        Row headerRow = sheet.createRow(0);
	 
	        Cell headerCell = headerRow.createCell(0);
	        headerCell.setCellValue("Mã Khách Hàng");
	 
	        headerCell = headerRow.createCell(1);
	        headerCell.setCellValue("Mã Sản Phẩm");
	 
	        headerCell = headerRow.createCell(2);
	        headerCell.setCellValue("Mã Đơn Hàng");
	 
	        headerCell = headerRow.createCell(3);
	        headerCell.setCellValue("Số Lượng");
	 
	        headerCell = headerRow.createCell(4);
	        headerCell.setCellValue("Giá tiền");
	        
	        headerCell = headerRow.createCell(5);
	        headerCell.setCellValue("Tổng Tiền");
	    }
	 
	    private void writeDataLines(ResultSet result, XSSFWorkbook workbook,
	            XSSFSheet sheet) throws SQLException {
	        int rowCount = 1;
	 
	        while (result.next()) {
	            String id = result.getString("id");
	            String masp = result.getString("ma_sp");
	            String madh = result.getString("ma_don_hang");
	            int soluong = result.getInt("so_luong");
	            float gia = result.getFloat("gia");
	            float tongtien = soluong*gia;
	 
	            Row row = sheet.createRow(rowCount++);
	 
	            int columnCount = 0;
	            Cell cell = row.createCell(columnCount++);
	            cell.setCellValue(id);
	 
	            cell = row.createCell(columnCount++);
	            cell.setCellValue(masp);
	 
	            cell = row.createCell(columnCount++);
	            cell.setCellValue(madh);
	 
	            cell = row.createCell(columnCount++);
	            cell.setCellValue(soluong);
	 
	            cell = row.createCell(columnCount++);
	            cell.setCellValue(gia);
	            
	            cell = row.createCell(columnCount++);
	            cell.setCellValue(tongtien);
	 
	        }
	    }
	public void print() {
		try {
		    table.print(JTable.PrintMode.FIT_WIDTH, null, null); 
		} catch (java.awt.print.PrinterException e) {
		     System.err.format("Cannot print %s%n", e.getMessage()); 
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

package View;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

//import org.apache.commons.math3.analysis.function.Add;

import Model.Donhang;
import Model.Khachhang;
import Model.Thucpham;

 
public class Menu extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//thuoc tinh
	private JPanel p;
	private JLabel Background;

	
		// constructor
	public Menu() {
		setTitle("HỆ THỐNG QUẢN LÝ SIÊU THỊ");
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
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Administrator\\Downloads\\Bai_cuoi_ky\\src\\icon\\shopping.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Khởi tạo thuộc tính
		
		 
		JButton khachhang = new JButton("Thông tin khách hàng");
		JButton thucpham = new JButton("Quản lý thực phẩm");
		JButton donhang = new JButton("Quản lý đơn hàng");
		JButton change = new JButton("Change color");
		p = new JPanel();
		p.add(donhang);
		p.add(khachhang);
		p.add(thucpham);
		p.add(change);
		
		
		setBounds(100, 100, 797, 530);
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(p);
		p.setLayout(null);
		
		
				
		// Khách hàng
		 khachhang.setFont(new Font("Tahoma", Font.PLAIN, 13));
	     Icon icon_khachhang = new ImageIcon("C:\\Users\\Administrator\\Downloads\\Bai_cuoi_ky\\src\\icon\\icon_khachhang.png");
	     khachhang.setIcon(icon_khachhang);
	     khachhang.setForeground(Color.black);
	     khachhang.setBackground(Color.white);
		khachhang.setBounds(5, 360, 222, 55);
		khachhang.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
				new	Khachhang();
			}
		});
			
		// thực phẩm
	thucpham.setFont(new Font("Tahoma", Font.PLAIN, 13));
	Icon icon_thucpham = new ImageIcon("C:\\Users\\Administrator\\Downloads\\Bai_cuoi_ky\\src\\icon\\icon_thucpham.png");
	thucpham.setIcon(icon_thucpham);
	thucpham.setForeground(Color.black);
	thucpham.setBackground(Color.white);
	thucpham.setBounds(228, 360, 185, 55);
	thucpham.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
			new	Thucpham("");
		}	
	});
	donhang.setBounds(410, 360, 185, 55);
	donhang.setFont(new Font("Tahoma", Font.PLAIN, 13));
	Icon icon_donhang = new ImageIcon("C:\\Users\\Administrator\\Downloads\\Bai_cuoi_ky\\src\\icon\\icon_donhang.png");
	donhang.setIcon(icon_donhang);
	donhang.setForeground(Color.black);
	donhang.setBackground(Color.white);
	donhang.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
			new Donhang();
		}	
	});
		change.setBounds(595, 360, 185, 55);
		change.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Icon icon_change = new ImageIcon("C:\\Users\\Administrator\\Downloads\\Bai_cuoi_ky\\src\\icon\\change.png");
		change.setIcon(icon_change);
		change.setForeground(Color.black);
		change.setBackground(Color.white);
		change.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					Color initialBackground = khachhang.getBackground();
				    Color background = JColorChooser.showDialog(null,
				        "Change Color Jbutton", initialBackground);
				    if (khachhang != null) {
				    	JOptionPane.showMessageDialog(null, "Đổi thành công!!!");
				    	khachhang.setBackground(background);
				    }
			}
		});

	setVisible(true);
	ImageIcon BackGroundImage = new ImageIcon("C:\\Users\\Administrator\\Downloads\\Bai_cuoi_ky\\src\\icon\\background.png");
	Background = new JLabel("",BackGroundImage,JLabel.CENTER);
	Background.setBounds(0,0,797, 530);
	add(Background);
	
	
}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

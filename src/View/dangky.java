package View;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import DB.Database;



/**
 * User Registration using Swing
 * @author javaguides.net
 *
 */
public class dangky extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField name;
    private JTextField ma_nv;
    private JTextField email;
    private JTextField username;
    private JTextField mob;
    private JPasswordField passwordField;
    private JButton btnNewButton, btnlogin;
    private JLabel Background, logo;

    /**
     * Launch the application.
     */
   

    /**
     * Create the frame.
     */

    public dangky() {
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
    	setTitle("Đăng ký tài khoản");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Administrator\\Downloads\\Bai_cuoi_ky\\src\\icon\\register.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("Đăng ký tài khoản");
//        lblNewUserRegister.setForeground(Color.BLACK);
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 42));
        lblNewUserRegister.setBounds(362, 52, 325, 50);
        contentPane.add(lblNewUserRegister);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblName.setBounds(58, 152, 99, 43);
        contentPane.add(lblName);

        JLabel lblNewLabel = new JLabel("Mã nhân viên");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(58, 243, 110, 29);
        contentPane.add(lblNewLabel);

        JLabel lblEmailAddress = new JLabel("Email\r\n address");
        lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEmailAddress.setBounds(58, 324, 124, 36);
        contentPane.add(lblEmailAddress);

        name = new JTextField();
        name.setFont(new Font("Tahoma", Font.PLAIN, 32));
        name.setBounds(214, 151, 228, 50);
        contentPane.add(name);
        name.setColumns(10);

        ma_nv = new JTextField();
        ma_nv.setFont(new Font("Tahoma", Font.PLAIN, 32));
        ma_nv.setBounds(214, 235, 228, 50);
        contentPane.add(ma_nv);
        ma_nv.setColumns(10);

        email = new JTextField();

        email.setFont(new Font("Tahoma", Font.PLAIN, 32));
        email.setBounds(214, 320, 228, 50);
        contentPane.add(email);
        email.setColumns(10);

        username = new JTextField();
        username.setFont(new Font("Tahoma", Font.PLAIN, 32));
        username.setBounds(707, 151, 228, 50);
        contentPane.add(username);
        username.setColumns(10);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblUsername.setBounds(542, 159, 99, 29);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPassword.setBounds(542, 245, 99, 24);
        contentPane.add(lblPassword);

        JLabel lblMobileNumber = new JLabel("Mobile number");
        lblMobileNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblMobileNumber.setBounds(542, 329, 139, 26);
        contentPane.add(lblMobileNumber);

        mob = new JTextField();
        mob.setFont(new Font("Tahoma", Font.PLAIN, 32));
        mob.setBounds(707, 320, 228, 50);
        contentPane.add(mob);
        mob.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(707, 235, 228, 50);
        contentPane.add(passwordField);
        
        btnlogin = new JButton("Login Now");
        btnlogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dangnhap dn = new dangnhap();
				dn.setVisible(true);
				dispose();
			}
		});
        Icon icon_login = new ImageIcon("C:\\Users\\Administrator\\Downloads\\Bai_cuoi_ky\\src\\icon\\icon_login.png");
        btnlogin.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnlogin.setBounds(255, 447, 259, 74);
        btnlogin.setForeground(Color.black);
        btnlogin.setBackground(Color.white);
        btnlogin.setIcon(icon_login);
        contentPane.add(btnlogin);
      
        btnNewButton = new JButton("Register");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Name = name.getText();
                String manv = ma_nv.getText();
                String emailId = email.getText();
                String userName = username.getText();
                String mobileNumber = mob.getText();
                int len = mobileNumber.length();
                String password = passwordField.getText();
                String encryptedpassword = null;  
                try   
                {  
                    /* MessageDigest instance for MD5. */  
                    MessageDigest m = MessageDigest.getInstance("MD5");  
                      
                    /* Add plain-text password bytes to digest using MD5 update() method. */  
                    m.update(password.getBytes());  
                      
                    /* Convert the hash value into bytes */   
                    byte[] bytes = m.digest();  
                      
                    /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
                    StringBuilder s = new StringBuilder();  
                    for(int i=0; i< bytes.length ;i++)  
                    {  
                        s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
                    }  
                      
                    /* Complete hashed password in hexadecimal format */  
                    encryptedpassword = s.toString();  
                }   
                catch (Exception e1)   
                {  
                    e1.printStackTrace();  
                }  
                  
                String msg = "" + Name;
                msg += " \n";
                if (len != 10) {
                    JOptionPane.showMessageDialog(null, "Enter a valid mobile number");
                }
                if(name.getText().equals("")||ma_nv.getText().equals("")||email.getText().equals("")||username.getText().equals("")||mob.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"vui lòng nhập dữ liệu vào!!!!!");
                }
                else
                try {
                	Connection conn = Database.getConnection();

                    String query = "INSERT INTO account values('" + Name + "','" + manv + "','" + userName + "','" +
                        encryptedpassword + "','" + emailId + "','" + mobileNumber + "')";

                    Statement sta = conn.createStatement();
                    int x = sta.executeUpdate(query);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(null, "Điều này đã tồn tại");
                    } else {
                        JOptionPane.showMessageDialog(null,
                            "Chào, " + msg + "Tài khoản của bạn được tạo thành công");
                    }
                    conn.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        Icon icon_register = new ImageIcon("C:\\Users\\Administrator\\Downloads\\Bai_cuoi_ky\\src\\icon\\icon_register.png");
        btnNewButton.setIcon(icon_register);
        btnNewButton.setBounds(575, 447, 259, 74);
        btnNewButton.setForeground(Color.black);
        btnNewButton.setBackground(Color.white);
        contentPane.add(btnNewButton);
        setVisible(true);
        
        ImageIcon BackGroundImage = new ImageIcon("C:\\Users\\Administrator\\Downloads\\Bai_cuoi_ky\\src\\icon\\background_register.png");
    	Background = new JLabel("",BackGroundImage,JLabel.CENTER);
    	Background.setBounds(0, 0, 0, 0);
    	add(Background);
    	
    	ImageIcon BackGroundImage1 = new ImageIcon("C:\\Users\\Administrator\\Downloads\\Bai_cuoi_ky\\src\\icon\\logo.png");
    	logo = new JLabel("",BackGroundImage1,JLabel.CENTER);
    	logo.setBounds(35, 52, 325, 50);
    	add(logo);
    
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
    
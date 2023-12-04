package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import DB.Database;

public class dangnhap extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JButton resetButton = new JButton("Reset");
    private JCheckBox showPassword = new JCheckBox("Show Password");
    JLabel label;
    SimpleDateFormat sdf = new SimpleDateFormat( "dd MMM yyyy hh:mm:ss" );
    private JPanel contentPane;

    /**
     * Launch the application.
     */
   

    /**
     * Create the frame.
     */
    public dangnhap() {
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
    	setTitle("Đăng nhập tài khoản");
    	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Administrator\\Downloads\\Bai_cuoi_ky\\src\\icon\\login.png"));
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(450, 190, 1014, 597);
    	setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Đăng nhập");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(423, 13, 273, 93);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(481, 170, 281, 50);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(481, 286, 281, 50);
        passwordField.setColumns(10);
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblUsername.setBounds(250, 166, 193, 52);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword.setBounds(250, 286, 193, 52);
        contentPane.add(lblPassword);
        
        showPassword.setBounds(480, 340, 150, 30);
        showPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contentPane.add(showPassword);
        showPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == showPassword) {
		            if (showPassword.isSelected()) {
		                passwordField.setEchoChar((char) 0);
		            } else {
		                passwordField.setEchoChar('*');
		            }		 
		        }
				
			}
		});

        resetButton.setBounds(300, 425, 162, 73);
        Icon icon_reset = new ImageIcon("C:\\Users\\Administrator\\Downloads\\Bai_cuoi_ky\\src\\icon\\icon_reset.png");
        resetButton.setIcon(icon_reset);
        resetButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        resetButton.setForeground(Color.black);
        resetButton.setBackground(Color.white);
        contentPane.add(resetButton);
        resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 if (e.getSource() == resetButton) {
			            textField.setText("");
			            passwordField.setText("");
			        }
			}
		});
        
        btnNewButton = new JButton("Login");
        Icon icon_login = new ImageIcon("C:\\Users\\Administrator\\Downloads\\Bai_cuoi_ky\\src\\icon\\icon_login.png");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.setForeground(Color.black);
        btnNewButton.setBackground(Color.white);
        btnNewButton.setIcon(icon_login);
        btnNewButton.setBounds(575, 425, 162, 73);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                String userName = textField.getText();
                String password = passwordField.getText();
                String encryptedpassword = null;  
                try {
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
                PreparedStatement st;
					try {
						Connection connection = Database.getConnection();
						st = connection.prepareStatement("Select T_UserName, T-Password from account where T_Username=? and T_Password=?");
						 st.setString(1, userName);
		                    st.setString(2, encryptedpassword);
		                    ResultSet rs = st.executeQuery();
		                    if (rs.next()) {
		                    	JOptionPane.showMessageDialog(null, "Chào," + userName + " Welcome bạn đến với hệ thống quản lý siêu thị");
		                    	dispose();
		                        new Menu();
		                    } else {
		                        JOptionPane.showMessageDialog(null, "User hoac password sai!");
		                    }
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

                   
          }
        });
        label = new JLabel( sdf.format( new GregorianCalendar().getTime()));
	    label.setHorizontalAlignment( JLabel.RIGHT );
	    label.setForeground(Color.red);
	    label.setFont(new Font("Tahoma", Font.PLAIN, 26));
	    label.setBounds(695, 0, 300, 40);
	    Timer timer = new Timer( 1000, this );
	    timer.start();
        contentPane.add(btnNewButton);
        setVisible(true);
        contentPane.add(label);
        
    }
    @Override
	public void actionPerformed(ActionEvent e) {
		label.setText( sdf.format( new GregorianCalendar().getTime()));
		
	}
	
}

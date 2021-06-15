package Agence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Log {

	private JFrame frame;
	private JTextField user;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Log window = new Log();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Log() {
		initialize();
		connect();
	}
	static Connection cnx;
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 cnx=DriverManager.getConnection("jdbc:mysql://localhost:3306/agence_imm","root","");
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("LOGIN");
		frame.getContentPane().setBackground(SystemColor.menu);
		frame.setBackground(Color.BLACK);
		frame.setSize(517, 419);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		user = new JTextField();
		user.setBounds(228, 84, 150, 29);
		frame.getContentPane().add(user);
		user.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(228, 141, 150, 29);
		frame.getContentPane().add(pass);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String u=user.getText();
				String p=pass.getText();
              
					try {
					Statement stmt=cnx.createStatement();
					String Requet="SELECT * from userlist where name='"+u+"' and password='"+p+"' " ;
					ResultSet result=stmt.executeQuery(Requet);
					if (result.next()){ 
						frame.dispose();
						Agence A =new Agence();
						}
					else	
							JOptionPane.showMessageDialog(null,"Username or Password Incorrect");;
					}
					catch(Exception ev) {ev.printStackTrace();}
		  }
		});
		btnNewButton.setBounds(125, 247, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(273, 247, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("User_Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(84, 91, 80, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel passwordField_1 = new JLabel("Password");
		passwordField_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		passwordField_1.setBounds(84, 148, 80, 14);
		frame.getContentPane().add(passwordField_1);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(0, 0, 511, 390);
		ImageIcon icon =new ImageIcon(this.getClass().getResource("/img2.jpg")); 
		lblNewLabel_1.setIcon(icon);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		
		
		
	}
}

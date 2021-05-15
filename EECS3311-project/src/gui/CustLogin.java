package gui;
import nongui.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustLogin extends JFrame {

	private JPanel contentPane;
	private JTextField emailField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	
	static CustLogin frame = new CustLogin();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// frame = new CustLogin();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustLogin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 20, 600, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblCustomerLogin = new JLabel("Customer Login");
		lblCustomerLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerLogin.setFont(new Font("Dialog", Font.BOLD, 20));
		lblCustomerLogin.setBounds(12, 12, 576, 30);
		contentPane.add(lblCustomerLogin);
		
		JLabel label = new JLabel("Log In to Your Account");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		label.setBounds(12, 54, 576, 25);
		contentPane.add(label);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(111, 119, 70, 15);
		contentPane.add(lblEmail);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(255, 110, 240, 30);
		contentPane.add(emailField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(110, 157, 70, 15);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(255, 150, 240, 30);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				String emailAddress = emailField.getText();
				String password = String.valueOf(passwordField.getPassword());
				String userType = "customer";
				
				if (UserLogin.isValid(emailAddress, password, userType)) {
					JOptionPane.showMessageDialog(null, "Login Successful!");
					CustAccount ca = new CustAccount(emailAddress);
					ca.setVisible(true);
					ca.setResizable(false);
				} else {
					JOptionPane.showMessageDialog(null, "Invalid email or password");
				}
				
			}
		});
		btnLogin.setBounds(239, 215, 117, 25);
		contentPane.add(btnLogin);
		
		JButton btnGoBack = new JButton("<< Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StartWindow sw = new StartWindow();
				sw.setVisible(true);
				sw.setResizable(false);
				frame.dispose();
			}
		});
		btnGoBack.setBounds(239, 278, 117, 25);
		contentPane.add(btnGoBack);
		
		JLabel lblNeedAccount = new JLabel("Need an account?");
		lblNeedAccount.setBounds(111, 400, 144, 30);
		contentPane.add(lblNeedAccount);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerRegistration cr = new CustomerRegistration();
				cr.setVisible(true);
				cr.setResizable(false);
				frame.dispose();
			}
		});
		btnRegister.setBounds(317, 403, 117, 25);
		contentPane.add(btnRegister);
	}
}

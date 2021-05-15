package gui;
import nongui.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class CustomerRegistration extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	static CustomerRegistration frame = new CustomerRegistration();
	private JTextField emailField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JPasswordField passwordField;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
	public CustomerRegistration() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 20, 600, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblRegister = new JLabel("Customer Registration");
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setFont(new Font("Calibri", Font.BOLD, 18));
		lblRegister.setBounds(12, 12, 576, 30);
		contentPane.add(lblRegister);
		
		JLabel label = new JLabel("Email");
		label.setBounds(111, 119, 70, 15);
		contentPane.add(label);
		
		JLabel lblRegisterForAn = new JLabel("Register for an Account");
		lblRegisterForAn.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterForAn.setFont(new Font("Dialog", Font.BOLD, 14));
		lblRegisterForAn.setBounds(12, 54, 576, 25);
		contentPane.add(lblRegisterForAn);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(111, 162, 92, 15);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(111, 205, 92, 15);
		contentPane.add(lblLastName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(111, 247, 92, 15);
		contentPane.add(lblPassword);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(237, 112, 240, 30);
		contentPane.add(emailField);
		
		firstNameField = new JTextField();
		firstNameField.setColumns(10);
		firstNameField.setBounds(237, 155, 240, 30);
		contentPane.add(firstNameField);
		
		lastNameField = new JTextField();
		lastNameField.setColumns(10);
		lastNameField.setBounds(237, 198, 240, 30);
		contentPane.add(lastNameField);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String emailAddress = emailField.getText();
				String firstName = firstNameField.getText();
				String lastName = lastNameField.getText();
				String password = String.valueOf(passwordField.getPassword());
				
				if (CustReg.isValid(emailAddress, password, firstName, lastName)) {
					JOptionPane.showMessageDialog(null, "Registration Successful!");
					CustLogin cl = new CustLogin();
					cl.setVisible(true);
					cl.setResizable(false);
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Email is already in use.");
				}
			}
		});
		btnRegister.setBounds(239, 308, 117, 25);
		contentPane.add(btnRegister);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(237, 240, 240, 30);
		contentPane.add(passwordField);
		
		
	}
	
	/*
	private static String userRegInfoFile = "user_registration_info.csv";
	
	public boolean isValid(String emailToSearch, String password, String firstName, String lastName) {
		if (emailToSearch.equals("") || password.equals("") || firstName.equals("") || lastName.equals("")) {
			return false;
		}
		
		BufferedReader reader = null;
		String line = "";
		
		try {
			reader = new BufferedReader(new FileReader(userRegInfoFile));
			
			while ((line = reader.readLine()) != null) {
				String[] row = line.split(",");
				
				if (row[0].equals(emailToSearch)) {
					return false;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		storeInFile(emailToSearch, password, firstName, lastName);
		return true;
	}
	
	private void storeInFile(String email, String password, String firstName, String lastName) {
		
		try {
			FileWriter fw = new FileWriter(userRegInfoFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			String userType = "customer";
			
			pw.println(email+","+password+","+userType+","+firstName+","+lastName);
			pw.flush();
			pw.close();
		}
		catch (Exception e) {
			
		}
		
	}
	*/
}

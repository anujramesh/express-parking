package gui;
import nongui.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class CustAccount extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	static String email;
	static CustAccount frame = new CustAccount(email);
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
	

	public CustAccount(String email) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 20, 600, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblMyAccount = new JLabel("My Account");
		lblMyAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyAccount.setFont(new Font("Dialog", Font.BOLD, 20));
		lblMyAccount.setBounds(12, 0, 576, 30);
		contentPane.add(lblMyAccount);
		
		JButton btnBookSpace = new JButton("Book Space");
		btnBookSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BookSpace bs = new BookSpace(email);
				bs.setVisible(true);
				bs.setResizable(false);
			}
		});
		btnBookSpace.setBounds(224, 70, 161, 45);
		contentPane.add(btnBookSpace);
		
		JButton btnCancellations = new JButton("Cancellations");
		btnCancellations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// JOptionPane.showConfirmDialog(frame, "Enter a number", "A silly question", JOptionPane.YES_NO_OPTION);
				JOptionPane inOption = new JOptionPane();
				String strDialogResponse = inOption.showInputDialog("Enter booking ID: ");
				
				if (strDialogResponse != null) {
					boolean cancelledBooking = CancelBooking.cancelBooking(strDialogResponse);
					
					if (cancelledBooking) {
						JOptionPane.showMessageDialog(null, "Your booking has been cancelled.");
					} else {
						JOptionPane.showMessageDialog(null, "Unable to locate/cancel booking with ID: " + strDialogResponse);
					}
				}
			}
		});
		btnCancellations.setBounds(224, 149, 161, 45);
		contentPane.add(btnCancellations);
		
		JButton buttonPay = new JButton("Pay");
		buttonPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane inOption = new JOptionPane();
				String strDialogResponse = inOption.showInputDialog("Enter Parking space number: ");
				
				if (strDialogResponse != null) {
					
					int parkingSpaceNum = Integer.parseInt(strDialogResponse);
					
					if (parkingSpaceNum >= 1 && parkingSpaceNum <= 1000) {
						
						int[] fileRowNumAndFeeAmount = PaymentValidator.isParkingSpaceValid(strDialogResponse, email);
						if (fileRowNumAndFeeAmount[0] >= 0) {
							JOptionPane.showMessageDialog(null, "The fee due for this parking space number is: $" + fileRowNumAndFeeAmount[1] +".00.");

							JTextField creditCardNumField = new JTextField();
							JTextField expiryDateField = new JTextField();
							JTextField cvvField = new JTextField();
							
							Object[] fields = {
									"Credit Card Number", creditCardNumField,
									"Expiry date", expiryDateField,
									"CVV", cvvField
							};
							
							JOptionPane.showConfirmDialog(null, fields, "Payment Information", JOptionPane.OK_CANCEL_OPTION);
							
							String creditCardNum = creditCardNumField.getText();
							String expiryDate = expiryDateField.getText();
							String cvv = cvvField.getText();
							
							
							if (PaymentValidator.validateCreditCardNumber(creditCardNum)) {
								if (PaymentValidator.validateCreditCardDate(expiryDate)) {
									if (PaymentValidator.validateCVV(cvv)) {
										// System.out.println("CVV valid");
										changeStatusToPaid(fileRowNumAndFeeAmount[0]);
									} else {
										JOptionPane.showMessageDialog(null, "The CVV you have entered is invalid.");
									}
								} else {
									JOptionPane.showMessageDialog(null, "The date you have entered is invalid.");
								}
							} else {
								JOptionPane.showMessageDialog(null, "The credit card number you have entered is invalid.");
							}
							
						} else {
							JOptionPane.showMessageDialog(null, "Invalid parking space number.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Invalid parking space number.");
					}
				}
			}
		});
		buttonPay.setBounds(224, 226, 161, 45);
		contentPane.add(buttonPay);
		
		CustAccount.email = email;
	}
	
	private static String occupiedSpacesFile = "occupied_parking_spaces.csv";
	
	private static void changeStatusToPaid(int rowNum) {
		BufferedReader reader = null;
		String line = "";
		LinkedList<String> lines = new LinkedList<String>();
		int index = 0;
		String paid = "paid";
		
		try {
			reader = new BufferedReader(new FileReader(occupiedSpacesFile));
			
			while ((line = reader.readLine()) != null) {
				if (index != rowNum) {
					lines.add(line);
				} else {
					String row[] = line.split(",");
					String temp = "";
					
					for (int i = 0; i < row.length - 1; i++) {
						temp += row[i];
						temp += ",";
					}
					
					temp += paid;
					lines.add(temp);
				}

				index++;
			}
			
		} catch (IOException e) {

			e.printStackTrace();
			
		} finally {
			try {
				reader.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		
		File newFile = new File(occupiedSpacesFile);
		try {
			FileWriter fw = new FileWriter(newFile, false);
			
			for (String l : lines) {
					fw.write(l + "\n");
			}
			
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

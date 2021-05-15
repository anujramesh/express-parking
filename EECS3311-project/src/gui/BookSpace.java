package gui;
import nongui.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class BookSpace extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	static String email;
	private JTextField spaceField;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookSpace frame = new BookSpace(email);
					frame.setVisible(true);
					frame.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param email 
	 */
	public BookSpace(String email) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 20, 600, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton btnClick = new JButton("Continue >>");
		btnClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String parkingSpaceEntry = spaceField.getText();
				int parkingSpace = 0;
				
				try {
					parkingSpace = Integer.parseInt(parkingSpaceEntry);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Please enter an integer value.");
				}
				
				if (parkingSpace >= 1 && parkingSpace <= 1000) {
					if (!BookingValidator.isParkingSpaceOccupied(parkingSpace)) {
						
						String input = "";
						int numHours = 0;
						
						// check if customer has entered an integer value between 1 and 3 (inclusive)
						while (input.length() <= 0 || numHours == 0) {
							try {
								input = JOptionPane.showInputDialog("Please enter number of hours (maximum 3):");
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							if (input.length() > 0) {
								
								try {
									numHours = Integer.parseInt(input);
									if (numHours < 1 || numHours > 3) {
										JOptionPane.showMessageDialog(null, "Value must be between 1 and 3.");
										numHours = 0;
									}
									
								} catch (NumberFormatException e) {
									JOptionPane.showMessageDialog(null, "Please enter an integer value.");
								}
								
							}
						}
						
						// check if customer has less than 3 bookings
						
						if (BookingValidator.canBookMore(email)) {
							String bookingSpaceID = BookingValidator.generateUniqueID();
							BookingRequest br = new BookingRequest(parkingSpace, numHours, email, bookingSpaceID);
							
							try {
								BookingRequestPC.addToQueue(br);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							/*
							Thread t = new Thread(new Runnable() {
								public void run() {
									try {
										BookingRequestPC.producer(br);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							});
							
							
							String bookingSpaceID = BookingValidator.bookSpace(parkingSpace, email, numHours);
							*/
							
							JOptionPane.showMessageDialog(null, "You have booked parking space #" + parkingSpace + " for " + numHours + " hours.\nYour booking ID is : " + bookingSpaceID);
						} else {
							JOptionPane.showMessageDialog(null, "You cannot have more than 3 bookings at a time.");
						}
						
						
					} else {
						JOptionPane.showMessageDialog(null, "Parking space occupied. Please enter a different number.");
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "The parking space number you have entered is out of range.");
				}
				
			}
		});
		btnClick.setBounds(213, 186, 176, 30);
		contentPane.add(btnClick);
		
		JLabel lblBookASpace = new JLabel("Book a Space");
		lblBookASpace.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookASpace.setFont(new Font("Dialog", Font.BOLD, 20));
		lblBookASpace.setBounds(12, 0, 576, 30);
		contentPane.add(lblBookASpace);
		
		JLabel lblEnterAParking = new JLabel("Enter a Parking Space Number Between 1-1000");
		lblEnterAParking.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterAParking.setFont(new Font("Dialog", Font.BOLD, 14));
		lblEnterAParking.setBounds(12, 42, 576, 25);
		contentPane.add(lblEnterAParking);
		
		spaceField = new JTextField();
		spaceField.setColumns(10);
		spaceField.setBounds(213, 129, 176, 30);
		contentPane.add(spaceField);
		
	}
	
	
}

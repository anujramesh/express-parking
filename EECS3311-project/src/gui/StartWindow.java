package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;

public class StartWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	static StartWindow frame = new StartWindow();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// frame = new StartWindow();
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
	public StartWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 20, 600, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("Welcome to Express Parking!");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Dialog", Font.BOLD, 24));
		label.setBounds(12, 12, 576, 30);
		contentPane.add(label);
		
		String[] userTypes = {"Customer", "Officer", "Administrator"};
		JComboBox userTypeComboBox = new JComboBox(userTypes);
		contentPane.add(userTypeComboBox);
		userTypeComboBox.setBounds(249, 133, 225, 38);
		
		JLabel lblIAmA = new JLabel("I am a:");
		lblIAmA.setHorizontalAlignment(SwingConstants.CENTER);
		lblIAmA.setFont(new Font("Calibri", Font.BOLD, 18));
		lblIAmA.setBounds(101, 133, 144, 36);
		contentPane.add(lblIAmA);
		
		JButton btnProceed = new JButton("Proceed >>");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String cbSelection = (String)userTypeComboBox.getSelectedItem();
				
				if (cbSelection.equals("Customer")) {
					CustLogin cl = new CustLogin();
					cl.setVisible(true);
					cl.setResizable(false);
					frame.dispose();
				} else if (cbSelection.equals("Officer")) {
					
				} else {
					
				}
				
				frame.dispose();
			}
		});
		btnProceed.setBounds(205, 219, 162, 45);
		contentPane.add(btnProceed);
		

	}
}

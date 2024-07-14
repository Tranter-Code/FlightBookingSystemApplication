package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.AddCustomer;
import bcu.cmp5332.bookingsystem.commands.AddFlight;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class AddCustomerWindow extends JFrame implements ActionListener {
	
	private MainWindow mw;
	private JTextField customerNameText = new JTextField();
	private JTextField customerPhoneText = new JTextField();
	private JTextField customerEmailText = new JTextField();

	private JButton addBtn = new JButton("Add");
	private JButton cancelBtn = new JButton("Cancel");
	
	public AddCustomerWindow(MainWindow mw) {
		this.mw = mw;
		initialize();
	}
	
	private void initialize() {
		
		 try {
			 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {

	    }
		 
		 setTitle("Add a New Customer");
		 
		 setSize(350, 220);
	     JPanel topPanel = new JPanel();
	     topPanel.setLayout(new GridLayout(4, 2));
	     topPanel.add(new JLabel("Name : "));
	     topPanel.add(customerNameText);
	     topPanel.add(new JLabel("Phone Number : "));
	     topPanel.add(customerPhoneText);
	     topPanel.add(new JLabel("Email Address : "));
	     topPanel.add(customerEmailText);
	     
	     JPanel bottomPanel = new JPanel();
	     bottomPanel.setLayout(new GridLayout(1, 3));
	     bottomPanel.add(new JLabel("     "));
	     bottomPanel.add(addBtn);
	     bottomPanel.add(cancelBtn);
	     
	     addBtn.addActionListener(this);
	     cancelBtn.addActionListener(this);
	     
	     this.getContentPane().add(topPanel, BorderLayout.CENTER);
	     this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
	     setLocationRelativeTo(mw);

	     setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == addBtn) {
            addBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }	
	}
	
	private void addBook() {
		try {
			String customerName = customerNameText.getText();
			String customerPhone = customerPhoneText.getText();
			String customerEmail = customerEmailText.getText();
			
			Command addCustomer = new AddCustomer(customerName, customerPhone, customerEmail);
			addCustomer.execute(mw.getFlightBookingSystem());
			
			mw.displayCustomers();
			
			this.setVisible(false);			
		} 
		catch (FlightBookingSystemException ex) {
			JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}

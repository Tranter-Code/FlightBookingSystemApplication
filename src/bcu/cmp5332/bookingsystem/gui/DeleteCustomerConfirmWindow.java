package bcu.cmp5332.bookingsystem.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.DeleteCustomer;
import bcu.cmp5332.bookingsystem.commands.DeleteFlight;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;

/**
 * <h1>Confirms Deleting a customer using GUI</h1>
 *Confirms that the user wants to delete a customer using the Graphical User Interface
 * 
 * @author Dave Tranter & Samuel Wilson
 */


public class DeleteCustomerConfirmWindow extends JFrame implements ActionListener{

	private Customer customer;
	private JButton confirmBtn = new JButton("Confirm");
	private JButton cancelBtn = new JButton("Cancel");
	private MainWindow mw;
	
	/**
	* This method is used to create the DeleteCustomerConfirmWindow
	* @param MainWindow is the first parameter
	* @param customer is the customer being deleted
	* @return Nothing
	*/
	
	public DeleteCustomerConfirmWindow(MainWindow mw, Customer customer) {
		this.customer = customer;
		this.mw = mw;
		initialize();
	}
	
	/**
	* This initialise method is used to initialise the ConfrimDeleteCustomerWindow.
	* It displays the customer information and asks the user to confirm that they want to delete this customer
	* @return Nothing
	*/
	
	public void initialize() {
		
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }
		
		setTitle("Confirm Deleting Customer #" + customer.getId());
		setSize(410, 250);
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2, 3));
		topPanel.add(new JLabel(""));
		topPanel.add(new JLabel("Deleting Customer #" + customer.getId()));
		topPanel.add(new JLabel(""));
		topPanel.add(new JLabel(""));
		topPanel.add(new JLabel("Are You Sure?"));
		topPanel.add(new JLabel(""));
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new GridLayout(4, 1));
		middlePanel.add(new JLabel("Customer ID : #" + customer.getId()));
		middlePanel.add(new JLabel("Name : " + customer.getName()));
		middlePanel.add(new JLabel("Phone Number : " + customer.getPhone()));
		middlePanel.add(new JLabel("Email Address : " + customer.getEmail()));
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 3));
		bottomPanel.add(confirmBtn);
		bottomPanel.add(cancelBtn);
		
		confirmBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		
		this.getContentPane().add(topPanel, BorderLayout.NORTH);
		this.getContentPane().add(middlePanel, BorderLayout.CENTER);
		this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		setVisible(true);
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == confirmBtn) {
            deleteBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }		
	}
	
	public void deleteBook() {
		try {
			Command deleteCustomer = new DeleteCustomer(customer);
			deleteCustomer.execute(mw.getFlightBookingSystem());
			mw.displayCustomers();
			this.setVisible(false);
		}
		catch (FlightBookingSystemException ex) {
			 JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}

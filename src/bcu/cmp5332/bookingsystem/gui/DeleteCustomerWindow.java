package bcu.cmp5332.bookingsystem.gui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.ArrayList;
import java.util.List;

import bcu.cmp5332.bookingsystem.model.Customer;

/**
 * <h1>Deletes customer using GUI</h1>
 *Deletes customers from the FlightBookingSystem using the Graphical User Interface
 * 
 * @author Dave Tranter & Samuel Wilson
 */


public class DeleteCustomerWindow extends JFrame{

	private MainWindow mw;
	
	/**
	* This method is used to create the DeleteCustomerWindow
	* @param MainWindow is the first parameter
	* @return Nothing
	*/
	
	public DeleteCustomerWindow(MainWindow mw) {
		this.mw = mw;
		initialize();
		
	}
	
	private void initialize() {
		
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }
		
		setTitle("Delete a Customer");
		setSize(700, 300);
		
		JPanel topPanel = new JPanel();
		topPanel.add(new JLabel("Select a Customer to Delete"));
		
		List<Customer> customersList = mw.getFlightBookingSystem().getCustomers();
		List<Customer> activeCustomers = new ArrayList<>();
		for (Customer customer : customersList) {
			if (customer.getDeleted() == false) {
				activeCustomers.add(customer);
			}
		}
		
		String[] columns = new String[]{"Name", "Phone", "Email Address", "Number of Bookings"};
    	
    	Object[][] data = new Object[activeCustomers.size()][4];
    	for (int i = 0; i < activeCustomers.size(); i++) {
    		Customer customer = activeCustomers.get(i);
    		data[i][0] = customer.getName();
    		data[i][1] = customer.getPhone();
    		data[i][2] = customer.getEmail();
    		data[i][3] = customer.getBookings().size();
    	}
    	JTable table = new JTable(data, columns);
    	this.getContentPane().removeAll();
    	this.getContentPane().add(new JScrollPane(table));      	
    	table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {   		
			@Override
			public void valueChanged(ListSelectionEvent ex) {
				if (!ex.getValueIsAdjusting()) {
					int selectedCustomerIndex = table.getSelectedRow();
					Customer customer = activeCustomers.get(selectedCustomerIndex);
					new DeleteCustomerConfirmWindow(mw, customer);
					setVisible(false);
				}
			}
    	});
		
		setVisible(true);
    	
	}
	
}

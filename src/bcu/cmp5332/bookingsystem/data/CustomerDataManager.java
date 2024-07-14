package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * <h1>Loads and stores customer data</h1>
 * Loads and stores customer data for the flightBookingSystem
 * 
 * @author Dave Tranter & Samuel Wilson
 */


public class CustomerDataManager implements DataManager {

    private final String RESOURCE = "./resources/data/customers.txt";
    
	/**
	* This method is used to load customer data from the flightBookingSystem
	* @param flightBookingSystem is the first parameter
	* @throws IOException if the data cannot be loaded
	* @throws FlightBookingSystemException if the data cannot be loaded
	* @return Nothing
	*/
    
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
    	try (Scanner sc = new Scanner(new File(RESOURCE))) {
    		int line_idx = 1;
    		while (sc.hasNextLine()) {
    			String line = sc.nextLine();
    			String[] properties = line.split(SEPARATOR, -1);
    			try {
    				int id = Integer.parseInt(properties[0]);
    				String customerName = properties[1];
    				String phoneNumber = properties[2];
    				String emailAddress = properties[3];
    				boolean deleted = Boolean.parseBoolean(properties[4]);
    				Customer customer = new Customer(id, customerName, phoneNumber, emailAddress, deleted);
    				fbs.addCustomer(customer);
    			} catch(NumberFormatException ex) {
    				throw new FlightBookingSystemException("Unable to parse book id " + properties[0] + " on line " + line_idx
    						+ "\nError: " + ex);
    			}
    			line_idx++;
    		}
    	}
    }
    
	/**
	* This method is used to store customer data to the FlightBookingSystem
	* @param FlightBookingSystem is the first parameter
	* @throws IOException if the data cannot be stored
	* @return Nothing
	*/
    
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
    	try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
    		for (Customer customer : fbs.getCustomers()) {
    			out.print(customer.getId() + SEPARATOR);
    			out.print(customer.getName() + SEPARATOR);
    			out.print(customer.getPhone() + SEPARATOR);
    			out.print(customer.getEmail() + SEPARATOR);
    			out.print(customer.getDeleted() + SEPARATOR);
    			out.println();
    		}
    	}
    }
}

package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * <h1>Adds a new customer</h1>
 * This class adds a new customer
 * 
 * <p>
 * @author Dave Tranter & Samuel Wilson
 */

public class AddCustomer implements Command {

    private final String name;
    private final String phone;
    private final String email;

	/**
	* This method is used to get the the name, phone, and email
	* @param name This is the customers name
	* @param phone This is the customer phone number
	* @param email This is the customers email address
	* @return Nothing
	*/
    
    public AddCustomer(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

	/**
	* This is the execute method and is used to add a customer to the flightBookingSystem
	* @param flightBookingSystem is the first parameter
	* @throws FlightBookingSystemException if the customer cannot be added
	* @return Nothing
	*/
    
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        int maxId = 0;
        if (flightBookingSystem.getCustomers().size() > 0) {
        	int lastIndex = flightBookingSystem.getCustomers().size() - 1;
        	maxId = flightBookingSystem.getCustomers().get(lastIndex).getId();
        }
        
        Customer customer = new Customer(++maxId, name, phone, email, false);
        flightBookingSystem.addCustomer(customer);
        System.out.println("Customer: " + customer.getName() + " added.");
        
    }
}

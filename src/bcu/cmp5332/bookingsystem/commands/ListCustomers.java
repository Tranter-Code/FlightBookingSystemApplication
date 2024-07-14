package bcu.cmp5332.bookingsystem.commands;

import java.util.List;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * <h1>Lists customers</h1>
 * Displays a list of all customers
 * 
 * <p>
 * @author Dave Tranter & Samuel Wilson
 */


public class ListCustomers implements Command{
	
	/**
	* This is the execute method and is used to display a list of all customers
	* @param flightBookingSystem is the first parameter
	* @throws FlightBookingSystemException if the list of customers cannot be fetched or displayed
	* @return Nothing
	*/
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		List<Customer> customers = flightBookingSystem.getCustomers();
		for (Customer customer : customers) {
			System.out.println(customer.getDetailsShort());
		}
		System.out.println(customers.size() + " customer(s)");
	}

}

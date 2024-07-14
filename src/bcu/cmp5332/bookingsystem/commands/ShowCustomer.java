package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * <h1>Shows customer details</h1>
 * Displays the details of a specific customer
 * 
 * <p>
 * @author Dave Tranter & Samuel Wilson
 */


public class ShowCustomer implements Command{
	
	private final int id;
	
	/**
	* This method is used to get the id of the customer whose details we are displaying
	* @param id is the id of the customer
	* @return Nothing
	*/
	
	public ShowCustomer(int id) {
		this.id = id;
	}
	
	/**
	* This is the execute method and it displays the details of a specific customer
	* @param flightBookingSystem is the first parameter
	* @throws FlightBookingSystemException if the customer details cannot be fetched or displayed
	* @return Nothing
	*/
	
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		Customer customer = flightBookingSystem.getCustomerByID(id);
		System.out.println(customer.getDetailsLong());
		
	}

}

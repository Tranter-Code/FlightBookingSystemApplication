package bcu.cmp5332.bookingsystem.commands;

import java.util.List;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * <h1>Delete a customer</h1>
 * This class deletes a customer
 * 
 * <p>
 * @author Dave Tranter & Samuel Wilson
 */

public class DeleteCustomer implements Command{

	private Customer customer;
	
	/**
	* This method is used to get the customer
	* @param customer is the customer that is going to be removed
	* @return Nothing
	*/
	
	public DeleteCustomer(Customer customer) {
		this.customer = customer;
	}
	
	/**
	* This is the execute method and is used to delete a customer from the flightBookingSystem
	* @param flightBookingSystem is the first parameter
	* @throws FlightBookingSystemException if the customer cannot be deleted
	* @return Nothing
	*/
    
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		customer.setDeleted(true);
		List<Booking> bookings = customer.getBookings();
		for (int i = 0; i < bookings.size(); i++) {
			Booking booking = bookings.get(i);
			Flight flight = booking.getFlight();
			flight.removePassenger(customer);
			customer.cancelBookingForFlight(flight);
		}
	}

}

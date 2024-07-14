package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * <h1>Adds a new booking</h1>
 * This class adds a new booking
 * 
 * @author Dave Tranter & Samuel Wilson
 */

public class AddBooking implements Command{
	
	private final int customerId;
	private final int flightId;

	/**
	* This method is used to get the customerId and the flightId
	* @param customerId This is the customers id
	* @param flightId  This is the flights id
	* @return Nothing
	*/
	
	public AddBooking(int customerId, int flightId) {
		this.customerId = customerId;
		this.flightId = flightId;
	}
	
	/**
	* This is the execute method and is used to add booking to the flightBookingSystem
	* @param flightBookingSystem is the first parameter
	* @throws FlightBookingSystemException if the booking cannot be created
	* @return Nothing
	*/
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		Customer customer = flightBookingSystem.getCustomerByID(customerId);
		Flight flight = flightBookingSystem.getFlightByID(flightId);
		if (customer.getDeleted()) {
			throw new FlightBookingSystemException("Unable to create Booking. \nThis Customer was Deleted.");
		} else if (flight.getDeleted()) {
			throw new FlightBookingSystemException("Unable to create Booking. \nThis Flight was Cancelled");
		} else if (flight.getDeleted() && customer.getDeleted()) {
			throw new FlightBookingSystemException("Unable to create Booking. \nFlight and Customer were removed from the System.");
		} else {
			Booking booking = new Booking(customer, flight, flightBookingSystem.getSystemDate(), "Original");
			
			flightBookingSystem.getFlightByID(flightId).addPassenger(customer);		
			flightBookingSystem.getCustomerByID(customerId).addBooking(booking);

			

			System.out.println("Booking created.");
		}
	
	}

}

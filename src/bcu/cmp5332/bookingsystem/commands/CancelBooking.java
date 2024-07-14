package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * <h1>Cancels a booking</h1>
 * This class cancels a booking
 * 
 * <p>
 * @author Dave Tranter & Samuel Wilson
 */


public class CancelBooking implements Command{
	private final int customerId;
	private final int flightId;
	
	/**
	* This method is used to get the customerId and flightId
	* @param customerId is the customers id
	* @param flightId is the flights id
	* @return Nothing
	*/
	
	public CancelBooking(int customerId, int flightId) {
		this.customerId = customerId;
		this.flightId = flightId;
	}

	/**
	* This is the execute method used to remove a booking from the flightBookingSystem
	* @param flightBookingSystem is the first parameter
	* @throws FlightBookingSystemException if the booking cannot be removed
	* @return Nothing
	*/
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		Customer customer = flightBookingSystem.getCustomerByID(customerId);
		Flight flight = flightBookingSystem.getFlightByID(flightId);
		
		flightBookingSystem.getCustomerByID(customerId).cancelBookingForFlight(flight);
		
		flightBookingSystem.getFlightByID(flightId).removePassenger(customer);

		
		System.out.println("Booking Cancelled.");
	}
}

package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * <h1>Edit a booking</h1>
 * This class edits a booking
 * 
 * <p>
 * @author Dave Tranter & Samuel Wilson
 */


public class EditBooking implements Command{
	
	private final int customerId;
	private final int oldFlightId;
	private final int newFlightId;
	
	/**
	* This method is used to get the customerId, oldFlightId, and newFlightId
	* @param customerId is the id of the customer whose booking is being edited
	* @param oldFlightId is the id of the old flight
	* @param newFlightId is the id of the new flight that is replacing the old flight
	* @return Nothing
	*/
	
	
	public EditBooking(int customerId, int oldFlightId, int newFlightId) {
		this.customerId = customerId;
		this.oldFlightId = oldFlightId;
		this.newFlightId = newFlightId;		
	}
	
	/**
	* This is the execute method and is used to edit a booking in the flightBookingSystem
	* @param flightBookingSystem is the first parameter
	* @throws FlightBookingSystemException if the flight cannot be edited
	* @return Nothing
	*/
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		// TODO Auto-generated method stub
		Customer customer = flightBookingSystem.getCustomerByID(customerId);
		Flight oldFlight = flightBookingSystem.getFlightByID(oldFlightId);
		Flight newFlight = flightBookingSystem.getFlightByID(newFlightId);
		for (int i = 0; i < customer.getBookings().size(); i++) {
			Booking booking = customer.getBookings().get(i);
			if (booking.getFlight() == oldFlight && booking.getCustomer() == customer) {
				flightBookingSystem.getFlightByID(newFlightId).addPassenger(customer);
				flightBookingSystem.getFlightByID(oldFlightId).removePassenger(customer);
				booking.setFlight(newFlight);
				booking.setStatus("Edited");
				System.out.println("Booking Edited.");
			}
		}


		
	}

}

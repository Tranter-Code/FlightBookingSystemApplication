package bcu.cmp5332.bookingsystem.commands;

import java.util.List;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * <h1>Delete a flight</h1>
 * This class deletes a flight
 * 
 * <p>
 * @author Dave Tranter & Samuel Wilson
 */

public class DeleteFlight implements Command{
	
	private Flight flight;
	
	/**
	* This method is used to get the flight
	* @param flight is the flight that is going to be removed
	* @return Nothing
	*/
	
	public DeleteFlight(Flight flight) {
		this.flight = flight;
	}

	/**
	* This is the execute method and is used to delete a flight from the flightBookingSystem
	* @param flightBookingSystem is the first parameter
	* @throws FlightBookingSystemException if the flight cannot be deleted
	* @return Nothing
	*/
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		flight.setDeleted(true);
		List<Customer> passengers = flight.getPassengers();
		for (Customer passenger : passengers) {
			passenger.cancelBookingForFlight(flight);		
		}
		flight.getPassengers().clear();		
	}
}

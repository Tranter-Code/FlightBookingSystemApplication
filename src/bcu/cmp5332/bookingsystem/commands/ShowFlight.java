package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * <h1>Shows flight details</h1>
 * Displays the details of a specific flight
 * 
 * <p>
 * @author Dave Tranter & Samuel Wilson
 */


public class ShowFlight implements Command{
	
	private final int id;
	
	/**
	* This method is used to get the id of the flight whose details we are displaying
	* @param id is the id of the flight
	* @return Nothing
	*/
	
	public ShowFlight(int id) {		
		this.id = id;		
	}

	/**
	* This is the execute method and it displays the details of a specific flight
	* @param flightBookingSystem is the first parameter
	* @throws FlightBookingSystemException if the flight details cannot be fetched or displayed
	* @return Nothing
	*/
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		Flight flight = flightBookingSystem.getFlightByID(id);	
		System.out.println(flight.getDetailsLong());
	}

}

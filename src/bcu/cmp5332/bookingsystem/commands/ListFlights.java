package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.util.List;

/**
 * <h1>Lists flights</h1>
 * Displays a list of all flights
 * 
 * <p>
 * @author Dave Tranter & Samuel Wilson
 */


public class ListFlights implements Command {
	
	/**
	* This is the execute method and is used to display a list of all flights
	* @param flightBookingSystem is the first parameter
	* @throws FlightBookingSystemException if the list of flights cannot be fetched or displayed
	* @return Nothing
	*/
	
	
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        List<Flight> flights = flightBookingSystem.getFlights();
        int deletedFlights = 0;
        for (Flight flight : flights) {
        	if (flight.getDeleted() == false) {
        		System.out.println(flight.getDetailsShort());
        	} 
        	else if (flight.getDeleted() == true ) {
        		deletedFlights += 1;
        	}
        }
        System.out.println(flights.size() - deletedFlights + " flight(s)");
    }
}

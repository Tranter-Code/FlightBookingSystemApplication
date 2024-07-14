package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.time.LocalDate;

/**
 * <h1>Adds a new flight</h1>
 * This class adds a new flight
 * 
 * <p>
 * @author Dave Tranter & Samuel Wilson
 */

public class AddFlight implements  Command {

    private final String flightNumber;
    private final String origin;
    private final String destination;
    private final LocalDate departureDate;
    private final int capacity;
    private double price;

	/**
	* This method is used to get the origin, destinatiom, departure date, capacity, and price
	* @param flightNumber This is the flight number
	* @param origin This is the flights origin location
	* @param destination This is the flights destination location
	* @param departureDate This is the flights departure date
	* @param capacity This is the flights passenger capacity
	* @param price This is the price of a ticket on this flight
	* @return Nothing
	*/
    
    public AddFlight(String flightNumber, String origin, String destination, LocalDate departureDate, int capacity, double price) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
		this.capacity = capacity;
		this.price = price;    
    }
    
	/**
	* This is the execute method and is used to add a flight to the flightBookingSystem
	* @param flightBookingSystem is the first parameter
	* @throws FlightBookingSystemException if the flight cannot be added to the flightBookingSystem
	* @return Nothing
	*/
    
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        int maxId = 0;
        if (flightBookingSystem.getFlights().size() > 0) {
            int lastIndex = flightBookingSystem.getFlights().size() - 1;
            maxId = flightBookingSystem.getFlights().get(lastIndex).getId();
        }
        
        Flight flight = new Flight(++maxId, flightNumber, origin, destination, departureDate, capacity, price, false);
        flightBookingSystem.addFlight(flight);
        System.out.println("Flight #" + flight.getId() + " added.");
    }
}

package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * <h1>Loads and stores booking data</h1>
 * Loads and stores booking data for the flightBookingSystem
 * 
 * @author Dave Tranter & Samuel Wilson
 */

public class BookingDataManager implements DataManager {
    
    public final String RESOURCE = "./resources/data/bookings.txt";

	/**
	* This method is used to load booking data from the flightBookingSystem
	* @param flightBookingSystem is the first parameter
	* @throws IOException if the data cannot be loaded
	* @throws FlightBookingSystemException if the data cannot be loaded
	* @return Nothing
	*/
	
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
    	try (Scanner sc = new Scanner(new File(RESOURCE))) {
    		int line_idx = 1;
    		while (sc.hasNextLine()) {
    			String line = sc.nextLine();
    			String[] properties = line.split(SEPARATOR, -1);
    			try {
    				int customerId = Integer.parseInt(properties[0]);
    				int flightId = Integer.parseInt(properties[1]);
    				LocalDate bookingDate = LocalDate.parse(properties[2]);
    				String status = properties[3];
    				
    				Customer customer = fbs.getCustomerByID(customerId);
    				Flight flight = fbs.getFlightByID(flightId);
    				Booking booking = new Booking(customer, flight, bookingDate, status);
    				fbs.getCustomerByID(customerId).addBooking(booking);
    				fbs.getFlightByID(flightId).addPassenger(customer);
    				
    			} catch (NumberFormatException ex) {
    	    		throw new FlightBookingSystemException("Unable to parse book id " + properties[0] + " on line " + line_idx
    	    				+ "\nError: " + ex);
    	    	}
    			line_idx++;
    		}
    	}     		
    }

	/**
	* This method is used to store booking data to the FlightBookingSystem
	* @param FlightBookingSystem is the first parameter
	* @throws IOException if the data cannot be stored
	* @return Nothing
	*/
	
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
    	try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
    		for (Customer customer : fbs.getCustomers()) {
    			for (Booking booking : customer.getBookings()) {
    				out.print(booking.getCustomer().getId() + SEPARATOR);
    				out.print(booking.getFlight().getId() + SEPARATOR);
    				out.print(booking.getBookingDate() + SEPARATOR);
    				out.print(booking.getStatus() + SEPARATOR);
    				out.println();
    			}
    		}
    		
    	}
    }
    
}

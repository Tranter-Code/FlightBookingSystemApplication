package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.IOException;

/**
 * <h1>Data manager</h1>
 * Manages data for the flightBookingSystem
 * 
 * @author Dave Tranter & Samuel Wilson
 */


public interface DataManager {
    
    public static final String SEPARATOR = "::";
    
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException;
    public void storeData(FlightBookingSystem fbs) throws IOException;
    
}

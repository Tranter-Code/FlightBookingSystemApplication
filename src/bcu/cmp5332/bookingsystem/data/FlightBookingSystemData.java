package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Manages FlightBookingSystemData</h1>
 * Manages the FlightBookingSystemData
 * 
 * @author Dave Tranter & Samuel Wilson
 */


public class FlightBookingSystemData {
    
    private static final List<DataManager> dataManagers = new ArrayList<>();
    
    // runs only once when the object gets loaded to memory
    static {
        dataManagers.add(new FlightDataManager());
        
        /* Uncomment the two lines below when the implementation of their 
        loadData() and storeData() methods is complete */
        dataManagers.add(new CustomerDataManager());
        dataManagers.add(new BookingDataManager());
    }
    
	/**
	* This method is used to load data from the flightBookingSystem
	* @param flightBookingSystem is the first parameter
	* @throws IOException if the data cannot be loaded
	* @throws FlightBookingSystemException if the data cannot be loaded
	* @return Nothing
	*/
    public static FlightBookingSystem load() throws FlightBookingSystemException, IOException {

        FlightBookingSystem fbs = new FlightBookingSystem();
        for (DataManager dm : dataManagers) {
            dm.loadData(fbs);
        }
        return fbs;
    }
    
	/**
	* This method is used to load customer data from the flightBookingSystem
	* @param flightBookingSystem is the first parameter
	* @throws IOException if the data cannot be loaded
	* @throws FlightBookingSystemException if the data cannot be loaded
	* @return Nothing
	*/
    
    public static void store(FlightBookingSystem fbs) throws IOException {

        for (DataManager dm : dataManagers) {
            dm.storeData(fbs);
        }
    }
    
}

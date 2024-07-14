package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import bcu.cmp5332.bookingsystem.gui.MainWindow;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

/**
 * <h1>Loads GUI</h1>
 * Loads the Graphical User Interface
 * 
 * <p>
 * @author Dave Tranter & Samuel Wilson
 */


public class LoadGUI implements Command {
	
	/**
	* This is the execute method and it opens up the MainWindow for the GUI
	* @param flightBookingSystem is the first parameter
	* @throws FlightBookingSystemException if the GUI cannot be displayed
	* @return Nothing
	*/
	
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        new MainWindow(flightBookingSystem);
    }
    
}

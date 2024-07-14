package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * <h1>Display help message</h1>
 * Displays a help message to help the user navigate the program
 * 
 * <p>
 * @author Dave Tranter & Samuel Wilson
 */


public class Help implements Command {
	
	/**
	* This is the execute method and is used display the help message
	* @return Nothing
	*/
	
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) {
        System.out.println(Command.HELP_MESSAGE);
    }
}

package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * <h1>Creates the help message</h1>
 * Creates the help message that is displayed when the Help class is called
 * 
 * @author Dave Tranter & Samuel Wilson
 */



public interface Command {
	
	/**
	* This method creates the help message
	* @param HELP_MESSAGE is the first parameter
	* @return Nothing
	*/
	
    public static final String HELP_MESSAGE = "Commands:\n"
        + "\tlistflights                                                 print all flights\n"
        + "\tlistcustomers                                               print all customers\n"
        + "\taddflight                                                   add a new flight\n"
        + "\taddcustomer                                                 add a new customer\n"
        + "\tshowflight [flight id]                                      show flight details\n"
        + "\tshowcustomer [customer id]                                  show customer details\n"
        + "\taddbooking [customer id] [flight id]                        add a new booking\n"
        + "\tcancelbooking [customer id] [flight id]                     cancel a booking\n"
        + "\teditbooking [customer id] [old flight id] [new flight id]   update a booking\n"
        + "\tloadgui                                                     loads the GUI version of the app\n"
        + "\thelp                                                        prints this help message\n"
        + "\texit                                                        exits the program";

    
	/**
	* This is the excecute method and it creates the HELP_MESSAGE
	* @param FlightBookingSystem is the first parameter
	* @throws FlightBookingSystemException if the help message cannot be created
	* @return Nothing
	*/
    
    
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException;
    
}

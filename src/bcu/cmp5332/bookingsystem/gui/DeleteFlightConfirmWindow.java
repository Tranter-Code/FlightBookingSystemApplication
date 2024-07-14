package bcu.cmp5332.bookingsystem.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.DeleteFlight;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;

/**
 * <h1>Confirms Deleting a flight using GUI</h1>
 *Confirms that the user wants to delete a flight using the Graphical User Interface
 * 
 * @author Dave Tranter & Samuel Wilson
 */


public class DeleteFlightConfirmWindow extends JFrame implements ActionListener{
	

	private Flight flight;
	private JButton confirmBtn = new JButton("Confirm");
	private JButton cancelBtn = new JButton("Cancel");
	private MainWindow mw;
	
	/**
	* This method is used to create the DeleteFlightConfirmWindow
	* @param MainWindow is the first parameter
	* @param flight is the flight being deleted
	* @return Nothing
	*/
	
	public DeleteFlightConfirmWindow(MainWindow mw, Flight flight) {
		this.flight = flight;
		this.mw = mw;
		initialize();
	}
	
	/**
	* This initialise method is used to initialise the ConfrimDeleteFlightWindow.
	* It displays the flight information and asks the user to confirm that they want to delete this flight
	* @return Nothing
	*/
	
	public void initialize() {
		
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }
		
		setTitle("Confirm Deleting Flight " + flight.getFlightNumber());
		setSize(410, 250);
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2, 3));
		topPanel.add(new JLabel(""));
		topPanel.add(new JLabel("Deleting Flight " + flight.getFlightNumber()));
		topPanel.add(new JLabel(""));
		topPanel.add(new JLabel(""));
		topPanel.add(new JLabel("Are You Sure?"));
		topPanel.add(new JLabel(""));
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new GridLayout(4, 1));
		middlePanel.add(new JLabel("Flight # " + flight.getFlightNumber()));
		middlePanel.add(new JLabel("Origin : " + flight.getOrigin()));
		middlePanel.add(new JLabel("Destination : " + flight.getDestination()));
		middlePanel.add(new JLabel("Departure Date : " + flight.getDepartureDate()));
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 3));
		bottomPanel.add(confirmBtn);
		bottomPanel.add(cancelBtn);
		
		confirmBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		
		this.getContentPane().add(topPanel, BorderLayout.NORTH);
		this.getContentPane().add(middlePanel, BorderLayout.CENTER);
		this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		setVisible(true);
		
	}

	/**
	* This method is used to check if the action has been peformed
	* @param ActionEvent iis used to check if the action has been peformed
	* @return Nothing
	*/
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == confirmBtn) {
            deleteBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }
	
	/**
	* This method is used to delete the flight from the FlightBookingSystem
	* @exception FlightBookingSystemException if the flight could not be deleted
	* @return Nothing
	*/
	
	public void deleteBook() {
		try {
			Command deleteFlight = new DeleteFlight(flight);
			deleteFlight.execute(mw.getFlightBookingSystem());
			mw.displayFlights();
			this.setVisible(false);
		}
		catch (FlightBookingSystemException ex) {
			 JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}

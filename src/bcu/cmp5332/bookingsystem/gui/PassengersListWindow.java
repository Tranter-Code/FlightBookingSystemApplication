package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.commands.AddFlight;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
public class PassengersListWindow extends JFrame{

	private Flight flight;
	
	public PassengersListWindow(Flight flight) {
		this.flight = flight;
		initialize();
	}
	
	private void initialize() {
		
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }
		
		setTitle("Displaying Flight : " + flight.getFlightNumber() + "'s Passengers");
		setSize(800, 400);
		JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Bookings for : " + flight.getFlightNumber()));
        
        List<Customer> passengersList = flight.getPassengers();
        
        String[] columns = new String[]{"Name", "Phone Number", "Email Address", "Date Booked"};
        
        Object[][] data = new Object[passengersList.size()][4];
        for (int i = 0; i < passengersList.size(); i++) {
        	Customer customer = passengersList.get(i);
        	data[i][0] = customer.getName();
        	data[i][1] = customer.getPhone();
        	data[i][2] = customer.getEmail();
        	for (int j = 0; j < customer.getBookings().size(); j++) {
        		Booking booking = customer.getBookings().get(j);
        		if (booking.getFlight() == flight && booking.getCustomer() == customer) {
        			data[i][3] = booking.getBookingDate();
        		}       		
        	}        	
        }
        JTable table = new JTable(data, columns);
        
        this.getContentPane().add(topPanel, BorderLayout.NORTH);
        this.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
        
        setVisible(true);
	}
}

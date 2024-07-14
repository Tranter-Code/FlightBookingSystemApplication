package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
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

public class BookingsListWindow extends JFrame{
	
	private Customer customer;
	
	public BookingsListWindow(Customer customer) {
		
		this.customer = customer;
		initialize();		
	}
	
	private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }
        
        setTitle("Displaying Customer : " + customer.getName() + "'s Bookings");
        setSize(800, 400);
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Bookings for : " + customer.getName()));
        
        List<Booking> bookingsList = customer.getBookings();
        
        String[] columns = new String[]{"Flight Number", "Origin", "Destination", "Departure Date", "Date Booked", "Status"};
        
        Object[][] data = new Object[bookingsList.size()][6];
        for (int i = 0; i < bookingsList.size(); i++) {
        	Booking booking = bookingsList.get(i);
        	data[i][0] = booking.getFlight().getFlightNumber();
        	data[i][1] = booking.getFlight().getOrigin();
        	data[i][2] = booking.getFlight().getDestination();
        	data[i][3] = booking.getFlight().getDepartureDate();
        	data[i][4] = booking.getBookingDate();
        	if (booking.getStatus().equals("Original")) {
        		data[i][5] = "Booked";
        	} else if (booking.getStatus().equals("Cancelled")) {
        		data[i][5] = "Cancelled";
        	} else if (booking.getStatus().equals("Edited")) {
        		data[i][5] = "Edited";
        	}
        }
        JTable table = new JTable(data, columns);
        
        this.getContentPane().add(topPanel, BorderLayout.NORTH);
        this.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
        
        
        setVisible(true);
	
	}

}

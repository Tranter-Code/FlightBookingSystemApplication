package bcu.cmp5332.bookingsystem.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;

/**
 * <h1>Deletes flight using GUI</h1>
 *Deletes flights from the FlightBookingSystem using the Graphical User Interface
 * 
 * @author Dave Tranter & Samuel Wilson
 */

public class DeleteFlightWindow extends JFrame{

	private MainWindow mw;
	
	/**
	* This method is used to create the DeleteFlightWindow
	* @param MainWindow is the first parameter
	* @return Nothing
	*/
	
	public DeleteFlightWindow(MainWindow mw) {
		this.mw = mw;
		initialize();
	}
	
	private void initialize() {
		
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }
		
		setTitle("Delete a Flight");
		setSize(700, 300);
		
		JPanel topPanel = new JPanel();
		topPanel.add(new JLabel("Select a Flight to Delete."));
		
		List<Flight> flightsList = mw.getFlightBookingSystem().getFlights();  
        List<Flight> activeFlights = new ArrayList<>();        
        for(Flight flight : flightsList) {
        	if(flight.getDeleted() == false) {
        		activeFlights.add(flight);
        	}
        }
        
        // headers for the table
        String[] columns = new String[]{"Flight No", "Origin", "Destination", "Departure Date", "Number of Seats", "Price"};

        Object[][] data = new Object[activeFlights.size()][6];
        for (int i = 0; i < activeFlights.size(); i++) {
            Flight flight = activeFlights.get(i);
            if(flight.getDeleted() == false) {
            	data[i][0] = flight.getFlightNumber();
                data[i][1] = flight.getOrigin();
                data[i][2] = flight.getDestination();
                data[i][3] = flight.getDepartureDate();
                data[i][4] = flight.getCapacity();
                data[i][5] = flight.getPrice(); 
            }
        }
        JTable table = new JTable(data, columns);
        this.getContentPane().add(topPanel, BorderLayout.NORTH);
        this.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {   		
			@Override
			public void valueChanged(ListSelectionEvent ex) {
				if (!ex.getValueIsAdjusting()) {
					int selectedFlightIndex = table.getSelectedRow();
					Flight flight = activeFlights.get(selectedFlightIndex);
					new DeleteFlightConfirmWindow(mw, flight);
					setVisible(false);
				}
			}
    	});
        
        setVisible(true);
		
	}
}

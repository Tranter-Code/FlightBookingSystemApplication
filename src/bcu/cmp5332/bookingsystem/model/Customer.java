package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    
    private int id;
    private String name;
    private String phone;
    private final List<Booking> bookings;
    private String email;
    private boolean deleted;   
    
    public Customer (int id, String name, String phone, String email, boolean deleted) {
    	
    	this.id = id;
    	this.name = name;
    	this.phone = phone;
    	this.email = email;
    	this.deleted = deleted;
    	
    	bookings = new ArrayList<>();
    }
    
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getPhone() {
    	return phone;
    }
    
    public void setPhone(String phone) {
    	this.phone = phone;
    }
    
    public List<Booking> getBookings() {
    	return bookings;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public boolean getDeleted() {
    	return deleted;
    }
    
    public void setDeleted(boolean deleted) {
    	this.deleted = deleted;
    }
    
    public String getDetailsShort() {
    	return "Customer #" + id + " - " + name + " - " + phone + " - " + email;
    }
    
    public String getDetailsLong() {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY/MM/dd");
    	
    	String detailsLong = "";
    	String bookingsList = "";
    	
    	if (this.deleted) {
    		detailsLong = "Customer #" + id + "\n"
        			+ "Name: " + name + "\n"
        			+ "Phone: " + phone + "\n"
        			+ "Email Address: " + email + "\n"
        			+ "CUSTOMER DELETED! \n";
    	} 
    	else if(!this.deleted) {
    		detailsLong = "Customer #" + id + "\n"
        			+ "Name: " + name + "\n"
        			+ "Phone: " + phone + "\n"
        			+ "Email Address: " + email + "\n";
    	}
    	
    	
    
    	for (Booking booking : bookings) {
    		
    		if (booking.getStatus().equals("Original")) {
    			bookingsList += " * Booking date: " + booking.getBookingDate().format(dtf)
        				+ " for Flight #" + booking.getFlight().getId() + " - "
        				+ booking.getFlight().getFlightNumber() + " - "
        				+ booking.getFlight().getOrigin() + " to " + booking.getFlight().getDestination()
        				+ " on " + booking.getFlight().getDepartureDate().format(dtf) + "\n";
    		} else if (booking.getStatus().equals("Cancelled")) {
    			bookingsList += " * Booking date: " + booking.getBookingDate().format(dtf)
        				+ " for Flight #" + booking.getFlight().getId() + " - "
        				+ booking.getFlight().getFlightNumber() + " - "
        				+ booking.getFlight().getOrigin() + " to " + booking.getFlight().getDestination()
        				+ " on " + booking.getFlight().getDepartureDate().format(dtf) 
        				+ " - " + booking.getStatus() + "\n";
    		} else if (booking.getStatus().equals("Edited")) {
    			bookingsList += " * Booking date: " + booking.getBookingDate().format(dtf)
        				+ " for Flight #" + booking.getFlight().getId() + " - "
        				+ booking.getFlight().getFlightNumber() + " - "
        				+ booking.getFlight().getOrigin() + " to " + booking.getFlight().getDestination()
        				+ " on " + booking.getFlight().getDepartureDate().format(dtf) 
        				+ " - " + booking.getStatus() + "\n";
    		}
    	}
    	
    	return detailsLong
    			+ "-----------------------------\n"
    			+ "Bookings:\n"
    			+ bookingsList
    			+ String.valueOf(bookings.size()) + " booking(s)";
    }
    
    public void addBooking(Booking booking) throws FlightBookingSystemException {
    	if (bookings.contains(booking)) {
    		throw new FlightBookingSystemException("Booking aready created.");
    	}
    	bookings.add(booking);
    }
    
    
    // TODO
    public void cancelBookingForFlight(Flight flight) throws FlightBookingSystemException {
    	
    	    	
    	for (int i = 0; i < bookings.size(); i++) {
    		Booking booking = bookings.get(i);
    		if(booking.getFlight().equals(flight) && booking.getCustomer().equals(this)) {
    			booking.setStatus("Cancelled");
    		}
    		else if (i > bookings.size()){
    			throw new FlightBookingSystemException("Booking not found.");
    		}
    	}
    	
    }    
}

package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Flight {
    
    private int id;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private int capacity;
    private double price;
    private boolean deleted;

    private final Set<Customer> passengers;

    public Flight(int id, String flightNumber, String origin, String destination, LocalDate departureDate, int capacity, double price, boolean deleted) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        passengers = new HashSet<>();       
        this.capacity = capacity - passengers.size();
        this.price = price;
        this.deleted = deleted;  
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    
    public String getOrigin() {
        return origin;
    }
    
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
    
    public int getCapacity() {
    	return capacity;
    }
    
    public void setCapacity(int capacity) {
    	this.capacity = capacity;
    }
    
    public double getPrice() {
    	return price;
    }
    
    public void setPrice(double price) {
    	this.price = price;
    }
    
    public boolean getDeleted() {
    	return deleted;
    }
    
    public void setDeleted(boolean deleted) {
    	this.deleted = deleted;
    }

    public List<Customer> getPassengers() {
        return new ArrayList<>(passengers);
    }
	
    public String getDetailsShort() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return "Flight #" + id + " - " + flightNumber + " - " + origin + " to " 
                + destination + " on " + departureDate.format(dtf) + " - "
                + "Seats: " + capacity + " - " + "Price: £" + price;
    }

    public String getDetailsLong() {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY/MM/dd");
    	
    	String detailsLong = "";
    	String passengersList = "";
    	
    	if (this.deleted) {
    		detailsLong = "Flight #" + id + "\n"
        		+ "Flight No: " + flightNumber + "\n"
        		+ "Origin: " + origin + "\n"
        		+ "Destination: " + destination + "\n"
        		+ "Departure Date: " + departureDate.format(dtf) + "\n"
        		+ "Number of Seats: " + String.valueOf(getCapacity()) + "\n"
        		+ "Price: £" + String.valueOf(getPrice() + "\n"
        		+ "FLIGHT DELETED! \n");
    	}
    	else if (!this.deleted) {
    		detailsLong = "Flight #" + id + "\n"
        		+ "Flight No: " + flightNumber + "\n"
        		+ "Origin: " + origin + "\n"
        		+ "Destination: " + destination + "\n"
        		+ "Departure Date: " + departureDate.format(dtf) + "\n"
        		+ "Number of Seats: " + String.valueOf(getCapacity()) + "\n"
        		+ "Price: £" + String.valueOf(getPrice() + "\n");
    	}
    	
    	
    	
    	for(Customer passenger : passengers) {
    		passengersList += " * Id: " + passenger.getId() 
    		+ " - " + passenger.getName() 
    		+ " - " + passenger.getPhone() + "\n";
    	} 
    	
    	return detailsLong 
    			+ "-----------------------------\n"
    			+ "Passengers:\n"
    			+ passengersList
    			+ String.valueOf(passengers.size()) + " Passenger(s)";
    }
    
    public void addPassenger(Customer passenger) throws FlightBookingSystemException {
        if (passengers.contains(passenger)) {
        	throw new FlightBookingSystemException("Passenger already added.");
        } else if (passengers.size() == capacity) {
        	throw new FlightBookingSystemException("No seats left on this flight.");
        }
        passengers.add(passenger);
    }
    
    public void removePassenger(Customer passenger) throws FlightBookingSystemException {
    	if (!passengers.contains(passenger)) {
    		throw new FlightBookingSystemException("Passenger does not exist.");
    	}
    	passengers.remove(passenger);
    }
}

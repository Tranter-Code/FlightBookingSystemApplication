package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

public class Booking {
    

    private Customer customer;
    private Flight flight;
    private LocalDate bookingDate;
    private String status;
    

    public Booking(Customer customer, Flight flight, LocalDate bookingDate, String status) {
    	this.customer = customer;
    	this.flight = flight;
    	this.bookingDate = bookingDate; 
    	this.status = status;
    }
    
    public Customer getCustomer() {
    	return customer;
    }
    
    public void setCustomer(Customer customer) {
    	this.customer = customer;
    }
    
    public Flight getFlight() {
    	return flight;
    }
    
    public void setFlight(Flight flight) {
    	this.flight = flight;
    }
    
    public LocalDate getBookingDate() {
    	return bookingDate;
    }
    
    public void setBookingDate(LocalDate bookingDate) {
    	this.bookingDate = bookingDate;
    }
    
    public String getStatus() {
    	return status;
    }
    
    public void setStatus(String status) {
    	this.status = status;
    }
}

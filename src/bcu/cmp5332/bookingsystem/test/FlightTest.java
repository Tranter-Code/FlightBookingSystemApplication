package bcu.cmp5332.bookingsystem.test;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import bcu.cmp5332.bookingsystem.model.Flight;


class FlightTest {
	
	@Test
	void testConstructor() {
		LocalDate date = LocalDate.parse("2020-11-11");
		Flight flight = new Flight(1, "ABC123", "London", "America", date, 5, 50, false);
		assertEquals(1, flight.getId());
		assertEquals("ABC123", flight.getFlightNumber());
		assertEquals("London", flight.getOrigin());
		assertEquals("America", flight.getDestination());
		assertEquals(date, flight.getDepartureDate());
		assertEquals(5, flight.getCapacity());
		assertEquals(50, flight.getPrice());
		assertEquals(false, flight.getDeleted());
	}

	@Test
	void testGetCapacity() {
		Flight flight = new Flight(1, "FL123", "London", "NewYork", null, 10, 150.50, false);
		assertEquals(10, flight.getCapacity());		
	}
	
	@Test
	void testGetPrice() {
		Flight flight = new Flight(1, "FL123", "London", "NewYork", null, 10, 150.50, false);
		assertEquals(150.50, flight.getPrice());
	}
	
	@Test
	void testSetCapacity() {
		Flight flight = new Flight(1, "FL123", "London", "NewYork", null, 10, 150.50, false);
		flight.setCapacity(5);
		assertTrue(flight.getCapacity() == 5);
	}
	
	@Test
	void testSetPrice() {
		Flight flight = new Flight(1, "FL123", "London", "NewYork", null, 10, 150.50, false);
		flight.setPrice(500);
		assertTrue(flight.getPrice() == 500);
	}

}

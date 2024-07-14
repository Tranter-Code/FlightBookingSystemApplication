package bcu.cmp5332.bookingsystem.test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import bcu.cmp5332.bookingsystem.model.Customer;

class CustomerTest {
	
	@Test
	void testConstructor() {
		Customer customer = new Customer(1, "David", "12345", "David@Mail.com", false);
		assertEquals(1, customer.getId());
		assertEquals("David", customer.getName());
		assertEquals("12345", customer.getPhone());
		assertEquals("David@Mail.com", customer.getEmail());
		assertEquals(false, customer.getDeleted());
	}

	@Test
	void testGetEmail() {
		Customer customer = new Customer(1, "Test", "123456789", "Test@Email.com", false);
		assertEquals("Test@Email.com", customer.getEmail());
	}
	
	@Test
	void testSetEmail() {
		Customer customer = new Customer(1, "Test", "123456789", "Test@Email.com", false);
		customer.setEmail("Email@Test.com");
		assertTrue(customer.getEmail() == "Email@Test.com");
	}

}

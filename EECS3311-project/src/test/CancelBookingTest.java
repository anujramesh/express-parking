package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import nongui.BookingValidator;
import nongui.CancelBooking;

class CancelBookingTest {
	
	@Test
	void test1() {	// cancelling a booking that has just been made, should be able to do
		String id = BookingValidator.generateUniqueID();
		BookingValidator.bookSpace(777, "abc@usermail.com", 3, id);
		boolean cancelBooking = CancelBooking.cancelBooking(id);
		assertEquals(true, cancelBooking);
	}
	
	@Test
	void test2() {	// cancelling a booking that does not exist, should not be able to do
		String randomID = "a382f032";
		boolean cancelBooking = CancelBooking.cancelBooking(randomID);
		assertEquals(false, cancelBooking);
	}
	
	@Test
	void test3() { // cancelling a booking that has already expired, should not be able to do
		String expiredBookingID = "9a0c134b";
		boolean cancelBooking = CancelBooking.cancelBooking(expiredBookingID);
		
		assertEquals(false, cancelBooking);
	}
}

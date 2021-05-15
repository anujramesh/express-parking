package test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import nongui.BookingValidator;
import nongui.CancelBooking;

class BookingValidatorTest {

	@Test
	void test1() {
		boolean isParkingSpaceOccupied = BookingValidator.isParkingSpaceOccupied(999);	// not an occupied space
		assertEquals(false, isParkingSpaceOccupied);
	}
	
	@Test
	void test2() {
		boolean isParkingSpaceOccupied = BookingValidator.isParkingSpaceOccupied(543);	// occupied space
		assertEquals(true, isParkingSpaceOccupied);
	}
	
	@Test
	void test3() {
		boolean canBookMore = BookingValidator.canBookMore("anujramesh@hotmail.com");	// this email cannot book any more, has more than 3 bookings
		assertEquals(false, canBookMore);
	}
	
	@Test
	void test4() {
		boolean canBookMore = BookingValidator.canBookMore("rickjames@gmail.com");
		assertEquals(true, canBookMore);
	}
	
	@Test
	void test5() {
		boolean canBookMore = BookingValidator.canBookMore("rickjames@gmail.com");
		assertEquals(true, canBookMore);
	}
	
	@Test
	void test6() throws FileNotFoundException, IOException {	// check if it can generate 1000 unique IDs
		
		int result = 0;
		try
		(
		   FileReader input = new FileReader("occupied_parking_spaces.csv");
		   LineNumberReader count = new LineNumberReader(input);
		)
		{
		   while (count.skip(Long.MAX_VALUE) > 0)
		   {
		      // Loop just in case the file is > Long.MAX_VALUE or skip() decides to not read the entire file
		   }
		
		   result = count.getLineNumber() + 1;             // +1 because line index starts at 0
		}
		
		result--;
		
		// We create a hashset to see if we can add all of our unique IDs into it
		// If we can, all are distinct
		
		Set<String> uniqueIDSet = new HashSet<String>();
		boolean unableToAddToSet = false;
		
		for (int i = 0; i < 1000; i++) {
			String uniqueID = BookingValidator.generateUniqueID();
			BookingValidator.bookSpace(i+1, "user@usermail.com", 2, uniqueID);
			unableToAddToSet = !uniqueIDSet.add(uniqueID);
			
			if (unableToAddToSet) {
				break;
			}
		}
		
		for (int i = result + 1000; i >= result; i--) {
			CancelBooking.removeRow(i);
		}
		
		assertEquals(false, unableToAddToSet);
	}

}

package test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import org.junit.jupiter.api.Test;

import nongui.BookingValidator;
import nongui.CancelBooking;
import nongui.PaymentValidator;

class PaymentValidatorTest {
	
	@Test
	void isParkingSpaceValidTest1() throws IOException {	// a parking space that has not been paid for should be valid
		
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
		
		   result = count.getLineNumber() + 1;                                    // +1 because line index starts at 0
		}
		
		result--;
		
		BookingValidator.bookSpace(179, "user@usermail.com", 2);
		int[] fileRowNumAndFeeAmount = PaymentValidator.isParkingSpaceValid("179", "user@usermail.com");
		assertEquals(true, fileRowNumAndFeeAmount[0] >= 0);
		
		CancelBooking.removeRow(result);
		
	}
	
	@Test
	void isParkingSpaceValidTest2() {	// a parking space that has already been paid for should be invalid
		
		int[] fileRowNumAndFeeAmount = PaymentValidator.isParkingSpaceValid("111", "anujramesh@hotmail.com");
		assertEquals(false, fileRowNumAndFeeAmount[0] >= 0);
	}
	
	@Test
	void validateCreditCardNumberTest1() { // a credit card number that satisfies Luhn Algorithm will be valid
		String validCardNumber = "4916103808670373";
		boolean validateCreditCardNumber = PaymentValidator.validateCreditCardNumber(validCardNumber);
		assertEquals(true, validateCreditCardNumber);
	}
	
	@Test
	void validateCreditCardNumberTest2() { // a credit card number that does not satisfy Luhn Algorithm will be invalid
		String invalidCardNumber = "987654321888383";
		boolean validateCreditCardNumber = PaymentValidator.validateCreditCardNumber(invalidCardNumber);
		assertEquals(false, validateCreditCardNumber);
	}
	
	@Test
	void validateCreditCardNumberTest3() { // An empty string will be invalid
		String emptyString = "";
		boolean validateCreditCardNumber = PaymentValidator.validateCreditCardNumber(emptyString);
		assertEquals(false, validateCreditCardNumber);
	}
	
	@Test
	void validateCreditCardDateTest1() {	// improper date format should be invalid even if date is in the future
		String invalidFormatDate = "05-23";
		boolean validateCreditCardDate = PaymentValidator.validateCreditCardDate(invalidFormatDate);
		assertEquals(false, validateCreditCardDate);
	}
	
	@Test
	void validateCreditCardDateTest2() {	// A date from the past should be invalid
		String pastDate = "05/11";
		boolean validateCreditCardDate = PaymentValidator.validateCreditCardDate(pastDate);
		assertEquals(false, validateCreditCardDate);
	}
	
	@Test
	void validateCreditCardDateTest3() {	// A date in the future should be valid
		String futureDate = "09/22";
		boolean validateCreditCardDate = PaymentValidator.validateCreditCardDate(futureDate);
		assertEquals(true, validateCreditCardDate);
	}
	
	@Test
	void validateCVVTest1() {	// invalid cvv, two digits
		String invalidcvv = "32";
		boolean validateCVV = PaymentValidator.validateCVV(invalidcvv);
		assertEquals(false, validateCVV);
	}
	
	@Test
	void validateCVVTest2() {	// invalid cvv, four digits
		String invalidcvv = "3237";
		boolean validateCVV = PaymentValidator.validateCVV(invalidcvv);
		assertEquals(false, validateCVV);
	}
	
	@Test
	void validateCVVTest3() {	// invalid, empty string
		String emptyString = "";
		boolean validateCVV = PaymentValidator.validateCVV(emptyString);
		assertEquals(false, validateCVV);
	}
	
	@Test
	void validateCVVTest4() {	// valid cvv, three digits
		String validcvv = "327";
		boolean validateCVV = PaymentValidator.validateCVV(validcvv);
		assertEquals(true, validateCVV);
	}
	
	@Test
	void validateCVVTest5() {	// valid cvv, three digits
		String validcvv = "026";
		boolean validateCVV = PaymentValidator.validateCVV(validcvv);
		assertEquals(true, validateCVV);
	}
}

package nongui;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentValidator {
	
	private static String occupiedSpacesFile = "occupied_parking_spaces.csv";
	
	public static int[] isParkingSpaceValid(String parkingSpaceNum, String email) {
		int[] fileRowNumAndFeeAmount = {-1, -1};	// Will contain the file row number in index 0, fee amount due in index 1
		int rowNum = 0;
		BufferedReader reader = null;
		String line = "";
		int parkingSpaceCol = 0;
		int emailCol = 1;
		int feeAmountCol = 5;
		int paidOrUnpaidCol = 6;
		
		try {
			
			reader = new BufferedReader(new FileReader(occupiedSpacesFile));
			while ((line = reader.readLine()) != null) {
				String[] row = line.split(",");
				
				if (row.length > 1) {
					if (row[parkingSpaceCol].equals(parkingSpaceNum) && row[emailCol].equals(email) && row[paidOrUnpaidCol].equals("unpaid")) {
						fileRowNumAndFeeAmount[0] = rowNum;
						fileRowNumAndFeeAmount[1] = Integer.parseInt(row[feeAmountCol]);
						return fileRowNumAndFeeAmount;
					}
				}
				
				rowNum++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return fileRowNumAndFeeAmount;
	}
	
	// Uses Luhn algorithm to validate credit card number
	public static boolean validateCreditCardNumber(String creditCardNumAsString) {
		
		if (creditCardNumAsString.length() == 0) {
			return false;
		}
		
		// convert string as int array
		int[] creditCardIntArr = new int[creditCardNumAsString.length()];
		
		for (int i = 0; i < creditCardNumAsString.length(); i++) {
			creditCardIntArr[i] = Integer.parseInt(creditCardNumAsString.substring(i, i + 1));
		}
		
		// starting from right, double every other digit, if greater than 9, mod 10 and add 1 to the remainder
		for (int i = creditCardIntArr.length - 2; i >= 0; i = i - 2) {
			int tempVal = creditCardIntArr[i];
			tempVal = tempVal * 2;
			
			if (tempVal > 9) {
				tempVal = tempVal % 10 + 1;
			}
			
			creditCardIntArr[i] = tempVal;
		}
		
		
		// Sum up all digits
		int sum = 0;
		
		for (int i = 0; i < creditCardIntArr.length; i++) {
			sum += creditCardIntArr[i];
		}
		
		// If sum is a multiple of 10, it is valid
		if (sum % 10 == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean validateCreditCardDate(String expiryDate) {
		
		// Make sure expiry date string has a MM/YY format
		if (!expiryDate.matches("(?:0[1-9]|1[0-2])/[0-9]{2}")) {
			return false;
		}
		
		// parse the string to a date and compare it to the current date
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
		simpleDateFormat.setLenient(false);
		boolean isValid = false;
		
		try {
			Date expiry = simpleDateFormat.parse(expiryDate);
			isValid = !expiry.before(new Date());	// valid if expiry dates comes after current date
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isValid;
	}
	
	public static boolean validateCVV(String cvv) {
		String regex = "^[0-9]{3}$";
		Pattern p = Pattern.compile(regex);
		
		Matcher m = p.matcher(cvv);
		return m.matches();
	}
	
}

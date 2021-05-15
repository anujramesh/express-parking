package nongui;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class BookingValidator {
	
	private static String occupiedSpacesFile = "occupied_parking_spaces.csv";
	public static boolean isParkingSpaceOccupied(int parkingSpace) {
		
		BufferedReader reader = null;
		String line = "";
		
		try {
			
			reader = new BufferedReader(new FileReader(occupiedSpacesFile));
			String spaceNumAsString = Integer.toString(parkingSpace);
			
			while ((line = reader.readLine()) != null) {
				String[] row = line.split(",");
				
				if (row[0].equals(spaceNumAsString)) {
					return true;
				}
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	public static boolean canBookMore(String email) {
		
		BufferedReader reader = null;
		String line = "";
		int custNumOfBookings = 0;
		
		try {
			
			reader = new BufferedReader(new FileReader(occupiedSpacesFile));
			
			while ((line = reader.readLine()) != null) {
				String[] row = line.split(",");
				
				if (row.length > 1) {
					if (row[1].equals(email)) {
						custNumOfBookings++;
						
						if (custNumOfBookings >= 3) {
							return false;
						}
					}
				}
				
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	public static void bookSpace(int parkingSpace, String email, int numHours, String id) {
		
		try {
			FileWriter fw = new FileWriter(occupiedSpacesFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			long currentTimeInMillis = System.currentTimeMillis();
			int millisPerHour = 3600000;
			long expirationTimeInMillis = currentTimeInMillis + (long)(numHours * millisPerHour);
			int parkingFeePerHour = 5;
			int feeAmountDue = parkingFeePerHour * numHours;
			String unpaid = "unpaid";
			// uniqueID = generateUniqueID();
			
			pw.println(parkingSpace+","+email+","+currentTimeInMillis+","+expirationTimeInMillis+","+id+","+feeAmountDue+","+unpaid);
			pw.flush();
			pw.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static String generateUniqueID() {
		Random rand = new Random();
		int randomNum = rand.nextInt((999 - 100) + 1) + 100;

		long timeSegment = (System.nanoTime()) & 0xfffff;
		String uniqueValue = Long.toHexString(timeSegment) + "" + Long.toHexString(randomNum);
		
		return uniqueValue;
	}
}

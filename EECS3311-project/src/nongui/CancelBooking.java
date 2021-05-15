package nongui;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class CancelBooking {
	
	private static String occupiedSpacesFile = "occupied_parking_spaces.csv";
	
	// returns true if removed, false if not found
	public static boolean cancelBooking(String bookingID) {
		
		int rowNum = 0;
		BufferedReader reader = null;
		String line = "";
		
		try {
			
			reader = new BufferedReader(new FileReader(occupiedSpacesFile));
			
			while ((line = reader.readLine()) != null) {
				String[] row = line.split(",");
				
				if (row.length > 1) {
					
					if (row[4].equals(bookingID)) {	// A bookingID match found
						long endTimeMillis = Long.parseLong(row[3]);
						long currTimeMillis = System.currentTimeMillis();
						
						if (currTimeMillis < endTimeMillis) {	// cancel only if time of cancellation is before booking expiration time
							removeRow(rowNum);
							return true;
						} else {
							return false;
						}

					}
				}
				
				rowNum++;
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
	
	public static void removeRow(int rowNumber) {
		BufferedReader reader = null;
		String line = "";
		LinkedList<String> lines = new LinkedList<String>();
		int index = 0;
		
		try {
			reader = new BufferedReader(new FileReader(occupiedSpacesFile));
			
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {

			e.printStackTrace();
			
		} finally {
			try {
				reader.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		
		File newFile = new File(occupiedSpacesFile);
		try {
			FileWriter fw = new FileWriter(newFile, false);
			
			for (String l : lines) {
				if (index != rowNumber) {
					fw.write(l + "\n");
				}
				
				index++;
			}
			
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

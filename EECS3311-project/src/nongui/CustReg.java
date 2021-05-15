package nongui;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.Base64;

public class CustReg {
	
	private static String userRegInfoFile = "user_registration_info.csv";
	
	public static boolean isValid(String emailToSearch, String password, String firstName, String lastName) {
		if (emailToSearch.equals("") || password.equals("") || firstName.equals("") || lastName.equals("")) {
			return false;
			
		} else if (!firstName.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+")) {	// Make sure firstName and lastName contain only letters
			return false;
			
		} else if (!emailToSearch.contains("@")) {	// make sure email address has an @
			return false;
		}
		
		BufferedReader reader = null;
		String line = "";
		
		try {
			reader = new BufferedReader(new FileReader(userRegInfoFile));
			
			while ((line = reader.readLine()) != null) {
				String[] row = line.split(",");
				
				if (row[0].equals(emailToSearch)) {
					return false;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		storeInFile(emailToSearch, password, firstName, lastName);
		return true;
	}
	
	private static void storeInFile(String email, String password, String firstName, String lastName) {
		
		try {
			FileWriter fw = new FileWriter(userRegInfoFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			String userType = "customer";
			
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			random.nextBytes(salt);
			String saltAsString = Base64.getEncoder().encodeToString(salt);
			String digest = PasswordHasher.generateHash(password, saltAsString);
			
			pw.println(email+","+userType+","+firstName+","+lastName+","+saltAsString+","+digest);
			pw.flush();
			pw.close();
		}
		catch (Exception e) {
			
		}
		
	}

}

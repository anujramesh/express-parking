package nongui;
import java.io.*;

public class UserLogin {
	
	private static String userRegInfoFile = "user_registration_info.csv";
	
	public static boolean isValid(String emailEntered, String passwordEntered, String userType) {
		
		if (emailEntered.equals("") || passwordEntered.equals("") || userType.equals("")) {
			return false;
		}
		
		BufferedReader reader = null;
		String line = "";
		
		try {
			reader = new BufferedReader(new FileReader(userRegInfoFile));
			
			while ((line = reader.readLine()) != null) {
				String[] row = line.split(",");
				
				if (row[0].equals(emailEntered)) {
					if (row[1].equals(userType) && validateEmailEntered(passwordEntered,row[4], row[5])) {
						return true;
					} else {
						return false;
					}
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	private static boolean validateEmailEntered(String password, String salt, String passwordHashValue) {
		String digest = PasswordHasher.generateHash(password, salt);
		
		if (digest.equals(passwordHashValue)) {
			return true;
		}
		
		return false;
	}

}

package nongui;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHasher {
	
	// Uses PBKDF2 salted hashing 
	public static String generateHash(String plaintext, String saltAsString) {
		
		byte[] salt = Base64.getDecoder().decode(saltAsString);

		KeySpec spec = new PBEKeySpec(plaintext.toCharArray(), salt, 65536, 128);
		SecretKeyFactory factory = null;
		try {
			factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			byte[] hash = factory.generateSecret(spec).getEncoded();
			String s = Base64.getEncoder().encodeToString(hash);
			return s;
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}

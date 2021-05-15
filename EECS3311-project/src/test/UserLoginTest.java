package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import nongui.UserLogin;

class UserLoginTest {

	@Test
	void test1() {
		boolean isValid = UserLogin.isValid("anujramesh@hotmail.com", "password123", "customer");
		assertEquals(true, isValid);
	}
	
	@Test
	void test2() {
		boolean isValid = UserLogin.isValid("", "", "");
		assertEquals(false, isValid);
	}
	
	@Test
	void test3() {
		boolean isValid = UserLogin.isValid("anujramesh@hotmail.com", "password123", "officer");
		assertEquals(false, isValid);
	}

}

package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import nongui.CustReg;

class CustRegTest {

	@Test
	void test1() {
		boolean isValid = CustReg.isValid("bobby12@yahoo.com", "pass", "Bobby", "Brown");
		assertEquals(true, isValid);
	}
	
	@Test
	void test2() {
		boolean isValid = CustReg.isValid("roberto@yahoo.com", "pass", "firs3.tName", "lastName9");
		assertEquals(false, isValid);
	}
	
	@Test
	void test3() {
		boolean isValid = CustReg.isValid("rob.yahoo.com", "pass", "Rob", "G");
		assertEquals(false, isValid);
	}
	
	@Test
	void test4() {
		boolean isValid = CustReg.isValid("anujramesh@hotmail.com", "pass", "Anuj", "Ramesh");
		assertEquals(false, isValid);
	}

}

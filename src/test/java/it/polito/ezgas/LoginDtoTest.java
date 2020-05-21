package it.polito.ezgas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezgas.dto.LoginDto;

public class LoginDtoTest {

	LoginDto logIn;
	
	@Before
	public void init() {
		logIn = new LoginDto();
	}
	
	@Test
	public void testNullObject() {
		assertEquals(null, logIn.getEmail());
	}
	
	@Test
	public void testCostructor() {
		logIn = new LoginDto((Integer)12, "user12", "tok12", "user12@user.com", 5);
		assertEquals((Integer)12, logIn.getUserId());
		assertEquals("user12", logIn.getUserName());
		assertEquals("tok12", logIn.getToken());
		assertEquals("user12@user.com", logIn.getEmail());
		assertEquals((Integer)5, logIn.getReputation());
	}
 
	@Test
	public void testUserId() {
		logIn.setUserId(12);
		assertEquals((Integer)12, logIn.getUserId());
	}
	
	@Test
	public void testUserName() {
		logIn.setUserName("user12");
		assertEquals("user12", logIn.getUserName());
	}
	
	@Test
	public void testToken() {
		logIn.setToken("tok12");
		assertEquals("tok12", logIn.getToken());
	}
	
	@Test
	public void testEmail() {
		logIn.setEmail("user12@user.com");
		assertEquals("user12@user.com", logIn.getEmail());
	}
	
	@Test
	public void testReputation() {
		logIn.setReputation(5);
		assertEquals((Integer)5, logIn.getReputation());
	}
	
	@Test
	public void testAdmin() {
		logIn.setAdmin(true);
		assertEquals(true, logIn.getAdmin());
	}
}



package it.polito.ezgas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezgas.entity.User;

public class UserTest {
	User user;
	int actualValue;
	@Before
	public void init() {
		this.user = new User();
	}
	
	private Integer userId = 15;
	private String userName = "Username15";
	private String password = "pass15";
	private String email = "test15@email.com";
	private Integer reputation = 3;
	private Boolean admin = false;
	

	@Test
	public void testUserEntityConstructor() {
		user = new User(this.userName,this.password,this.email,this.reputation);
		assertEquals(null, user.getUserId());
		assertEquals(this.userName, user.getUserName());
		assertEquals(this.password, user.getPassword());
		assertEquals(this.email, user.getEmail());
		assertEquals(this.reputation, user.getReputation());
	}
	
	@Test
	public void testUserIdMinInt() {
		user.setUserId(Integer.MIN_VALUE);
		actualValue = user.getUserId();
		assertEquals(Integer.MIN_VALUE, actualValue);
	}
	
	@Test
	public void testUserIdMinIntPlusOne() {
		user.setUserId(Integer.MIN_VALUE + 1);
		actualValue = user.getUserId();
		assertEquals(Integer.MIN_VALUE + 1, actualValue);
	}
	
	@Test
	public void testUserIdMinusOne() {
		user.setUserId(-1);
		actualValue = user.getUserId();
		assertEquals(-1, actualValue);	
	}
	
	@Test
	public void testUserIdZero() {
		user.setUserId(0);
		actualValue = user.getUserId();
		assertEquals(0, actualValue);
	}
	
	@Test
	public void testUserIdMaxInt() {
		user.setUserId(Integer.MAX_VALUE);
		actualValue = user.getUserId();
		assertEquals(Integer.MAX_VALUE, actualValue);
	}
	
	@Test
	public void testUserIdMaxIntMinusOne() {
		user.setUserId(Integer.MAX_VALUE -1);
		actualValue = user.getUserId();
		assertEquals(Integer.MAX_VALUE -1, actualValue);
	}
	
	@Test
	public void testUserName() {
		user.setUserName(this.userName);
		assertEquals(this.userName,this.user.getUserName());
	}
	
	@Test
	public void testPassword() {
		user.setPassword(password);
		assertEquals(this.password,this.user.getPassword()); 
	}
	
	@Test
	public void testEmail() {
		user.setEmail(email);
		assertEquals(this.email,this.user.getEmail()); 
	}
	
	@Test
	public void testReputation() {
		user.setReputation(reputation);
		assertEquals(this.reputation,this.user.getReputation()); 
	}
	
	@Test
	public void testAdmin() {
		user.setAdmin(admin);
		assertEquals(this.admin,this.user.getAdmin()); 
	}
	
}
package it.polito.ezgas;


import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	    @Autowired
	    UserRepository userRepository;
	    
	    @Before
	    public void init() {
	    	userRepository.save(new User("user1", "password1", "user1@example.com", 2));
	    	userRepository.save(new User("user2", "password2", "user2@example.com", -3));
	    	userRepository.save(new User("user3", "password3", "user3@example.com", 1));
	    	userRepository.save(new User("user4", "password4", "user4@example.com", 5));
	    }
	    
	    @Test
	    public void testFindByEmail() {
	        assertNotNull(userRepository.findByEmail("user3@example.com"));
	    }
	    
	    @Test
	    public void testFindByUserId() {
	    	assertNotNull(userRepository.findByUserId(1));
	    }
	    
}

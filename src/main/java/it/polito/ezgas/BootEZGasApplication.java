package it.polito.ezgas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;

@SpringBootApplication
public class BootEZGasApplication {
	
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BootEZGasApplication.class, args);
	}
	
	@PostConstruct
	public void setupDbWithData() throws SQLException{
		
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		conn.close();
		
		User user = userRepository.findByEmail("admin@ezgas.com");
		if(user==null) {
			user= new User("admin", "admin", "admin@ezgas.com", 5);
			user.setAdmin(true);
		}
		userRepository.save(user);
		
		/*
		 
		list all the users stored in the database and, if there is no an admin user create it
		 
			User user= new User("admin", "admin", "admin@ezgas.com", 5);
			user.setAdmin(true);
			
		and then save it in the db
	
			
		*/

	}

}

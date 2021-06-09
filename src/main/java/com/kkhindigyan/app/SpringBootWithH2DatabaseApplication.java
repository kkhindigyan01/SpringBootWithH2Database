package com.kkhindigyan.app;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.kkhindigyan.app.dao.UserRepository;
import com.kkhindigyan.app.entities.User;

@SpringBootApplication
public class SpringBootWithH2DatabaseApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootWithH2DatabaseApplication.class, args);
		
		UserRepository userRepository = applicationContext.getBean(UserRepository.class);
		createUser(userRepository);
		createUsers(userRepository);
		
	}

	private static void createUsers(UserRepository userRepository) {
		User user1 = new User();
		user1.setName("Sudha Verma");
		user1.setAge(31);
		user1.setDob(LocalDate.of(1998, Month.AUGUST, 20));
		
		User user2 = new User();
		user2.setName("Raj Verma");
		user2.setAge(32);
		user2.setDob(LocalDate.of(1998, Month.FEBRUARY, 22));
		
		List<User> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user2);
		Iterable<User> dbSavedUsers = userRepository.saveAll(userList);
		System.out.println("Following users info saved in database:");
		dbSavedUsers.forEach(System.out::println);
	}

	private static void createUser(UserRepository userRepository) {
		User user = new User();
		user.setName("Sean Murphy");
		user.setAge(30);
		user.setDob(LocalDate.of(1998, Month.MARCH, 20));
		User dbUser = userRepository.save(user);
		System.out.println("Following user info saved in database:");
		System.out.println(dbUser);
	}
}

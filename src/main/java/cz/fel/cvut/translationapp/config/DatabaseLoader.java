package cz.fel.cvut.translationapp.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cz.fel.cvut.translationapp.model.Admin;
import cz.fel.cvut.translationapp.model.LoggedEvent;
import cz.fel.cvut.translationapp.model.User;
import cz.fel.cvut.translationapp.service.repository.AdminRepository;
import cz.fel.cvut.translationapp.service.repository.UserRepository;

/**
 * @author Marek Kozlovsky
 */
@Component
public class DatabaseLoader implements CommandLineRunner {

	private final UserRepository userRepository;
	private final AdminRepository adminRepository;

	@Autowired
	public DatabaseLoader(UserRepository userRepository, AdminRepository adminRepository) {
		this.userRepository = userRepository;
		this.adminRepository = adminRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
		User u1 = new User("johnson@doe1.com", "bobek");
		User u2 = new User("johnson@doe2.com", "bobek");
		User u3 = new User("johnson@doe3.com", "bobek");
		Admin admin = new Admin("johnson4@doe.com", "bobek");
		admin.setLoggedEvents(new HashSet<LoggedEvent>() {
			{
				LoggedEvent event = new LoggedEvent();
				event.setDescription("Translation desc. 1");
				add(event);

			}
		});
		admin = adminRepository.save(admin);
		System.out.println(admin);
		u1 = userRepository.save(u1);
		List<User> u1Friends = new ArrayList<>();
		u1Friends.add(u2);
		u1.setFriends(u1Friends);
		u2 = userRepository.save(u2);
		u3 = userRepository.save(u3);
		u1.getFriends().add(u3);
		u1 = userRepository.save(u1);

		for (User user : userRepository.findAll()) {
			System.out.println(user);
		}
	}
}

package cz.fel.cvut.translationapp.config;

import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cz.fel.cvut.translationapp.api.TranslationProvider;
import cz.fel.cvut.translationapp.api.dummy.DummyTranslation;
import cz.fel.cvut.translationapp.model.Admin;
import cz.fel.cvut.translationapp.model.LoggedEvent;
import cz.fel.cvut.translationapp.model.User;
import cz.fel.cvut.translationapp.service.repository.AdminRepository;
import cz.fel.cvut.translationapp.service.repository.DummyTranslationRepository;
import cz.fel.cvut.translationapp.service.repository.LoggedEventRepository;
import cz.fel.cvut.translationapp.service.repository.UserRepository;

/**
 * @author Marek Kozlovsky
 */
@Component
@Transactional
public class DatabaseLoader implements CommandLineRunner {

	private final UserRepository userRepository;
	private final AdminRepository adminRepository;
	private final DummyTranslationRepository translationRepository;
	private final TranslationProvider<DummyTranslation> dummyTranslationProvider;
	private final LoggedEventRepository loggedEventRepository;

	@Autowired
	public DatabaseLoader(UserRepository userRepository, AdminRepository adminRepository,
			DummyTranslationRepository translationRepository,
			TranslationProvider<DummyTranslation> dummyTranslationProvider,
			LoggedEventRepository loggedEventRepository) {
		this.userRepository = userRepository;
		this.adminRepository = adminRepository;
		this.translationRepository = translationRepository;
		this.dummyTranslationProvider = dummyTranslationProvider;
		this.loggedEventRepository = loggedEventRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
		
		userRepository.deleteAll();
		for (int i = 0; i < 5; i++) {
			User user = new User("john@ahoj.cz" + i, "bobek");
			user.setTranslations(new HashSet<>());
			user.setFriends(new HashSet<>());
			user = userRepository.save(user);
		}

		Iterable<User> users = userRepository.findAll();
		for (User user : users) {
			int randomInt = 5;

			for (int i = 0; i < randomInt; i++) {
				DummyTranslation translation = dummyTranslationProvider.translate("Nazdárek!", "cz", "en");
				translation.setUser(user);
				translation = translationRepository.save(translation);

				Hibernate.initialize(user.getTranslations());
				while (!Hibernate.isInitialized(user.getTranslations())) {
					Thread.sleep(1000);
					System.out.println("waiting");
				}

				user.getTranslations().add(translation);
				System.out.println("     " + translation);
			}
			// add himself to his friendlist
			user.getFriends().add(user);
			
			user = userRepository.save(user);
			System.out.println(randomInt);
			System.out.println("hi " + user);

		}

		Admin adminToSave = new Admin("admin@admin.cz", "bobek");
		adminToSave.setTranslations(new HashSet<>());
		adminToSave.setFriends(new HashSet<>());
		adminToSave.setLoggedEvents(new HashSet<>());
		adminRepository.save(adminToSave);
		
		int o = 0;
		Iterable<Admin> admins = adminRepository.findAll();
		for (Admin admin : admins) {
			admin.setLoggedEvents(new HashSet<LoggedEvent>());
			o++;
			for (int i = 0; i < 10; i++) {

				LoggedEvent event = new LoggedEvent();
				event.setDescription(("Translation desc. " + i) + o);
				event.setAdmin(admin);
				loggedEventRepository.save(event);
				Hibernate.initialize(admin.getLoggedEvents());

				admin.getLoggedEvents().add(event);

			}
			admin = adminRepository.save(admin);
			System.out.println("admin je tu : " + admin);
		}

	}
}

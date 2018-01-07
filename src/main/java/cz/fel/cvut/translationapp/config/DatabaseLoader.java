package cz.fel.cvut.translationapp.config;

import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cz.fel.cvut.translationapp.api.dummy.DummyTranslation;
import cz.fel.cvut.translationapp.api.dummy.DummyTranslationProvider;
import cz.fel.cvut.translationapp.model.Admin;
import cz.fel.cvut.translationapp.model.LoggedEvent;
import cz.fel.cvut.translationapp.model.User;
import cz.fel.cvut.translationapp.service.LoginService;

/**
 * @author Marek Kozlovsky
 */
@Component
@Transactional()
public class DatabaseLoader implements CommandLineRunner {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private DummyTranslationProvider dummyTranslationProvider;

	@Autowired
	private LoginService loginService;

	public DatabaseLoader() {
	}

	@Override
	public void run(String... strings) throws Exception {

		System.out.println(em);
		Query query = em.createNamedQuery("User.DeleteAllUsers");
		query.executeUpdate();
		System.out.println("Just deleted all");

		int numberOfUsers = 5;

		for (int i = 0; i < numberOfUsers; i++) {
			User user = new User("john@ahoj.cz" + i, "bobek");
			user.setTranslations(new HashSet<>());
			user.setFriends(new HashSet<>());

			em.persist(user);

			user.getFriends().add(user);
			for (int o = 0; o < 10; o++) {
				DummyTranslation translation = dummyTranslationProvider.translate("Nazdárek!", "cz", "en");
				translation.setUser(user);
				em.persist(translation);
				user.getTranslations().add(translation);
			}
			em.persist(user);
		}

		Admin admin = new Admin("admin@admin.cz", "bobek");
		admin.setTranslations(new HashSet<>());
		admin.setFriends(new HashSet<>());
		admin.setLoggedEvents(new HashSet<>());

		em.persist(admin);

		for (int i = 0; i < 10; i++) {

			LoggedEvent event = new LoggedEvent();
			event.setDescription("Translation desc. " + i);
			event.setAdmin(admin);
			em.persist(event);
			
			admin.getLoggedEvents().add(event);
		}
		em.persist(admin);
	}

}

package cz.fel.cvut.translationapp.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import cz.fel.cvut.translationapp.service.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepostory;

	private static User user;

	@BeforeClass
	public static void setup() {
		user = new User("john@dodo.cz", "password");
	}

	@Test
	public void testPersist() {
		User persistedUser = entityManager.persistAndFlush(user);
		assertThat(user.getEmail()).isEqualTo(persistedUser.getEmail());
	}

}

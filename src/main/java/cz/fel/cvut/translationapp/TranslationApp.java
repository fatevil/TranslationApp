package cz.fel.cvut.translationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import cz.fel.cvut.translationapp.api.DummyTranslation;
import cz.fel.cvut.translationapp.model.Admin;
import cz.fel.cvut.translationapp.model.LoggedEvent;
import cz.fel.cvut.translationapp.model.User;

/**
 * @author Marek Kozlovsky
 */
@SpringBootApplication
public class TranslationApp {

	public static void main(String[] args) {
		SpringApplication.run(TranslationApp.class, args);
	}

	@Configuration
	public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
		@Override
		public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
			config.exposeIdsFor(User.class, DummyTranslation.class, Admin.class, LoggedEvent.class);
		}
	}
}

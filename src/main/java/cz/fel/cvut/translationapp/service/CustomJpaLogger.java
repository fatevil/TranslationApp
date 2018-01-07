package cz.fel.cvut.translationapp.service;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.fel.cvut.translationapp.model.Admin;
import cz.fel.cvut.translationapp.model.LoggedEvent;
import cz.fel.cvut.translationapp.model.User;
import cz.fel.cvut.translationapp.security.Authentication;
import cz.fel.cvut.translationapp.service.repository.LoggedEventRepository;

@Component("myCustomJpaLogger")
public class CustomJpaLogger {

	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private LoggedEventRepository eventRepository;

	public boolean doLog(Long id) {
		System.out.println("DOING IT " + id);
		Authentication auth = beanFactory.getBean(Authentication.class);
		Admin currentUser = (Admin) auth.getCurrentUser();
		if (currentUser == null) {
			return false;
		}
		LoggedEvent event = new LoggedEvent();
		event.setAdmin(currentUser);
		event.setDescription("DELETE User with id:" + id);
		eventRepository.save(event);
		return true;
	}
}

package cz.fel.cvut.translationapp.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cz.fel.cvut.translationapp.model.LoggedEvent;
import cz.fel.cvut.translationapp.model.dto.LoggedEventDto;
import cz.fel.cvut.translationapp.service.repository.LoggedEventRepository;

@Controller
public class LoggedEventController {

	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private LoggedEventRepository eventRepository;

	@PersistenceContext
	private EntityManager em;

	@PostMapping("/eventsWithAdmins")
	public @ResponseBody List<LoggedEventDto> getAllWithAdmins() throws Exception {

		TypedQuery<LoggedEventDto> query = em.createNamedQuery("LoggedEvent.getAllWithAdmins", LoggedEventDto.class);
		List<LoggedEventDto> results = query.getResultList();

		Query query1 = em.createNativeQuery(
				"SELECT e.id, e.description,a.email, e.creation_date AS creationDate FROM logged_event e, user a  WHERE e.user_id = a.id",
				"LoggedEventDtoNativeMapping");

		// for (LoggedEventDto loggedEventDto : (List<LoggedEventDto>)
		// query1.getResultList()) {
		for (Object object : query1.getResultList()) {
			System.out.println(object);
		}

		// Authentication auth = beanFactory.getBean(Authentication.class);
		// User currentUser = auth.getCurrentUser();
		// if (currentUser == null || user == null) {
		// return null;
		// }
		// currentUser.getFriends().add(user);
		// User updatedCurrentUser = userRepository.save(currentUser);
		return results;
	}

}

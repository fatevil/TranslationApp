package cz.fel.cvut.translationapp.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import cz.fel.cvut.translationapp.model.Admin;
import cz.fel.cvut.translationapp.model.User;

@Service
public class LoginService {

	@PersistenceContext
	private EntityManager em;

	public User findUserByEmail(String email) {

		TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
		query.setParameter(1, email);

		List<User> users = query.getResultList();

		if (users.isEmpty()) {
			return null;
		}

		return users.get(0);
	}

	public Admin findAdminByEmail(String email) {

		TypedQuery<Admin> query = em.createNamedQuery("Admin.findByEmail", Admin.class);
		query.setParameter(1, email);

		List<Admin> admins = query.getResultList();

		if (admins.isEmpty()) {
			return null;
		}

		return admins.get(0);
	}

}

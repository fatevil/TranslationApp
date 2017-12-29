package cz.fel.cvut.translationapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.fel.cvut.translationapp.model.Admin;
import cz.fel.cvut.translationapp.model.User;
import cz.fel.cvut.translationapp.service.repository.AdminRepository;
import cz.fel.cvut.translationapp.service.repository.UserRepository;

@Repository
public class Authentication {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AdminRepository adminRepository;

	public User getCurrentUser() {
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains("ADMIN")) {
			Admin admin = adminRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
			return admin;
		} else {
			User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
			return user;
		}
	}
}

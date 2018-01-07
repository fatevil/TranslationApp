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
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getDetails());
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		System.out.println("Gonna do authorities");
		SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(System.out::println);
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());
		
		
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains("ROLE_ADMIN")) {
			Admin admin = adminRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
			return admin;
		} else {
			User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
			return user;
		}
	}
}

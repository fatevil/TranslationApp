package cz.fel.cvut.translationapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import cz.fel.cvut.translationapp.model.Admin;
import cz.fel.cvut.translationapp.service.repository.AdminRepository;
import cz.fel.cvut.translationapp.service.repository.UserRepository;

@Component
public class SpringDataJpaUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;
	private AdminRepository adminRepository;

	@Autowired
	public SpringDataJpaUserDetailsService(UserRepository userRepository, AdminRepository adminRepository) {
		this.userRepository = userRepository;
		this.adminRepository = adminRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminRepository.findByEmail(username);
		if (admin != null) {
			return new User(admin.getEmail(), admin.getPassword(),
					AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));
		}

		cz.fel.cvut.translationapp.model.User user = userRepository.findByEmail(username);
		if (user == null) {
			return null;
		}
		return new User(user.getEmail(), user.getPassword(),
				AuthorityUtils.commaSeparatedStringToAuthorityList("USER"));
	}

}

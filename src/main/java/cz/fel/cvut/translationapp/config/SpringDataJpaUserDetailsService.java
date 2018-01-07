package cz.fel.cvut.translationapp.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import cz.fel.cvut.translationapp.model.Admin;
import cz.fel.cvut.translationapp.service.LoginService;
import cz.fel.cvut.translationapp.service.repository.AdminRepository;
import cz.fel.cvut.translationapp.service.repository.UserRepository;

@Component
public class SpringDataJpaUserDetailsService implements UserDetailsService {

//	TRANSAKCE
	
	
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private LoginService loginService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = loginService.findAdminByEmail(username);
		
		if (admin != null) {
			System.out.println("ADMIN, USER ");
			return new User(admin.getEmail(), admin.getPassword(),
					AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN, ROLE_USER"));
		}

		cz.fel.cvut.translationapp.model.User user = loginService.findUserByEmail(username);
		if (user == null) {
			return null;
		}
		System.out.println("USER ");
		return new User(user.getEmail(), user.getPassword(),
				AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
	}

}

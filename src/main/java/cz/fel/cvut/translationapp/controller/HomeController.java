package cz.fel.cvut.translationapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cz.fel.cvut.translationapp.model.User;
import cz.fel.cvut.translationapp.service.repository.UserRepository;

/**
 * @author Marek Kozlovsky
 */
@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/current", method = RequestMethod.POST)
	public @ResponseBody User currentUser() {
		User user =userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		user.setFriends(null);
		user.setTranslations(null);
		return user;
	}

	@RequestMapping(value = "/*")
	public String index() {
		return "index";
	}

}

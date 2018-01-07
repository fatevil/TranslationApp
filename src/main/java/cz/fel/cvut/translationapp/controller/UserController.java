package cz.fel.cvut.translationapp.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cz.fel.cvut.translationapp.model.User;
import cz.fel.cvut.translationapp.model.dto.UserDto;
import cz.fel.cvut.translationapp.security.Authentication;
import cz.fel.cvut.translationapp.service.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/friends")
	public @ResponseBody UserDto create(@RequestParam("email") String email) throws Exception {
		User user = userRepository.findByEmail(email);
		Authentication auth = beanFactory.getBean(Authentication.class);
		User currentUser = auth.getCurrentUser();
		if (currentUser == null || user == null) {
			return null;
		}
		currentUser.getFriends().add(user);
		User updatedCurrentUser = userRepository.save(currentUser);
		return new UserDto(user);
	}

	@DeleteMapping("/friends")
	public @ResponseBody boolean delete(@RequestParam("email") String email) throws Exception {
		Authentication auth = beanFactory.getBean(Authentication.class);
		User currentUser = auth.getCurrentUser();
		if (currentUser == null) {
			return false;
		}
		int size = currentUser.getFriends().size();

		Set<User> friends = currentUser.getFriends().stream().filter(u -> {
			return !u.getEmail().trim().equals(email.trim());
		}).collect(Collectors.toSet());
		currentUser.setFriends(friends);
		userRepository.save(currentUser);
		return size != currentUser.getFriends().size();
	}

	@RequestMapping(value = "/current", method = RequestMethod.POST)
	public @ResponseBody UserDto currentUser() {
		Authentication auth = beanFactory.getBean(Authentication.class);
		return new UserDto(auth.getCurrentUser());
	}
}

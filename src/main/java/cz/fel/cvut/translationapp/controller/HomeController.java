package cz.fel.cvut.translationapp.controller;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cz.fel.cvut.translationapp.model.User;
import cz.fel.cvut.translationapp.security.Authentication;

/**
 * @author Marek Kozlovsky
 */
@Controller
public class HomeController {

	@Autowired
	private BeanFactory beanFactory;

	@RequestMapping(value = "/*")
	public String index() {
		return "index";
	}

}

package cz.fel.cvut.translationapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Marek Kozlovsky
 */
@Controller
public class HomeController {

	@RequestMapping(value = "/*")
	public String index() {
		return "index";
	}

}

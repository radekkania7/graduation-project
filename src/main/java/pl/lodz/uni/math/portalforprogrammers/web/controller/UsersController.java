package pl.lodz.uni.math.portalforprogrammers.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UsersController {

	@RequestMapping(value= {"/show/user/{id}"}, method = RequestMethod.GET)
	public String showUserProfil(@PathVariable String id, Model model) {
		return "user";
	}
}

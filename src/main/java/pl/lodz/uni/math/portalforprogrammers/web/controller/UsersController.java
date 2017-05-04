package pl.lodz.uni.math.portalforprogrammers.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.lodz.uni.math.portalforprogrammers.model.PortalUser;
import pl.lodz.uni.math.portalforprogrammers.service.UserService;

@Controller
public class UsersController {
	
	@Autowired
	UserService userService;

	@RequestMapping(value= {"/showuser/{id}"}, method = RequestMethod.GET)
	public String showUserProfil(@PathVariable String id, Model model) {
		PortalUser user = userService.findUserById(Integer.parseInt(id));
		if (user == null) {
			return "notfound";
		}
		model.addAttribute("user", user);
		return "user";
	}
}

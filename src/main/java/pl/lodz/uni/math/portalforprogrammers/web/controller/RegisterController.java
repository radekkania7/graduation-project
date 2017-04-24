package pl.lodz.uni.math.portalforprogrammers.web.controller;


import java.sql.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.lodz.uni.math.portalforprogrammers.model.PortalUser;
import pl.lodz.uni.math.portalforprogrammers.service.UserService;

@Controller
@RequestMapping({ "/register" })
public class RegisterController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new PortalUser());
		
		return "registerform";
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public String createNewUser(@Valid @ModelAttribute("user") PortalUser user, BindingResult result) {
		user.setRole("ROLE_USER");
		user.setEnabled(true);
		user.setDateOfBirth(new Date(918232));
		
		if (result.hasErrors()) {
			return "registerform";
		}
		
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			return "registerform";
		} 
		
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userService.save(user);
		return "home";
	}
	
}

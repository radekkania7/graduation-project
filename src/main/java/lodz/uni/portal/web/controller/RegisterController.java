package lodz.uni.portal.web.controller;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lodz.uni.portal.model.PortalUser;

@Controller
@RequestMapping({ "/register" })
public class RegisterController {

	@RequestMapping(method=RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new PortalUser());
		
		return "registerform";
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public String createNewUser(@Valid @ModelAttribute("user") PortalUser user, BindingResult result) {

		return "home";
	}
	
}

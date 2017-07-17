package lodz.uni.portal.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ShowUserProfileController {
	private static final String USER_PAGE = "user";
	
	@RequestMapping(value= {"/showuser/{id}"}, method = RequestMethod.GET)
	public String showUserProfil(@PathVariable String id, Model model) {
		
		return USER_PAGE;
	}
	
}

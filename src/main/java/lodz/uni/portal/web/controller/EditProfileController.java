package lodz.uni.portal.web.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lodz.uni.portal.service.UserService;


@Controller
@RequestMapping(value="edit")
public class EditProfileController {
	private static final Logger logger = Logger.getLogger(EditProfileController.class);
	
	private static final String EDIT_PAGE = "editProfile";
	private static final String EDIT_PAGE_REDIRECT = "redirect:/edit";
	private static final String SUCCESS_ADDED = "dodano sport";
	private static final String FAIL_ADDED = "nie udalo sie dodac sportu";
	private static final String SUBSCRIBED_SPORT = "sport jest juz dodany do twoich zainteresowan";
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getEditPage(RedirectAttributes model) {
		return EDIT_PAGE;
	}

}
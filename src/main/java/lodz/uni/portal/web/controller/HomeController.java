package lodz.uni.portal.web.controller;


import lodz.uni.portal.model.PortalUser;
import lodz.uni.portal.model.UserProfile;
import lodz.uni.portal.model.type.UserProfileType;
import lodz.uni.portal.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class HomeController {
	private static final Logger logger = Logger.getLogger(HomeController.class);

	private static final String REDIRECT_ADMIN_PAGE = "redirect:/panelAdmin";
	private static final String REDIRECT_USER_PAGE = "redirect:/homepage";
	private static final String FORBIDDEN = "brakDostepu";
	private static final String USER_PAGE = "user";

	private static final String ADMIN = UserProfileType.ADMIN.getType();
	private static final String USER = UserProfileType.USER.getType();

	@Autowired
	UserDetailsService portalUserDetailsService;

	@Autowired
	UserService userService;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String getDefaultPage(Principal principal) {
		PortalUser user = getLoggedInUser(principal);
		String userProfile = getUserProfileType(user);

		if (userProfile.equals(ADMIN)) {
			return REDIRECT_ADMIN_PAGE;
		}
		if (userProfile.equals(USER)) {
			return REDIRECT_USER_PAGE;
		}
		return FORBIDDEN;
	}

	private PortalUser getLoggedInUser(Principal principal) {
		String username = principal.getName();
		return userService.findByUsername(username);
	}

	private String getUserProfileType(PortalUser user) {
		UserProfile userProfile = user.getUserProfiles().get(0);
		return userProfile.getType();
	}

}

package lodz.uni.portal.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class UserEachSportController {
	
	private final String USER_SPORT_PAGE = "usersSport";
	
	@RequestMapping(value = "/usersport/{userId}/{sportId}", method=RequestMethod.GET)
	public String getUserSportPage(@PathVariable String userId,
								   @PathVariable String sportId, Model model)  {
		
		return USER_SPORT_PAGE;
	}
}

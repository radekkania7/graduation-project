package pl.lodz.uni.math.portalforprogrammers.web.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import pl.lodz.uni.math.portalforprogrammers.model.Event;
import pl.lodz.uni.math.portalforprogrammers.model.EventStatus;
import pl.lodz.uni.math.portalforprogrammers.model.PortalUser;
import pl.lodz.uni.math.portalforprogrammers.model.UserSport;
import pl.lodz.uni.math.portalforprogrammers.service.UserService;
import pl.lodz.uni.math.portalforprogrammers.service.UserSportService;
import pl.lodz.uni.math.portalforprogrammers.userhelper.UserHelper;


@Controller
@SessionAttributes({"nameofuser"})
public class HomeController {	
	
	private static final Logger logger = Logger.getLogger(HomeController.class);
		
	@Autowired
	private UserService userService;
	
	@ModelAttribute("nameofuser")
	public String nameOfLoggedInUser() {
		return UserHelper.getLoggedinUserName();
	}
	
	@RequestMapping(value = { "/homepage", "/" }, method=RequestMethod.GET)
	public String home(Model model) {	
		String username = UserHelper.getLoggedinUserName();
		PortalUser user = userService.findUserByUsername(username);
		Map<String, List<Event>> map = getEventsSortedByStatus(user.getUserEvents());
		
		model.addAttribute("user", user);
		model.addAttribute("history", map.get("history"));
		model.addAttribute("today", map.get("today"));
		model.addAttribute("upcoming", map.get("upcoming"));
		return "home";
	}
	
	private Map<String, List<Event>> getEventsSortedByStatus(List<Event> events) {
		Map<String, List<Event>> map = new HashMap<String, List<Event>>();
		List<Event> upcoming = new LinkedList<Event>();
		List<Event> today = new LinkedList<Event>();
		List<Event> history = new LinkedList<Event>();
		
		for (Event e : events) {
			if (e.getStatus().equals(EventStatus.NOT_ACTUAL.getValue())) {
				history.add(e);
			}
			if (e.getStatus().equals(EventStatus.UPCOMING_TODAY.getValue())) {
				today.add(e);
			}
			if (e.getStatus().equals(EventStatus.UPCOMING.getValue())) {
				upcoming.add(e);
			}
		}	
		map.put("today", today);
		map.put("upcoming", upcoming);
		map.put("history", history);
		return map;
	}
}

package pl.lodz.uni.math.portalforprogrammers.web.controller;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import pl.lodz.uni.math.portalforprogrammers.model.Event;
import pl.lodz.uni.math.portalforprogrammers.model.PortalUser;
import pl.lodz.uni.math.portalforprogrammers.model.UserSport;
import pl.lodz.uni.math.portalforprogrammers.service.EventService;
import pl.lodz.uni.math.portalforprogrammers.service.UserService;
import pl.lodz.uni.math.portalforprogrammers.service.UserSportService;
import pl.lodz.uni.math.portalforprogrammers.userhelper.UserHelper;

@Controller
@SessionAttributes({"nameofuser"})
public class UsersController {
	private static final Logger logger = Logger.getLogger(UsersController.class);
	
	@Autowired
	private UserSportService userSportService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	EventService eventService;
	
	@ModelAttribute("nameofuser")
	public String nameOfLoggedInUser() {
		return UserHelper.getLoggedinUserName();
	}

	@RequestMapping(value= {"/showuser/{id}"}, method = RequestMethod.GET)
	public String showUserProfil(@PathVariable String id, Model model) {
		PortalUser user = userService.findUserById(Integer.parseInt(id));
		if (user == null) {
			return "notfound";
		}
		model.addAttribute("user", user);
		return "user";
	}
	
	@RequestMapping(value = "/usersport/{userSportId}", method=RequestMethod.GET)
	public String getUserSportPage(@PathVariable String userSportId, Model model)  {
		UserSport userSport = userSportService.findById(Integer.valueOf(userSportId));
		List<Event> userEvents = userSport.getUser().getUserEvents();
		//Map<Event, List<Game>> thisSportEvents = getEventsBySport(userSport.getSport().getId(), userEvents);
		List<Event> thisSportEvents = getEventsBySport(userSport.getSport().getId(), userEvents);
		model.addAttribute("usersport", userSport);
		model.addAttribute("events", thisSportEvents);
		return "userssport";
	}

	private List<Event> getEventsBySport(Integer sportId, List<Event> events) {
		List<Event> ev = new LinkedList<Event>();
		for (Event e : events) {
			if (e.getId() == sportId) {
				ev.add(e);
			}
		}
		return ev;
	}
}

package pl.lodz.uni.math.portalforprogrammers.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import pl.lodz.uni.math.portalforprogrammers.model.Event;
import pl.lodz.uni.math.portalforprogrammers.model.PortalUser;
import pl.lodz.uni.math.portalforprogrammers.service.EventService;
import pl.lodz.uni.math.portalforprogrammers.service.SportService;
import pl.lodz.uni.math.portalforprogrammers.service.TownService;
import pl.lodz.uni.math.portalforprogrammers.service.UserService;
import pl.lodz.uni.math.portalforprogrammers.userhelper.LoggedinUserHelper;

@Controller
@SessionAttributes("nameofuser")
public class EventsController {
	
	private static final Logger logger = Logger.getLogger(EventsController.class);
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private SportService sportService;
	
	@Autowired
	private TownService townServive;
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("nameofuser")
	public String nameOfLoggedInUser() {
		return LoggedinUserHelper.getLoggedinUserName();
	}
	
	@RequestMapping(value = "/createnewevent", method = RequestMethod.GET)
	public String createNewEvent(Model model) {
		model.addAttribute("event", new Event());
		model.addAttribute("towns",townServive.findAllTown());
		model.addAttribute("sports",sportService.findAllSports());
		return "newevent";
	}
			
	@RequestMapping(value = "/createnewevent", method = RequestMethod.POST)
	public String processFormAlternative(@Valid Event event, BindingResult result, 
			Model model, HttpServletRequest request) {
		
		String stringDate = null;
		java.sql.Date eventDate = null;
		
		if (result.hasErrors()) {
			return "newevent";
		}
		
		stringDate = request.getParameter("eventTime");
		if (stringDate == null) {
			return "newevent";
		}
				
		eventDate = getDate(stringDate);
		
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date dateNow = new java.sql.Date(utilDate.getTime());
		
		if (!(eventDate.after(dateNow)) && !(eventDate.equals(dateNow))) {
			return "newevent";
		}
		
		logger.debug(dateNow.toString());
		
		event.setEventDate(eventDate);
		eventService.save(event);
		model.addAttribute("event", event);
		return "redirect:/eventinfo/" + event.getId();
	}
	
	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public String getEventList(Model model) {
		List<Event> actualEvents = eventService.findActualEvents();
		model.addAttribute("events", actualEvents);
		return "eventslist";
	}
			
	/**
	 * Metohod converts String to java.sql.Date;
	 * @param dateText
	 * @return java.sql.Date;
	 */
	private java.sql.Date getDate(String dateText) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-mm");
		java.util.Date date;
		java.sql.Date sqlDate;
		try {
			 date = format.parse(dateText);
			 sqlDate = new java.sql.Date(date.getTime());
			 return sqlDate;
		} catch (ParseException e) {
			logger.error("Date conversion failed", e);
		}
		return null;
	}
	
	@RequestMapping(value = "/eventinfo/{id}", method = RequestMethod.GET)
	public String showEventInfo(@PathVariable String id, Model model) {
		Event event = eventService.findEventById(Integer.parseInt(id));
		
		if (event == null) {
			return "notfound";
		}
		
		logger.debug(event.toString());
		model.addAttribute("event", event);
		return "event";
	}
	
	//dolaczanie do wydarzenia
	@RequestMapping(value = "/eventinfo/{id}", method = RequestMethod.POST)
	public String joinEvent(@PathVariable String id, Model model) {
		Event event = eventService.findEventById(Integer.valueOf(id));
		String username = LoggedinUserHelper.getLoggedinUserName();
		PortalUser user = userService.findUserByUsername(username);
		List<PortalUser> users = event.getEventUsers();
		
		if (users.contains(user)) {
			return "event";
		}
		
		users.add(user);
		event.setEventUsers(users);
		eventService.update(event);
		model.addAttribute("event", event);
		return "event";
	}

}

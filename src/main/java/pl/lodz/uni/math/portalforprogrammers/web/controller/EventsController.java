package pl.lodz.uni.math.portalforprogrammers.web.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import pl.lodz.uni.math.portalforprogrammers.model.Event;
import pl.lodz.uni.math.portalforprogrammers.model.EventStatus;
import pl.lodz.uni.math.portalforprogrammers.model.Game;
import pl.lodz.uni.math.portalforprogrammers.model.PortalUser;
import pl.lodz.uni.math.portalforprogrammers.model.Sport;
import pl.lodz.uni.math.portalforprogrammers.model.Town;
import pl.lodz.uni.math.portalforprogrammers.service.EventService;
import pl.lodz.uni.math.portalforprogrammers.service.GameService;
import pl.lodz.uni.math.portalforprogrammers.service.SportService;
import pl.lodz.uni.math.portalforprogrammers.service.TownService;
import pl.lodz.uni.math.portalforprogrammers.service.UserService;
import pl.lodz.uni.math.portalforprogrammers.userhelper.UserHelper;

@Controller
@SessionAttributes({"nameofuser", "listOfTowns", "listOfSports"})
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
	
	@Autowired
	private GameService gameService;

	@ModelAttribute("nameofuser")
	public String nameOfLoggedInUser() {
		return UserHelper.getLoggedinUserName();
	}
	
	@ModelAttribute("listOfSports")
	public List<Sport> getSports() {
		return sportService.findAllSports();
	}
	
	@ModelAttribute("listOfTowns")
	public List<Town> getTowns() {
		return townServive.findAllTown();
	}
	
	@RequestMapping(value = "/createnewevent", method = RequestMethod.GET)
	public String createNewEvent(Model model) {
		model.addAttribute("event", new Event());
		return "newevent";
	}
			
	@RequestMapping(value = "/createnewevent", method = RequestMethod.POST)
	public String processFormAlternative(@Valid Event event, BindingResult result, Model model, HttpServletRequest request) {
		event.setStatus(1);
		if (result.hasErrors()) {
			logger.debug(result.getFieldErrors().toString());
			return "newevent";
		} 
		
		eventService.save(event);
		model.addAttribute("event", event);
		return "redirect:/eventinfo/" + event.getId();
	}
	
	@RequestMapping(value = {"/events" , "findevents" }, method = RequestMethod.GET)
	public String getEventList(Model model) {
		List<Event> actualEvents = eventService.findEventsByStatus(EventStatus.UPCOMING);		
		model.addAttribute("towns",townServive.findAllTown());
		model.addAttribute("sports",sportService.findAllSports());
		model.addAttribute("events", actualEvents);
		
		return "eventslist"; 
	}
	
	@RequestMapping(value = "/findevents", method = RequestMethod.POST)
	public String sheareEvents(HttpServletRequest request, @RequestParam("townString") String town, 
			@RequestParam("sportString") String sport, Model model) {		
		List<Event> eventsFound = eventService.findActualEventsByParam(town, sport);
		logger.debug("found " + eventsFound.size() + " events");
		logger.debug(town + " " + sport);
		model.addAttribute("events", eventsFound);
		return "eventslist";
	}
			
	//DOROBIC PRZESYLANIE TYCH ELEMENTOW
	//flash attribute
	@RequestMapping(value = "/eventinfo/{id}", method = RequestMethod.GET)
	public String showEventInfo(@PathVariable String id, Model model) {
		Event event = null;
		if (!id.equals("")) {
			event = eventService.findEventById(Integer.parseInt(id));
		}
		if (event == null) {
			return "notfound";
		}
		
		boolean flag = event.getEventUsers().contains(userService.findUserByUsername(nameOfLoggedInUser()));
		
		if (event.getStatus() == 0) {
			if (event.getEventSport().isTeamSport() == false) {
				model.addAttribute("game", new Game());
			}
			//model.addAttribute("game", new TeamGame());
		}
		
		model.addAttribute("event", event);
		model.addAttribute("flag", flag);
		return "event";
	}
	
	@RequestMapping(value = "/eventinfo/{id}", method = RequestMethod.POST)
	public String joinEvent(@PathVariable String id, Model model) {
		Event event = eventService.findEventById(Integer.valueOf(id));
		String username = UserHelper.getLoggedinUserName();
		PortalUser user = userService.findUserByUsername(username);
		List<PortalUser> users = event.getEventUsers();
		
		if (users.contains(user)) {
			return "redirect:/eventinfo/" + id;
		}
		
		int limit = event.getPlayersLimit();
		int amountOfPrincipants = users.size();
		if (amountOfPrincipants+1 > limit) {
			model.addAttribute("info", "brak wolnych miejsc");
			return "redirect:/eventinfo/" + id;
		} 
		model.addAttribute("amountOfFreePlace", limit - amountOfPrincipants + 1);
		users.add(user);
		event.setEventUsers(users);
		eventService.update(event);
		model.addAttribute("event", event);
		
		boolean flag = event.getEventUsers().contains(userService.findUserByUsername(nameOfLoggedInUser()));
		model.addAttribute("flag", flag);
		return "redirect:/eventinfo/" + id;
	}
	
	@RequestMapping(value = "/eventinfo/{id}/unjoin", method = RequestMethod.POST)
	public String unJoinEvent(@PathVariable String id, Model model) {
		Event event = eventService.findEventById(Integer.valueOf(id));
		String username = UserHelper.getLoggedinUserName();
		PortalUser user = userService.findUserByUsername(username);
		List<PortalUser> users = event.getEventUsers();
		
		if (users.contains(user)) {
			users.remove(user);
		}
		
		eventService.update(event);
		
		boolean flag = event
				.getEventUsers()
				.contains(userService.findUserByUsername(
						UserHelper.getLoggedinUserName()));
		model.addAttribute("flag", flag);
		model.addAttribute(event);
		return "redirect:/eventinfo/" + id;
	}
	
	@RequestMapping(value = "eventinfo/{id}/addplay", method = RequestMethod.POST)
	public String addPlay(HttpServletRequest request,
			@RequestParam(value ="opponentName", required = false) String opponentName, 
			@RequestParam(value = "descStr", required = false) String desc,
			@RequestParam(value = "hostResultStr", required = false) String hostResult,
			@RequestParam(value = "guestResultStr", required = false) String guestResult,
			@PathVariable String id, Model model) {
		
		logger.debug(opponentName);
		Event event = eventService.findEventById(Integer.valueOf(id));
		String username = UserHelper.getLoggedinUserName();
		PortalUser user = userService.findUserByUsername(username);
		PortalUser guestUser = userService.findUserByUsername(opponentName);
		
		//walidacja
		Game game = new Game();
		game.setGuestUser(guestUser);
		game.setHostUser(user);
		game.setEvent(event);
		game.setHostResult(Integer.valueOf(hostResult));
		game.setGuestResult(Integer.valueOf(guestResult));
		game.setDesc(desc);
		gameService.save(game);
		logger.debug("Saved: " + game.toString());
		return "redirect:/eventinfo/" + id;
	}

	@RequestMapping(value = "/eventinfo/{eventId}/game/{gameId}/confirm", method = RequestMethod.POST)
	public String confirmGame(HttpServletRequest req,
			@PathVariable String eventId,
			@PathVariable String gameId) {
			Game game = gameService.findGameById(Integer.valueOf(gameId));
			game.setConfirm(true);
		return "redirect:/eventinfo/" + eventId;
	}

}

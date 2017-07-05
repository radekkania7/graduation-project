package pl.lodz.uni.math.portalforprogrammers.web.controller;

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
import pl.lodz.uni.math.portalforprogrammers.model.PortalUser;
import pl.lodz.uni.math.portalforprogrammers.model.Sport;
import pl.lodz.uni.math.portalforprogrammers.service.EventService;
import pl.lodz.uni.math.portalforprogrammers.service.SportService;
import pl.lodz.uni.math.portalforprogrammers.service.UserService;
import pl.lodz.uni.math.portalforprogrammers.userhelper.UserHelper;
import pl.lodz.uni.math.portalforprogrammers.utils.UserStatistic;

@Controller
@SessionAttributes({"nameofuser"})
public class UsersController {
	private static final Logger logger = Logger.getLogger(UsersController.class);
	
	private final String NOT_FOUND_PAGE = "notfoud";
	private final String USER_SPORT_PAGE = "userssport";
	
	@Autowired
	UserService userService;
	
	@Autowired
	EventService eventService;
	
	@Autowired
	SportService sportService;
	
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
	
	@RequestMapping(value = "/usersport/{userId}/{sportId}", method=RequestMethod.GET)
	public String getUserSportPage(@PathVariable String userId,
								   @PathVariable String sportId, Model model)  {
		
		Sport sport = sportService.findSportById(Integer.parseInt(sportId));
		PortalUser user = userService.findUserById(Integer.parseInt(userId));
		
		if (user == null || sport == null) {
			return NOT_FOUND_PAGE;
		}
		
		List<Event> userSportEvents = getUserEventsBySport(user, sport);
		
		model.addAttribute("sport", sport);
		model.addAttribute("user", user);
		model.addAttribute("events", userSportEvents);
		
		if (sport.isTeamSport() == true) {
			UserStatistic statistic = new UserStatistic(user);
			Map<Event, Double> eventsWithAvg = statistic.getMapAvgPerEeachEventAtSport(sport);
			Double avarage = statistic.getAvgBySport(sport);
			model.addAttribute("avarage", avarage);
			model.addAttribute("eventsWithAvg", eventsWithAvg);
			model.addAttribute("markCountPerEvent", statistic.getMarkCount());
			model.addAttribute("marksCount", statistic.getMarkCount());
		}
		
		return USER_SPORT_PAGE;
	}
	
	/*
	 * Function gets events with user participations at chosen sport
	 */
	private List<Event> getUserEventsBySport(PortalUser user, Sport sport) {
		List<Event> userEvents = user.getUserEvents();
		List<Event> resultEvents = new LinkedList<>();
		for (Event event : userEvents) {
			if (event.getEventSport().equals(sport)) {
				resultEvents.add(event);
			}
		}
		logger.debug("count of founded events =" + resultEvents.size());
		return resultEvents;
	}
	
			
	/*
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFileHandler(@RequestParam("file") MultipartFile file, Model model) {
		String info = null;
		String username = UserHelper.getLoggedinUserName();

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + username);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.debug("Server File Location=" + serverFile.getAbsolutePath());
				info = "Twoje zdjęcie zostało dodane";
			} catch (Exception e) {
				info = "Blad podczas wczytywania";
				logger.debug("upload filed", e);
			}
		} else {
			info = "Pusty plik";
		}
		model.addAttribute("info", info);
		return "editprofile";
	}
	*/
	
	/*
	@RequestMapping(value="/userphoto", method=RequestMethod.GET)
	public void showImage(HttpServletResponse response) throws Exception {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		
		try {
			BufferedImage image = FileUtils.getImage(USERNAME);
			ImageIO.write(image, "jpeg", stream);
		} catch (IllegalArgumentException e) {
			logger.debug(e);
		} catch (IOException e) {
			logger.debug(e);
		}
		
		byte[] imgByte = stream.toByteArray();
		
		response.setHeader("Cache-Control", "no-store");
	    response.setHeader("Pragma", "no-cache");
	    response.setDateHeader("Expires", 0);
	    response.setContentType("image/jpeg");
	    ServletOutputStream responseOutputStream = response.getOutputStream();
	    responseOutputStream.write(imgByte);
	    responseOutputStream.flush();
	    responseOutputStream.close();
	}
	*/
}

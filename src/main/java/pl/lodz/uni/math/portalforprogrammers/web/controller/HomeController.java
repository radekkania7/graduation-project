package pl.lodz.uni.math.portalforprogrammers.web.controller;


import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pl.lodz.uni.math.portalforprogrammers.model.Event;
import pl.lodz.uni.math.portalforprogrammers.model.PortalUser;
import pl.lodz.uni.math.portalforprogrammers.service.UserService;
import pl.lodz.uni.math.portalforprogrammers.userhelper.LoggedinUserHelper;


@Controller
public class HomeController {	

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = { "/homepage", "/" }, method=RequestMethod.GET)
	public String home(Model model) {	
		String username = LoggedinUserHelper.getLoggedinUserName();
		PortalUser user = userService.findUserByUsername(username);		
		List<Event> actual = new LinkedList<Event>();
		List<Event> history = new LinkedList<Event>();
		
		for (Event e : user.getUserEvents()) {
			if (e.isDone() == true) {
				history.add(e);
			} else {
				actual.add(e);
			}
		}
		
		model.addAttribute("username", username);
		model.addAttribute("user",user);
		model.addAttribute("history", history);
		model.addAttribute("actual", actual);
		return "home";
	}
	
	@RequestMapping(value = "/upload", method=RequestMethod.POST)
	public String addProfileImage(@RequestParam("file") MultipartFile file) {
		//uploader.uploadProfileImage(file);
		return "home";
	}
}

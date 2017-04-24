package pl.lodz.uni.math.portalforprogrammers.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pl.lodz.uni.math.portalforprogrammers.model.PortalUser;
import pl.lodz.uni.math.portalforprogrammers.service.UserService;
import pl.lodz.uni.math.portalforprogrammers.userhelper.LoggedinUserHelper;


@Controller
public class HomeController {	

	@Autowired
	UserService userService;
	
	@RequestMapping(value = { "/homepage", "/" }, method=RequestMethod.GET)
	public String home(Model model) {	
		String username = LoggedinUserHelper.getLoggedinUserName();
		PortalUser user = null;
		
		model.addAttribute("username", username);
		model.addAttribute("user",user);
		
		return "home";
	}
	
	@RequestMapping(value = "/upload", method=RequestMethod.POST)
	public String addProfileImage(@RequestParam("file") MultipartFile file) {
		//uploader.uploadProfileImage(file);
		return "home";
	}
}

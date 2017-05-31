package pl.lodz.uni.math.portalforprogrammers.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.lodz.uni.math.portalforprogrammers.model.Sport;
import pl.lodz.uni.math.portalforprogrammers.model.Town;
import pl.lodz.uni.math.portalforprogrammers.service.SportService;
import pl.lodz.uni.math.portalforprogrammers.service.TownService;

@Controller
public class AdminController {
	private static final Logger logger = Logger.getLogger(AdminController.class);
	
	@Autowired
	private TownService townServive;
	
	@Autowired
	private SportService sportService;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String getForm(Model model) {
		model.addAttribute("sport", new Sport());
		return "test";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public String processAddTown(Sport sport, BindingResult result) {
		
		if (result.hasErrors()) {
			logger.debug("failed");
		}
		
		//townServive.save(town);
		sportService.save(sport);
		logger.debug("added");
		return "test";
	}
}

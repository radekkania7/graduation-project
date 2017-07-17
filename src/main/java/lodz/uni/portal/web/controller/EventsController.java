package lodz.uni.portal.web.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class EventsController {
	private static final Logger logger = Logger.getLogger(EventsController.class);
	
	private static final String EVENTS_LIST = "eventsList";

	@RequestMapping(value = { "/events" }, method = RequestMethod.GET)
	public String getEventList(Model model) {
	
		return EVENTS_LIST; 
	}
	
	@RequestMapping(value = { "/events" }, method = RequestMethod.POST)
	public String sheareEvents(HttpServletRequest request, @RequestParam("townString") String town, 
			@RequestParam("sportString") String sport, Model model) {		
			
		return EVENTS_LIST;
	}
}

package lodz.uni.portal.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewEventController {
	private static final String NEW_EVENT_PAGE = "newEvent";
	
	@RequestMapping(value = { "createNewEvent" })
	public String showFormForNewEvent() {
		return NEW_EVENT_PAGE;
	}
}

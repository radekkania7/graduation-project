package lodz.uni.portal.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JoinAndUnJoinEventController {
	private static final String EACH_EVENT_PAGE_REDIRECT_BASE = "redirect:/eventInfo/";
	
	@RequestMapping(value = { "/eventinfo/{eventId}/join" }, method = RequestMethod.POST)
	public String joinEvent(@PathVariable String eventId, Model model) {
		return EACH_EVENT_PAGE_REDIRECT_BASE + eventId;
	}
	
	@RequestMapping(value = { "/eventInfo/{eventId}/unjoin" }, method = RequestMethod.POST)
	public String unJoinEvent(@PathVariable String eventId, Model model) {
		return EACH_EVENT_PAGE_REDIRECT_BASE + eventId;
	}
		
}

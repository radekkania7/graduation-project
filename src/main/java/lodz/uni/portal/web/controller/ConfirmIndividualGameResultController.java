package lodz.uni.portal.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ConfirmIndividualGameResultController {
	private static final String EACH_EVENT_PAGE_REDIRECT_BASE = "redirect:/eventInfo/";
	
	@RequestMapping(value = "/eventInfo/{eventId}/game/{gameId}/confirm", method = RequestMethod.POST)
	public String confirmGame(HttpServletRequest req,
			@PathVariable String eventId,
			@PathVariable String gameId) {
		
		return EACH_EVENT_PAGE_REDIRECT_BASE + eventId;
	}

}

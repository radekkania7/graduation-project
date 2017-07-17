package lodz.uni.portal.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddIndividualGameResultToEvent {
	private static final String EACH_EVENT_PAGE_REDIRECT_BASE = "redirect:/eventInfo/";
	
	@RequestMapping(value = "eventInfo/{eventId}/addResult", method = RequestMethod.POST)
	public String addPlay(HttpServletRequest request,
			@RequestParam(value ="opponentName", required = false) String opponentName, 
			@RequestParam(value = "descStr", required = false) String desc,
			@RequestParam(value = "hostResultStr", required = false) String hostResult,
			@RequestParam(value = "guestResultStr", required = false) String guestResult,
			@PathVariable String id, Model model) {
		
		return EACH_EVENT_PAGE_REDIRECT_BASE + id;
	}
}

package lodz.uni.portal.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddUserMarkAfterDoneEventController {
	private static final String EACH_EVENT_PAGE_REDIRECT_BASE = "redirect:/eventInfo/";
	
	@RequestMapping(value = "/eventInfo/{eventId}/addmark", method = RequestMethod.POST)
	public String addMark(HttpServletRequest request, Model model,
			@PathVariable String eventId,
			@RequestParam String opponentName,
			@RequestParam Integer markValue) {

		return EACH_EVENT_PAGE_REDIRECT_BASE + eventId;
	}
}

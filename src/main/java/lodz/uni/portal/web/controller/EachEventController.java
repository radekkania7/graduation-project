package lodz.uni.portal.web.controller;

import lodz.uni.portal.model.Event;
import lodz.uni.portal.model.EventStatus;
import lodz.uni.portal.model.PortalUser;
import lodz.uni.portal.model.type.EventStatusType;
import lodz.uni.portal.service.EachEventService;
import lodz.uni.portal.service.LoggedInUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;

@Controller
public class EachEventController {
	private static final String EVENT_PAGE = "event";
	private static final String REDIRECT_EVENT_INFO_PAGE_BASE = "redirect:/eventInfo/";
	private static final String NOT_FOUND = "notFound";

	private static final String NO_PLACES = "Wszytkie miejsca sa zajete";
	private static final String LEAVED = "Opusciles wydarzenie";
	private static final String JOINED = "Dolaczyles do wyadarzenia";

	@Autowired
	EachEventService eachEventService;

	@Autowired
	LoggedInUserService loggedInUserService;
	
	@RequestMapping(value = { "/eventInfo/{eventId}" }, method = RequestMethod.GET)
	public String showEvent(@PathVariable String eventId, Model model) {
		Event event = eachEventService.getEventById(Integer.parseInt(eventId));
		PortalUser user = loggedInUserService.getLoggedInUser();

		if (event == null) {
			return NOT_FOUND;
		}

		if (eachEventService.isUserParticipate(user, event)) {
			model.addAttribute("bttnValue", "OPUSC WYDARZENIE");
		} else {
			model.addAttribute("bttnValue", "WEZ UDZIAL");
		}
		model.addAttribute("event", event);
		model.addAttribute("participants", event.getEventUsers());
		model.addAttribute("status", event.getStatus().getType());
		model.addAttribute("isParticipant", eachEventService.isUserParticipate(user, event));
		return EVENT_PAGE;
	}

	@RequestMapping(value =  "/eventInfo/{eventId}/join" , method = RequestMethod.POST)
	public String joinEvent(@PathVariable String eventId, RedirectAttributes model) {
		Event event = eachEventService.getEventById(Integer.parseInt(eventId));
		PortalUser user = loggedInUserService.getLoggedInUser();

		if (event == null) {
			return NOT_FOUND;
		}

		if (!eachEventService.isFreePlace(event)) {
			model.addFlashAttribute("joinInfo", NO_PLACES);
			return REDIRECT_EVENT_INFO_PAGE_BASE + Integer.valueOf(eventId);
		}

		if (eachEventService.isUserParticipate(user, event) == true){
			eachEventService.removeUserFromEvenParticipants(user, event);
			model.addFlashAttribute("joinInfo", LEAVED);
		} else {
			eachEventService.addUserIntoEventParticipants(user, event);
			model.addFlashAttribute("joinInfo", JOINED);
		}

		return REDIRECT_EVENT_INFO_PAGE_BASE + Integer.valueOf(eventId);
	}
	
}

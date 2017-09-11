package lodz.uni.portal.web.controller;



import lodz.uni.portal.form.FindEventForm;
import lodz.uni.portal.model.Event;
import lodz.uni.portal.service.EachEventService;
import lodz.uni.portal.service.EventsService;
import lodz.uni.portal.service.NewEventService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(value = { "/events" })
public class EventsController {
	private static final Logger logger = Logger.getLogger(EventsController.class);

	private static final String EVENTS_LIST = "eventsList";
	private static final String REDIRECT_EVENT_LIST = "redirect:/events";

	@Autowired
	NewEventService newEventService;

	@Autowired
	EventsService eventsService;

	@Autowired
	EachEventService eachEventService;

	@RequestMapping(method = RequestMethod.GET)
	public String getEventList(Model model) {
		model.addAttribute("sportNames", newEventService.getSportNames());
		model.addAttribute("findEventForm", new FindEventForm());
		model.addAttribute("loggedInUserName", eachEventService.getLoggedInUser().getNickname());
		return EVENTS_LIST; 
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String shearEvents(@Valid @ModelAttribute("findEventForm") FindEventForm form,
							  BindingResult result,
							  Model model) {

		if (result.hasErrors()) {
			model.addAttribute("sportNames", newEventService.getSportNames());
			model.addAttribute("loggedInUserName", eachEventService.getLoggedInUser().getNickname());
			return EVENTS_LIST;
		}

		List<Event> foundedEvents = getFoundedEvents(form);
		if (foundedEvents.size() > 0) {
			model.addAttribute("foundedEvents", foundedEvents);
		}

		model.addAttribute("loggedInUserName", eachEventService.getLoggedInUser().getNickname());
		model.addAttribute("sportNames", newEventService.getSportNames());
		return EVENTS_LIST;
	}

	private List<Event> getFoundedEvents(FindEventForm form) {
		return eventsService.findEventsByParams(form);
	}

}

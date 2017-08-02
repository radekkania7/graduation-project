package lodz.uni.portal.web.controller;



import lodz.uni.portal.form.FindEventForm;
import lodz.uni.portal.service.NewEventService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
@RequestMapping(value = { "/events" })
public class EventsController {
	private static final Logger logger = Logger.getLogger(EventsController.class);

	private static final String EVENTS_LIST = "eventsList";

	@Autowired
	NewEventService newEventService;

	@RequestMapping(method = RequestMethod.GET)
	public String getEventList(Model model) {
		model.addAttribute("sportNames", newEventService.getSportNames());
		model.addAttribute("findEventForm", new FindEventForm());
		return EVENTS_LIST; 
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String shearEvents(@Valid @ModelAttribute("findEventForm") FindEventForm form,
							  BindingResult result,
							  Model model) {

		if (result.hasErrors()) {
			model.addAttribute("sportNames", newEventService.getSportNames());
			return EVENTS_LIST;
		}

		return EVENTS_LIST;
	}


}

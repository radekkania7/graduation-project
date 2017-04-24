package pl.lodz.uni.math.portalforprogrammers.web.controller;

import java.sql.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.lodz.uni.math.portalforprogrammers.model.Event;
import pl.lodz.uni.math.portalforprogrammers.service.EventService;
import pl.lodz.uni.math.portalforprogrammers.service.SportService;
import pl.lodz.uni.math.portalforprogrammers.service.TownService;

@Controller
public class EventsController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private SportService sportService;
	
	@Autowired
	private TownService townServive;
	
	@RequestMapping(value = "/createnewevent", method = RequestMethod.GET)
	public String createNewEvent(Model model) {
		model.addAttribute("event", new Event());
		model.addAttribute("towns",townServive.findAllTown());
		model.addAttribute("sports",sportService.findAllSports());
		return "newevent";
	}
	
	@RequestMapping(value = "/createnewevent", method = RequestMethod.POST)
	public String proccessForm(@Valid Event event, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			return "newevent";
		}
		
		event.setEventDate(new Date(19248327));
		eventService.save(event);
		model.addAttribute("event", event);
		return "redirect:/eventinfo/" + event.getId();
	}
	
	@RequestMapping(value = "/eventinfo/{id}", method = RequestMethod.GET)
	public String showEventInfo(@PathVariable String id, Model model) {
		Event event = eventService.findEventById(Integer.parseInt(id));
		model.addAttribute("event", event);
		return "event";
	}

}

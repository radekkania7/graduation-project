package lodz.uni.portal.web.controller;

import lodz.uni.portal.form.SingleGameForm;
import lodz.uni.portal.form.TeamGameForm;
import lodz.uni.portal.model.*;
import lodz.uni.portal.model.type.EventStatusType;
import lodz.uni.portal.service.EachEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EachEventController {
	private static final String EVENT_PAGE = "event";
	private static final String REDIRECT_EVENT_INFO_PAGE_BASE = "redirect:/eventInfo/";
	private static final String NOT_FOUND = "notFound";

	private static final String NO_PLACES = "Wszytkie miejsca sa zajete";
	private static final String LEAVED = "Opusciles wydarzenie";
	private static final String JOINED = "Dolaczyles do wyadarzenia";
	private static final String GAME_CREATED = "Dodano nową rozgrywkę";
	private static final String MARK_ADDED_INFO = "Dodano ocenę";
	private static final String USER_IS_EVALUATED_INFO = "Ten uzytkownik zostal juz przez Ciebie\n oceniony w ramach tego wydarzenia.\n Ocen pozostalych!";

	private Event globalEvent;

	@Autowired
	EachEventService eachEventService;

	@RequestMapping(value = { "/eventInfo/{eventId}" }, method = RequestMethod.GET)
	public String showEvent(@PathVariable String eventId, Model model) {
		Event event = eachEventService.getEventById(Integer.parseInt(eventId));
		this.globalEvent = event;

		if (eachEventService.getLoggedInUser() == null) {
			return "login";
		}

		if (event == null) {
			return NOT_FOUND;
		}

		String eventStatus = globalEvent.getStatus().getType();
		model.addAttribute("loggedInUser", eachEventService.getLoggedInUser().getNickname());

		if (EventStatusType.CREATED.getType().equals(eventStatus)) {
			model.addAttribute("participants", globalEvent.getEventUsers());
			model.addAttribute("freePlaces", eachEventService.getFreePlaces(globalEvent));
			prepareButtonValue(model);
		}

		if (EventStatusType.DURING.getType().equals(eventStatus)) {
			model.addAttribute("isParticipate", eachEventService.isUserParticipate(globalEvent));
			model.addAttribute("participantsNames", eachEventService.getParticipantsNamesWithoutLoggedInUser(globalEvent));
		}

		if (EventStatusType.AFTER.getType().equals(eventStatus)) {
			prepareAttributeForAfterEvent(model);
		}

		if (EventStatusType.CLOSED.getType().equals(eventStatus)) {
			prepareAttributeForAfterEvent(model);
		}

		prepareOtherAttributes(model);
		return EVENT_PAGE;
	}

	private void prepareAttributeForAfterEvent(Model model) {
		if (globalEvent.getEventSport().isTeamSport()) {
			model.addAttribute("teamGameForm", new TeamGameForm());
			model.addAttribute("userWithMarksAvg", eachEventService.getMapUsersAvgByEvents(globalEvent));
			model.addAttribute("loggedInUserAvg", eachEventService.getLoggedInUserAvgByEvent(globalEvent));
		} else {
			model.addAttribute("singleGameForm", new SingleGameForm());
		}
		model.addAttribute("participantsNames", eachEventService.getParticipantsNamesWithoutLoggedInUser(globalEvent));
		model.addAttribute("isParticipate", eachEventService.isUserParticipate(globalEvent));
	}

	private void prepareButtonValue(Model model) {
		if (eachEventService.isUserParticipate(globalEvent)) {
			model.addAttribute("bttnValue", "OPUSC WYDARZENIE");
		} else {
			model.addAttribute("bttnValue", "WEZ UDZIAL");
		}
	}

	private void prepareOtherAttributes(Model model) {
		model.addAttribute("event", globalEvent);
		model.addAttribute("participants", globalEvent.getEventUsers());
		model.addAttribute("status", globalEvent.getStatus().getType());
		model.addAttribute("isParticipant", eachEventService.isUserParticipate(globalEvent));
		model.addAttribute("loggedInUserName", eachEventService.getLoggedInUser().getNickname());
		model.addAttribute("sportName", globalEvent.getEventSport().getName());
		model.addAttribute("sportIconPath", globalEvent.getEventSport().getSportIconPath());
	}

	@RequestMapping(value =  "/eventInfo/{eventId}/join" , method = RequestMethod.POST)
	public String joinEvent(@PathVariable String eventId, RedirectAttributes model) {
		Event event = eachEventService.getEventById(Integer.parseInt(eventId));

		if (event == null) {
			return NOT_FOUND;
		}

		if (eachEventService.isUserParticipate(event)){
			eachEventService.removeUserFromEvenParticipants(event);
			model.addFlashAttribute("joinInfo", LEAVED);
		} else {
			if (!eachEventService.isFreePlace(event)) {
				model.addFlashAttribute("joinInfo", NO_PLACES);
				return REDIRECT_EVENT_INFO_PAGE_BASE + Integer.valueOf(eventId);
			}
			eachEventService.addUserIntoEventParticipants(event);
			model.addFlashAttribute("joinInfo", JOINED);
		}

		return REDIRECT_EVENT_INFO_PAGE_BASE + Integer.valueOf(eventId);
	}


	@RequestMapping(value = "/eventInfo/{eventId}/confirm/{gameId}" )
	public String confirmGame(@PathVariable String eventId,
							  @PathVariable String gameId) {
		Game game = eachEventService.getGameById(Integer.parseInt(gameId));
		game.setConfirm(true);
		eachEventService.updateGame(game);
		return REDIRECT_EVENT_INFO_PAGE_BASE + Integer.valueOf(eventId);
	}

	@RequestMapping(value = "/eventInfo/{eventId}/addGame", method = RequestMethod.POST)
	public String addSingleGame(@PathVariable String eventId,
								@ModelAttribute("singleGameForm") SingleGameForm form,
								RedirectAttributes model) {
		Event event = eachEventService.getEventById(Integer.parseInt(eventId));

		if (event == null) {
			return NOT_FOUND;
		}

		Game gameToPersist = eachEventService.createAndFillNewSingleGame(form, event);
		eachEventService.persistNewSingleGame(gameToPersist);
		model.addFlashAttribute("gameCreateInfo", GAME_CREATED);
		return REDIRECT_EVENT_INFO_PAGE_BASE + Integer.valueOf(eventId);
	}

	@RequestMapping(value = "/eventInfo/{eventId}/addMark", method = RequestMethod.POST)
	public String addMarkToTeamSport(@PathVariable String eventId,
									 @ModelAttribute("teamGameForm") TeamGameForm form,
									 RedirectAttributes model) {

		Event event = eachEventService.getEventById(Integer.parseInt(eventId));
		Mark mark = eachEventService.createAndFillNewMark(form, event);
		String evaluativeUser = form.getParticipant();
		Integer idOfEvent = Integer.parseInt(eventId);

		if (!eachEventService.isUserEvaluatedByLoggedInUser(evaluativeUser, idOfEvent)) {
			eachEventService.persitNewMark(mark);
			model.addFlashAttribute("markInfo", MARK_ADDED_INFO);
		} else {
			model.addFlashAttribute("markInfo", USER_IS_EVALUATED_INFO);
		}
		return REDIRECT_EVENT_INFO_PAGE_BASE + Integer.parseInt(eventId);
	}
}

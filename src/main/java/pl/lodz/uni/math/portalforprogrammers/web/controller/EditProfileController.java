package pl.lodz.uni.math.portalforprogrammers.web.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.lodz.uni.math.portalforprogrammers.logic.forms.SportForm;
import pl.lodz.uni.math.portalforprogrammers.logic.utils.LoggedinUser;
import pl.lodz.uni.math.portalforprogrammers.userhelper.UserHelper;

@Controller
public class EditProfileController {
	private static final String EDIT_PAGE = "editprofile";
	private static final String EDIT_PAGE_REDIRECT = "redirect:/edit";
	private static final String SUCCESS_ADDED = "dodano sport";
	private static final String FAIL_ADDED = "nie udalo sie dodac sportu";

	private LoggedinUser loggedinUser = null;
	
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public String getEditPage(Model model) {
		List<String> sportsNames = generateTownNames();
		model.addAttribute("sportForm", new SportForm());
		model.addAttribute("sports", sportsNames);
		return EDIT_PAGE;
	}
	
	private List<String> generateTownNames() {
		return Arrays.asList("TENIS ZIEMNY", "TENIS STOLOWY", "PILKA NOZNA", "SIATKOWKA");
	}
	
	@RequestMapping(value="/edit/addsport", method=RequestMethod.POST)
	public String procesForm(@Valid @ModelAttribute("sportForm") SportForm form,
			BindingResult result, Model model) {
		
		String username = UserHelper.getLoggedinUserName();
		loggedinUser = new LoggedinUser(username);
		
		if (!result.hasErrors()) {
			String sportName = form.getSportName();
			loggedinUser.addSport(sportName);
			model.addAttribute("info", SUCCESS_ADDED);
			return EDIT_PAGE_REDIRECT;
		}
		
		model.addAttribute("info", FAIL_ADDED);
		return EDIT_PAGE_REDIRECT;
	}
}
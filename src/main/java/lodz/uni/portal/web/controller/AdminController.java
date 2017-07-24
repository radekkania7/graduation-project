package lodz.uni.portal.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lodz.uni.portal.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lodz.uni.portal.model.PortalUser;
import lodz.uni.portal.model.UserAccountStatus;
import lodz.uni.portal.model.type.UserAccountStatusType;
import lodz.uni.portal.service.UserAccountStatusService;

@Controller
public class AdminController {
	private static final Logger logger = Logger.getLogger(AdminController.class);
	
	private static final String ADMIN_PAGE = "adminPanel";
	private static final String REDIRECT_ADMIN_PAGE = "redirect:/panelAdmin";
	private static final String SUCCESS_ACTIVATED_ACCOUNT= "aktywowano konto uzytkownika";
	private static final String SUCCESS_DISABLED_ACCOUNT = "zablokowano konto uzytkownika";
	
	@Autowired
	@Qualifier("userAccountStatusService")
	UserAccountStatusService userAccountStatusService;
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/panelAdmin", method = RequestMethod.GET)
	public String getForm(Model model, HttpServletRequest request) {
		List<PortalUser> activeUsers = getUsersByAccountStatusType(UserAccountStatusType.ACTIVE);
		List<PortalUser> createdUsers = getUsersByAccountStatusType(UserAccountStatusType.CREATED);
		List<PortalUser> disabledUsers = getUsersByAccountStatusType(UserAccountStatusType.DISABLE);
				
		model.addAttribute("activeUsers", activeUsers);
		model.addAttribute("createdUsers", createdUsers);
		model.addAttribute("disabledUsers", disabledUsers);
		return ADMIN_PAGE;
	}
		
	private List<PortalUser> getUsersByAccountStatusType(UserAccountStatusType type) {
		String statusType = type.getType();
		UserAccountStatus status = userAccountStatusService.getStatusByType(statusType);
		List<PortalUser> users = null;
		if (status != null) {
			users = status.getUsersWithThisStatus();
		}
		return users;
	}
	
	@RequestMapping(value = "/panelAdmin/{action}/{userId}", method = RequestMethod.POST)
	public String changeeUserStatus(@PathVariable String userId,
											@PathVariable String action,
											RedirectAttributes model) {
		
		PortalUser user = userService.findById(Integer.parseInt(userId));
		UserAccountStatus newStatus = getStatus(action);

		user.setUserAccountStatus(newStatus);
		userService.update(user);
		addInfo(model, newStatus.getType());
		return REDIRECT_ADMIN_PAGE;
	}
	
	private UserAccountStatus getStatus(String type) {
		return userAccountStatusService.getStatusByType(type);
	}
	
	private void addInfo(Model model, String action) {
		String ACTIVE = UserAccountStatusType.ACTIVE.getType();
		String DISABLE = UserAccountStatusType.DISABLE.getType();
		
		if (action.equals(ACTIVE)) {
			model.addAttribute("info", SUCCESS_ACTIVATED_ACCOUNT);
		}
		if (action.equals(DISABLE)) {
			model.addAttribute("info", SUCCESS_DISABLED_ACCOUNT);
		}
	} 
}
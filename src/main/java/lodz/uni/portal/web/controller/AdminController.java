package lodz.uni.portal.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lodz.uni.portal.model.PortalUser;
import lodz.uni.portal.model.UserAccountStatus;
import lodz.uni.portal.model.type.UserAccountStatusType;
import lodz.uni.portal.service.UserAccountStatusService;

@Controller
public class AdminController {
	private static final Logger logger = Logger.getLogger(AdminController.class);
	
	private static final String ADMIN_PAGE = "adminPanel";
	
	@Autowired
	@Qualifier("userAccountStatusService")
	UserAccountStatusService userAccountStatusService;

	@RequestMapping(value = "/panelAdmin", method = RequestMethod.GET)
	public String getForm(Model model) {
		List<PortalUser> activeUsers = getUsersByAccountStatusType(UserAccountStatusType.ACTIVE);
		List<PortalUser> createdUsers = getUsersByAccountStatusType(UserAccountStatusType.CREATED);
		List<PortalUser> disabledUsers = getUsersByAccountStatusType(UserAccountStatusType.DISABLE);
		
		model.addAttribute("activeUsers", activeUsers);
		model.addAttribute("createdUsers", createdUsers);
		model.addAttribute("disabledUsers", disabledUsers);
		return ADMIN_PAGE;
	}
		
	private List<PortalUser> getUsersByAccountStatusType(UserAccountStatusType type) {
		String statusType = type.getUserAccountStatusType();
		UserAccountStatus status = userAccountStatusService.getStatusByType(statusType);
		List<PortalUser> users = null;
		if (status != null) {
			users = status.getUsersWithThisStatus();
		}
		return users;
	}
}

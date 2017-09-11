package lodz.uni.portal.web.controller;

import lodz.uni.portal.model.type.EventStatusType;
import lodz.uni.portal.service.LoggedInUserService;
import lodz.uni.portal.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserHomeController {
    private static final Logger logger = Logger.getLogger(UserHomeController.class);
    private static final String HOME_PAGE = "home";
    final static String CREATED = EventStatusType.CREATED.getType();
    final static String AFTER = EventStatusType.AFTER.getType();
    final static String CLOSED = EventStatusType.CLOSED.getType();
    final static String DURING = EventStatusType.DURING.getType();
    final static Integer limit = 5;

    @Autowired
    private UserService userService;

    @Autowired
    private LoggedInUserService loggedInUserService;

    @RequestMapping(value = { "/homepage" }, method= RequestMethod.GET)
    public String home(Model model) {
        String loggedInUseName = loggedInUserService.getLoggedInUser().getNickname();

        model.addAttribute("incomingEvents", userService.getEventsByStatusForUser(loggedInUseName, CREATED, limit));
        model.addAttribute("eventsToEvaluate", userService.getEventsByStatusForUser(loggedInUseName, AFTER, limit));
        model.addAttribute("historyEvents", userService.getEventsByStatusForUser(loggedInUseName, CLOSED, limit));
        model.addAttribute("duringEvents", userService.getEventsByStatusForUser(loggedInUseName, DURING, limit));
        model.addAttribute("loggedInUser", loggedInUseName);
        return HOME_PAGE;
    }


}

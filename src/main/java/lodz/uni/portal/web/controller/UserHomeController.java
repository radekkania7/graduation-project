package lodz.uni.portal.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserHomeController {
    private static final Logger logger = Logger.getLogger(UserHomeController.class);
    private static final String HOME_PAGE = "home";

    @RequestMapping(value = { "/homepage" }, method= RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("message", "hello, it is working");
        return HOME_PAGE;
    }
}

package lodz.uni.portal.web.controller;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
public class HomeController {
	private static final Logger logger = Logger.getLogger(HomeController.class);
	private static final String HOME_PAGE = "home";
	private static final String DEFAULT_PAGE = "default";
	
	@RequestMapping(value = { "/homepage" }, method=RequestMethod.GET)
	public String home(Model model) {	
		model.addAttribute("message", "hello, it is working");
		return HOME_PAGE;
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String getDefaultPage() {
		return DEFAULT_PAGE;
	}

}

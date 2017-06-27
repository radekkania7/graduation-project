package pl.lodz.uni.math.portalForProgrammer.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import junit.framework.Assert;
import pl.lodz.uni.math.portalforprogrammers.service.MarkService;
import pl.lodz.uni.math.portalforprogrammers.web.controller.EventsController;

public class EventsControllerTest {
	//@Test
	public void testHomePage() throws Exception {
		EventsController controller = new EventsController();
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/events")).andExpect(view().name("eventslist"));
	}
}

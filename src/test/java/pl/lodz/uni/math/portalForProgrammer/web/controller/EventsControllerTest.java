package pl.lodz.uni.math.portalForProgrammer.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.springframework.test.web.servlet.MockMvc;

import pl.lodz.uni.math.portalforprogrammers.web.controller.EventsController;

public class EventsControllerTest {
	//@Test
	public void testHomePage() throws Exception {
		EventsController controller = new EventsController();
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/events")).andExpect(view().name("eventslist"));
	}
}

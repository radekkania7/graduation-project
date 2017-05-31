package pl.lodz.uni.math.portalforprogrammers.service;

import java.util.List;

import pl.lodz.uni.math.portalforprogrammers.model.Event;
import pl.lodz.uni.math.portalforprogrammers.model.EventStatus;

public interface EventService {
	public List<Event> findAllEvents();
	
	public List<Event> findActualEvents();
	
	public List<Event> findActualEventsByParam(String TownName, String SportName);
		
	public Event findEventById(Integer id);
	
	public List<Event> findEventsByStatus(EventStatus status);

	public void save(Event event);
	
	public void update(Event event);
	
	public List<Event> findEventByUserNameAndSportName(Integer userId, Integer sportId);
}


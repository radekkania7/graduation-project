package pl.lodz.uni.math.portalforprogrammers.service;

import java.util.List;

import pl.lodz.uni.math.portalforprogrammers.model.Event;

public interface EventService {
	public List<Event> findAllEvents();
	
	public List<Event> findActualEvents();
	
	public List<Event> findActualEventsByParam(String TownName, String SportName);
	
	public Event findEventById(Integer id);

	public void save(Event event);
	
	public void update(Event event);
}


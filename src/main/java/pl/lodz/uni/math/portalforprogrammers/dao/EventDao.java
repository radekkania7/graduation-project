package pl.lodz.uni.math.portalforprogrammers.dao;

import java.util.List;

import pl.lodz.uni.math.portalforprogrammers.model.Event;

public interface EventDao {
	public void save(Event event);
	
	public void updateEvent(Event event);
	
	public List<Event> findAllEvents();

	public Event findEventById(Integer id);
	
	public List<Event> findActualEvents();

	public List<Event> findActualEventsByPara(String townName, String sportName);
}

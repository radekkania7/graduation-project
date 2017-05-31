package pl.lodz.uni.math.portalforprogrammers.dao;

import java.util.List;

import pl.lodz.uni.math.portalforprogrammers.model.Event;
import pl.lodz.uni.math.portalforprogrammers.model.EventStatus;

public interface EventDao {
	public void save(Event event);
	
	public void updateEvent(Event event);
	
	public List<Event> findAllEvents();

	public Event findEventById(Integer id);
	
	public List<Event> findActualEvents();

	public List<Event> findActualEventsByParam(String townName, String sportName);

	public List<Event> findEventsByStatus(EventStatus status);

	public List<Event> findEventByUserNameAndSport(Integer userId, Integer sportId);
}

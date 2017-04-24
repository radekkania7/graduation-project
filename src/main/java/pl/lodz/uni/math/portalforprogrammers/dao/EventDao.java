package pl.lodz.uni.math.portalforprogrammers.dao;

import java.util.List;

import pl.lodz.uni.math.portalforprogrammers.model.Event;

public interface EventDao {
	public void save(Event event);
	
	public List<Event> findAllEvents();

	public Event findEventById(Integer id);
}

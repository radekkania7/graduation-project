package pl.lodz.uni.math.portalforprogrammers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.lodz.uni.math.portalforprogrammers.dao.EventDao;
import pl.lodz.uni.math.portalforprogrammers.model.Event;

@Service("eventService")
@Transactional
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventDao dao;

	@Override
	public List<Event> findAllEvents() {
		return dao.findAllEvents();
	}

	@Override
	public List<Event> findActualEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> findActualEventsByParam(String TownName, String SportName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Event event) {
		dao.save(event);
	}

	@Override
	public Event findEventById(Integer id) {
		return dao.findEventById(id);
	}

	@Override
	public void update(Event event) {
		dao.update(event);
	}
	
}

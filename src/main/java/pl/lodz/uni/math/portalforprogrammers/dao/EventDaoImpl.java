package pl.lodz.uni.math.portalforprogrammers.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import pl.lodz.uni.math.portalforprogrammers.model.Event;

@Repository("eventDao")
public class EventDaoImpl extends AbstracDao<Integer, Event> implements EventDao {
	
	@Override
	public void save(Event event) {
		super.persist(event);
	}

	@Override
	public void updateEvent(Event event) {
		super.update(event);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Event> findAllEvents() {
		Criteria criteria = getEntityCriteria().addOrder(Order.asc("eventDate"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<Event>) criteria.list();
	}

	@Override
	public Event findEventById(Integer id) {
		Event event = getByKey(id);
		if (event != null) {
			Hibernate.initialize(event.getEventUsers());
		}
		return event;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Event> findActualEvents() {
		Criteria criteria = getEntityCriteria().addOrder(Order.asc("eventDate"));
        criteria.add(Restrictions.eq("done", new Boolean(false)));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Event> events = (List<Event>) criteria.list();
        return events;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Event> findActualEventsByPara(String townName, String sportName) {
		//TODO -> Metoda do filtrowania wydarzen
		return null;
	}
}

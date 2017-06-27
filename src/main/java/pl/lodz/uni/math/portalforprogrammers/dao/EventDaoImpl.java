package pl.lodz.uni.math.portalforprogrammers.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import pl.lodz.uni.math.portalforprogrammers.model.Event;
import pl.lodz.uni.math.portalforprogrammers.model.EventStatus;
import pl.lodz.uni.math.portalforprogrammers.model.PortalUser;

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
		if (event != null && event.getEventUsers() != null) {
			Hibernate.initialize(event.getEventUsers());
			for (PortalUser u : event.getEventUsers()) {
				Hibernate.initialize(u.getEvaluatedMarks());
			}
		}
		return event;
	}

	//sprawdzic date, jesli data wieksza lub rowna dzisiejszej dacie to wyswietlac
	@Override
	@SuppressWarnings("unchecked")
	public List<Event> findActualEvents() {
		Criteria criteria = getEntityCriteria().addOrder(Order.asc("eventDate"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Event> events = (List<Event>) criteria.list();
        return events;
	}

	//filtrowanie wydarzen.
	@Override
	@SuppressWarnings("unchecked")
	public List<Event> findActualEventsByParam(String townName, String sportName) {
		//Criteria criteria = getEntityCriteria().addOrder(Order.asc("eventDate"));
		Criteria criteria = getSession().createCriteria(Event.class, "event");
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.gt("status", 0));
		
		//criteria.createAlias("event.eventDate", "date");
		//criteria.add(Restrictions.ge("date", new Date()));
		
		if (townName != null) {
			criteria.createAlias("event.eventTown", "town");
			criteria.add(Restrictions.eq("town.name", townName));
		} else if (sportName != null) {
			criteria.createAlias("event.eventSport", "sport");
			criteria.add(Restrictions.eq("sport.name", sportName));
		} 
		return (List<Event>) criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Event> findEventsByStatus(EventStatus status) {
		Criteria criteria = getEntityCriteria().addOrder(Order.asc("eventDate"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("status", status.getValue()));
        List<Event> events = (List<Event>) criteria.list();
        return events;
	}

	@Override
	public List<Event> findEventByUserNameAndSport(Integer userId, Integer sportId) {
		Criteria criteria = getEntityCriteria();
		Criterion sportCrit = Restrictions.eq("eventSport.id", sportId);
		return null;
	}
}

package lodz.uni.portal.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import lodz.uni.portal.model.Event;
import lodz.uni.portal.model.PortalUser;

@Repository("eventDao")
public class EventDaoImpl extends BaseDao<Integer, Event> implements EventDao {
	
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

	@Override
	@SuppressWarnings("unchecked")
	public List<Event> findActualEventsByParam(String townName, String sportName) {
		Criteria criteria = getSession().createCriteria(Event.class, "event");
		
		criteria.add(Restrictions.gt("status", 0));
		criteria.createAlias("event.eventTown", "town");
		criteria.createAlias("event.eventSport", "sport");
		
		Criterion townCriterion = null;
		Criterion sportCriterion = null;
		
		if (townName != null) {
			townCriterion = Restrictions.eq("town.name", townName);
		}
		
		if(sportName != null) {
			sportCriterion = Restrictions.eq("sport.name", sportName);
		}
		
		if (townCriterion != null && sportCriterion != null) {
			criteria.add(Restrictions.and(townCriterion, sportCriterion));
		}
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<Event>) criteria.list();
	}


}

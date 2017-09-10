package lodz.uni.portal.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.regexp.internal.RE;
import lodz.uni.portal.form.FindEventForm;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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

	@Override
	public List<Event> getByParams(FindEventForm form) {
		Criteria criteria = getSession().createCriteria(Event.class, "event");
		criteria.createAlias("event.eventSport", "sport");
		criteria.createAlias("event.status", "status");

		Date date = (Date) form.getDate();
		String sportName = (String) form.getSport();
		String town = (String) form.getTown();

		Criterion dateCriterion = Restrictions.eq("eventDate", date);
		Criterion sportCriterion = Restrictions.eq("sport.name", sportName);
		Criterion townCriterion = Restrictions.eq("town", town);
		Criterion eventStatusCriterion = Restrictions.eq("status.type", "CREATED");

		Criterion expressionFirst = Restrictions.and(sportCriterion, townCriterion);
		Criterion expressionSecond = Restrictions.and(dateCriterion, eventStatusCriterion);

		Criterion finalCriterion = Restrictions.and(expressionFirst, expressionSecond);

		criteria.add(finalCriterion);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return (List<Event>) criteria.list();
	}

	public List<Event> getEventsByStatusAndLimit(String username, String statusType, Integer limit) {
		Criteria criteria = getEntityCriteria();

		criteria.createAlias("eventUsers", "user");
		criteria.createAlias("status", "status");

		criteria.add(Restrictions.eq("status.type", statusType));
		criteria.add(Restrictions.eq("user.nickname", username));

		if (limit != null) {
			criteria.setMaxResults(limit);
		}

		criteria.addOrder(Order.asc("eventDate"));

		return (List<Event>) criteria.list();
	}
}

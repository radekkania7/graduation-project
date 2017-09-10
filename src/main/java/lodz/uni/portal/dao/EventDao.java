package lodz.uni.portal.dao;

import java.util.List;
import java.util.Map;

import com.sun.xml.internal.org.jvnet.fastinfoset.stax.LowLevelFastInfosetStreamWriter;
import lodz.uni.portal.form.FindEventForm;
import lodz.uni.portal.model.Event;

public interface EventDao {
	public void save(Event event);
	
	public void updateEvent(Event event);
	
	public List<Event> findAllEvents();

	public Event findEventById(Integer id);
	
	public List<Event> findActualEvents();

	public List<Event> findActualEventsByParam(String townName, String sportName);

	//nowe API

	List<Event> getByParams(FindEventForm form);

	List<Event> getEventsByStatusAndLimit(String username, String statusType, Integer limit);
}

package lodz.uni.portal.service;

import lodz.uni.portal.dao.EventDao;
import lodz.uni.portal.form.FindEventForm;
import lodz.uni.portal.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("eventService")
@Transactional
public class EventsServiceImpl implements EventsService {
    @Autowired
    EventDao eventDao;

    @Override
    public List<Event> findEventsByParams(FindEventForm form) {
        return eventDao.getByParams(form);
    }
}

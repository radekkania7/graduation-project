package lodz.uni.portal.service;

import lodz.uni.portal.dao.EventDao;
import lodz.uni.portal.model.Event;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class EventsServiceImpl implements EventsService {
    @Autowired
    EventDao eventDao;

    @Override
    public List<Event> findEventsByParams(Map<String, Object> params) {
        return eventDao.getByParams(params);
    }
}

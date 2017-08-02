package lodz.uni.portal.service;

import lodz.uni.portal.model.Event;

import java.util.List;
import java.util.Map;

public interface EventsService {
    List<Event> findEventsByParams(Map<String, Object> params);
}

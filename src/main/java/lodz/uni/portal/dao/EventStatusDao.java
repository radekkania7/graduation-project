package lodz.uni.portal.dao;

import lodz.uni.portal.model.EventStatus;

public interface EventStatusDao {
    EventStatus getEventStatusByType(String type);
}

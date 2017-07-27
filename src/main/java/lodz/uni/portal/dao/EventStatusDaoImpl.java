package lodz.uni.portal.dao;

import lodz.uni.portal.model.EventStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.events.Event;

@Repository("eventStatusDao")
@Transactional
public class EventStatusDaoImpl extends BaseDao<Integer, EventStatus> implements EventStatusDao {

    @Override
    public EventStatus getEventStatusByType(String type) {
        return getByField("type", type);
    }
}

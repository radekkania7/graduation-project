package lodz.uni.portal.service;

import lodz.uni.portal.dao.EventDao;
import lodz.uni.portal.dao.PortalUserDao;
import lodz.uni.portal.model.Event;
import lodz.uni.portal.model.PortalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;


@Service("eachEventService")
@Transactional
public class EachEventServiceImpl implements EachEventService {

    @Autowired
    EventDao eventDao;

    @Autowired
    PortalUserDao portalUserDao;

    @Override
    public Event getEventById(Integer id) {
        return eventDao.findEventById(id);
    }

    @Override
    public boolean isUserParticipate(PortalUser user, Event event) {
        List<String> names = new LinkedList<>();
        String userName = user.getNickname();
        for (PortalUser u : event.getEventUsers()) {
            if (u.getNickname().equals(userName))  {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean isFreePlace(Event event) {
        return getFreePlaces(event) >= 1;
    }

    private int getFreePlaces(Event event) {
        int limitCount = event.getPlayersLimit();
        int participantCount = event.getEventUsers().size();
        return limitCount - participantCount;
    }

    @Override
    public void addUserIntoEventParticipants(PortalUser user, Event event) {
        event.getEventUsers().add(user);
        user.getUserEvents().remove(event);
        eventDao.updateEvent(event);
        portalUserDao.updateUser(user);
    }

    @Override
    public void removeUserFromEvenParticipants(PortalUser user, Event event) {
        event.getEventUsers().remove(user);
        user.getUserEvents().remove(event);
        eventDao.updateEvent(event);
        portalUserDao.updateUser(user);
    }
}

package lodz.uni.portal.service;

import lodz.uni.portal.model.Event;
import lodz.uni.portal.model.PortalUser;

public interface EachEventService {
    Event getEventById(Integer id);

    boolean isUserParticipate(PortalUser user, Event event);

    boolean isFreePlace(Event event);

    void addUserIntoEventParticipants(PortalUser user, Event event);

    void removeUserFromEvenParticipants(PortalUser user, Event event);
}

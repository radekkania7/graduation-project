package lodz.uni.portal.service;

import lodz.uni.portal.form.SingleGameForm;
import lodz.uni.portal.form.TeamGameForm;
import lodz.uni.portal.model.Event;
import lodz.uni.portal.model.Game;
import lodz.uni.portal.model.Mark;
import lodz.uni.portal.model.PortalUser;

import java.util.List;

public interface EachEventService {
    Event getEventById(Integer id);

    boolean isUserParticipate(Event event);

    boolean isFreePlace(Event event);

    void addUserIntoEventParticipants(Event event);

    void removeUserFromEvenParticipants(Event event);

    PortalUser getLoggedInUser();

    Game createAndFillNewSingleGame(SingleGameForm form, Event event);

    void persistNewSingleGame(Game gameToPersist);

    List<String> getParticipantsNamesWithoutLoggedInUser(Event event);

    Game getGameById(Integer id);

    void updateGame(Game game);

    void persitNewMark(Mark mark);

    Mark createAndFillNewMark(TeamGameForm form, Event event);

    boolean isUserEvaluatedByLoggedInUser(String thatUsername, Integer eventId);
}

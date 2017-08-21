package lodz.uni.portal.service;

import lodz.uni.portal.dao.EventDao;
import lodz.uni.portal.dao.GameDao;
import lodz.uni.portal.dao.MarkDao;
import lodz.uni.portal.dao.PortalUserDao;
import lodz.uni.portal.form.SingleGameForm;
import lodz.uni.portal.form.TeamGameForm;
import lodz.uni.portal.model.Event;
import lodz.uni.portal.model.Game;
import lodz.uni.portal.model.Mark;
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

    @Autowired
    LoggedInUserService loggedInUserService;

    @Autowired
    GameDao gameDao;

    @Autowired
    MarkDao markDao;

    @Override
    public Event getEventById(Integer id) {
        return eventDao.findEventById(id);
    }

    @Override
    public boolean isUserParticipate(Event event) {
        PortalUser user = getLoggedInUser();
        String userName = user.getNickname();
        if (event.getEventUsers().size() == 0) {
            return false;
        }

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
    public void addUserIntoEventParticipants(Event event) {
        PortalUser loggedInUser = getLoggedInUser();
        event.getEventUsers().add(loggedInUser);
        loggedInUser.getUserEvents().remove(event);
        eventDao.updateEvent(event);
        portalUserDao.updateUser(loggedInUser);
    }

    @Override
    public void removeUserFromEvenParticipants(Event event) {
        PortalUser loggedInUser = getLoggedInUser();
        event.getEventUsers().remove(loggedInUser);
        loggedInUser.getUserEvents().remove(event);
        eventDao.updateEvent(event);
        portalUserDao.updateUser(loggedInUser);
    }

    @Override
    public Game createAndFillNewSingleGame(SingleGameForm form, Event event) {
        Game game = new Game();
        PortalUser hostUser = loggedInUserService.getLoggedInUser();
        PortalUser opponetUser = portalUserDao.findByUsername(form.getOpponentName());

        game.setEvent(event);
        game.setHostUser(hostUser);
        game.setHostResult(form.getLoggedInUserResult());
        game.setGuestResult(form.getOpponentResult());
        game.setGuestUser(opponetUser);
        game.setDesc(form.getDescription());
        game.setConfirm(false);
        return game;
    }

    @Override
    public void persistNewSingleGame(Game gameToPersist) {
        gameDao.save(gameToPersist);
    }


    public PortalUser getLoggedInUser() {
        return loggedInUserService.getLoggedInUser();
    }

    public List<String> getParticipantsNamesWithoutLoggedInUser(Event event) {
        List<String> names = new LinkedList<>();
        for (PortalUser u : event.getEventUsers()) {
            String name = u.getNickname();
            if (!name.equals(getLoggedInUser().getNickname())) {
                names.add(name);
            }
        }
        return names;
    }

    public Game getGameById(Integer id) {
        return gameDao.findById(id);
    }

    public void updateGame(Game game) {
        gameDao.updateGame(game);
    }

    @Override
    public Mark createAndFillNewMark(TeamGameForm form, Event event) {
        Mark mark = new Mark();
        String evalutedUserNickname = form.getParticipant();
        Integer markValue = Integer.parseInt(form.getMark());

        PortalUser loggedInUser = getLoggedInUser();
        PortalUser evaluatedUser = portalUserDao.findByUsername(evalutedUserNickname);

        mark.setEvent(event);
        mark.setEvaluativeUser(loggedInUser);
        mark.setEvaluatedUser(evaluatedUser);
        mark.setValue(markValue);
        return mark;
    }

    @Override
    public void persitNewMark(Mark mark) {
        markDao.saveMark(mark);
    }

    public boolean isUserEvaluatedByLoggedInUser(String thatUsername, Integer eventId) {
        String loggedInUser = getLoggedInUser().getNickname();
        List<Mark> marks = markDao.getMarkByParams(loggedInUser, thatUsername, eventId);
        if (!marks.isEmpty()) {
            return true;
        }
        return false;
    }
}

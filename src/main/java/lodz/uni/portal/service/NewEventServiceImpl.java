package lodz.uni.portal.service;

import lodz.uni.portal.dao.EventDao;
import lodz.uni.portal.dao.EventStatusDao;
import lodz.uni.portal.dao.SportDao;
import lodz.uni.portal.form.EventForm;
import lodz.uni.portal.model.Event;
import lodz.uni.portal.model.EventStatus;
import lodz.uni.portal.model.Sport;

import lodz.uni.portal.model.type.EventStatusType;
import lodz.uni.portal.utils.CustomDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.sql.Date;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@Service("newEventService")
@Transactional
public class NewEventServiceImpl implements NewEventService {

    @Autowired
    MessageSource messageSource;

    @Autowired
    SportDao sportDao;

    @Autowired
    EventDao eventDao;

    @Autowired
    EventStatusDao eventStatusDao;

    @Override
    public List<String> getSportNames() {
        List<String> names = new LinkedList<>();
        for (Sport s : getSportList()) {
            names.add(s.getName());
        }
        return names;
    }

    private List<Sport> getSportList() {
        return sportDao.findAll();
    }

    @Override
    public void validateOtherFiled(EventForm form, BindingResult result) {
        validateDateAfterToday(form.getDate(), result);
    }

    private void validateDateAfterToday(Date date, BindingResult result) {
        if (date == null || CustomDateUtils.isDateBeforeToday(date)) {
            String message = messageSource.getMessage("before.today.eventForm.date", new String[]{""}, Locale.getDefault());
            FieldError error = new FieldError("eventForm", "date", message);
            result.addError(error);
        }
    }

    @Override
    public void persistNewEvent(Event event) {
        eventDao.save(event);
    }

    @Override
    public Event createAndFillNewEvent(EventForm form) {
        String sportName = form.getSport();
        Integer participantLimit = Integer.valueOf(form.getParticipantLimit());
        EventStatus status = getCreatedStatus();
        Time timeStart = getTimeFromString(form.getTimeStart());
        Time timeEnd = getTimeFromString(form.getTimeEnd());

        Event event = new Event();
        event.setEventDate(form.getDate());
        event.setTown(form.getTown());
        event.setEventSport(getSportByName(sportName));
        event.setPlayersLimit(participantLimit);
        event.setStatus(status);
        event.setStartTime(timeStart);
        event.setStopTime(timeEnd);

        //TODO ++ autor wydarzenia
        //dodac kolumne w bazie, zmapowac i gitara siema
        return event;
    }

    private Sport getSportByName(String name) {
        return sportDao.findSportByName(name);
    }

    private EventStatus getCreatedStatus() {
        String type = EventStatusType.CREATED.getType();
        return eventStatusDao.getEventStatusByType(type);
    }

    private Time getTimeFromString(String time) {
        return CustomDateUtils.getSqlTimeFromString(time);
    }

}

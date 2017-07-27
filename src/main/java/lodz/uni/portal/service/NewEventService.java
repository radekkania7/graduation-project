package lodz.uni.portal.service;

import lodz.uni.portal.form.EventForm;
import lodz.uni.portal.model.Event;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface NewEventService {
    List<String> getSportNames();

    Event createAndFillNewEvent(EventForm form);

    void validateOtherFiled(EventForm form, BindingResult result);

    void persistNewEvent(Event event);


}

package lodz.uni.portal.form;


import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

public class EventForm {
    private String sport;
    @Size(min = 3, max = 50)
    private String town;

    @Size(min = 3, max = 50)
    private String address;

    @NotNull
    private Date date;

    @Size(min = 5, max = 5)
    private String timeStart;

    @Size(min = 5, max = 5)
    private String timeEnd;

    @Size(min = 1, max = 2)
    private String participantLimit;

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getParticipantLimit() {
        return participantLimit;
    }

    public void setParticipantLimit(String participantLimit) {
        this.participantLimit = participantLimit;
    }
}

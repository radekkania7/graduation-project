package lodz.uni.portal.model;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="EVENT_STATUS")
public class EventStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_STATUS_ID")
    private Integer id;

    @Column(name="EVENT_STATUS_TYPE", unique = true)
    private String type;

    @OneToMany(mappedBy="status")
    private List<Event> eventsWithStatus = new LinkedList<>();

    public List<Event> getEventsWithStatus() {
        return eventsWithStatus;
    }

    public void setEventsWithStatus(List<Event> eventsWithStatus) {
        this.eventsWithStatus = eventsWithStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package pl.lodz.uni.math.portalforprogrammers.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="EVENTS")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EVENT_ID")
	private Integer id; 
	
	@Column(name="DESCRIPTION")
	@NotNull
	private String description;
	
	@Column(name="EVENT_DATE", nullable=false)
	private Date eventDate;
	
	@Column(name="EVENT_TIME")
	private String eventTime;
	
	@Column(name="DURATION_TIME")
	private Integer durationTime;
	
	@Column(name="DONE")
	private Boolean done;
	
	@Column(name="PLAYERS_LIMIT")
	@NotNull
	private Integer playersLimit;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="TOWN_ID")
	@NotNull
	private Town eventTown;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull
	@JoinColumn(name="SPORT_ID")
	private Sport eventSport;
	
	public Sport getEventSport() {
		return eventSport;
	}

	public void setEventSport(Sport eventSport) {
		this.eventSport = eventSport;
	}

	@ManyToMany
	@JoinTable(name="EVENT_USERS",
			joinColumns=@JoinColumn(name="EVENT_ID", referencedColumnName="EVENT_ID"),
			inverseJoinColumns=@JoinColumn(name="USER_ID", referencedColumnName="USER_ID"))
	private List<PortalUser> eventUsers;
	
	public Integer getPlayersLimit() {
		return playersLimit;
	}

	public void setPlayersLimit(Integer playersLimit) {
		this.playersLimit = playersLimit;
	}

	public Town getEventTown() {
		return eventTown;
	}

	public void setEventTown(Town eventTown) {
		this.eventTown = eventTown;
	}

	public List<PortalUser> getEventUsers() {
		return eventUsers;
	}

	public void setEventUsers(List<PortalUser> eventUsers) {
		this.eventUsers = eventUsers;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Boolean isDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public Integer getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(Integer durationTime) {
		this.durationTime = durationTime;
	}

	public Boolean getDone() {
		return done;
	}
	
	
	
}

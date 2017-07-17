package lodz.uni.portal.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
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
	
	@Column(name="EVENT_DATE")
	private Date eventDate;
	
	@Column(name="START_TIME")
	private Time startTime;
	
	@Column(name="STOP_TIME")
	private Time stopTime;
	
	/*
	 * Status can be 
	 * 0 - not actual event
	 * 1 - upcoming event
	 * 2 - today upcoming event
	 * 3 - ongoing event
	 */
	@Column(name="STATUS")
	private Integer status;
	
	@NotNull
	@Column(name="PLAYERS_LIMIT")
	private Integer playersLimit;
	
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="TOWN_ID")
	private Town eventTown;
	
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SPORT_ID")
	private Sport eventSport;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="event", cascade=CascadeType.ALL)
	private List<Game> eventGames = new LinkedList<Game>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="event", cascade=CascadeType.ALL)
	private List<Mark> eventMarks = new LinkedList<Mark>();
	
	@ManyToMany
	@JoinTable(name="EVENT_USERS",
			joinColumns=@JoinColumn(name="EVENT_ID", referencedColumnName="EVENT_ID"),
			inverseJoinColumns=@JoinColumn(name="USER_ID", referencedColumnName="USER_ID"))
	private List<PortalUser> eventUsers;
	
	public List<Mark> getEventMarks() {
		return eventMarks;
	}

	public void setEventMarks(List<Mark> eventMarks) {
		this.eventMarks = eventMarks;
	}

	public Sport getEventSport() {
		return eventSport;
	}

	public void setEventSport(Sport eventSport) {
		this.eventSport = eventSport;
	}
	
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
	
	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getStopTime() {
		return stopTime;
	}

	public void setStopTime(Time stopTime) {
		this.stopTime = stopTime;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Game> getEventGames() {
		return eventGames;
	}

	public void setEventGames(List<Game> eventGames) {
		this.eventGames = eventGames;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

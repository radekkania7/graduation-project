package pl.lodz.uni.math.portalforprogrammers.model;

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
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "event", cascade = CascadeType.ALL)
	private List<Game> eventGames = new LinkedList<Game>();
	
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
	
}

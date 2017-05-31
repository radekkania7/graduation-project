package pl.lodz.uni.math.portalforprogrammers.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SPORTS")
public class Sport implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SPORT_ID")
	private Integer id;
	
	@Column(name="NAME", nullable=false)
	private String name;
	
	@Column(name="TEAM", nullable=false)
	private boolean teamSport;
	
	@OneToMany(mappedBy="sport")
	private Set<UserSport> userSports = new HashSet<UserSport>();
	
	@OneToMany(mappedBy="eventSport")
	private Set<Event> sportEvents = new HashSet<Event>();
	
	public Set<Event> getSportEvents() {
		return sportEvents;
	}

	public void setSportEvents(Set<Event> sportEvents) {
		this.sportEvents = sportEvents;
	}

	public Set<UserSport> getUserSports() {
		return userSports;
	}

	public void setUserSports(Set<UserSport> userSports) {
		this.userSports = userSports;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isTeamSport() {
		return teamSport;
	}

	public void setTeamSport(boolean teamSport) {
		this.teamSport = teamSport;
	}

}

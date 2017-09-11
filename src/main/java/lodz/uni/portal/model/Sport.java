package lodz.uni.portal.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

	@Column(name="sportIcon")
	private String sportIconPath;
		
	@OneToMany(mappedBy="eventSport")
	private Set<Event> sportEvents = new HashSet<Event>();

	public String getSportIconPath() {
		return sportIconPath;
	}

	public void setSportIconPath(String sportIconPath) {
		this.sportIconPath = sportIconPath;
	}

	@ManyToMany
	@JoinTable(name="USER_SPORTS",
			joinColumns=@JoinColumn(name="SPORT_ID", referencedColumnName="SPORT_ID"),
			inverseJoinColumns=@JoinColumn(name="USER_ID", referencedColumnName="USER_ID"))
	private List<PortalUser> sportUsers;
	
	public List<PortalUser> getSportUsers() {
		return sportUsers;
	}

	public void setSportUsers(List<PortalUser> sportUsers) {
		this.sportUsers = sportUsers;
	}

	public Set<Event> getSportEvents() {
		return sportEvents;
	}

	public void setSportEvents(Set<Event> sportEvents) {
		this.sportEvents = sportEvents;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Sport other = (Sport) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
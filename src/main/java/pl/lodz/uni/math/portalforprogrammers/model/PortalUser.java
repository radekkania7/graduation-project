package pl.lodz.uni.math.portalforprogrammers.model;

import java.io.Serializable;
import java.sql.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="USERS")
public class PortalUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private Integer id;
	
    @Column(name="NICKNAME", nullable=false)
    @NotEmpty
    //@Size(min=8, max=50)
    private String nickname;
    
    @Column(name="FIRSTNAME", nullable=false)
    @NotEmpty
    //@Size(min=3, max=50)
    private String firstName;
    
    @Column(name="LASTNAME", nullable=false)
    @NotEmpty
    //@Size(min=3, max=50)
    private String lastName;
    
    @Column(name="EMAIL", nullable=false)
    @NotEmpty
    @Email
    private String email;

    @Column(name="PASSWORD", nullable=false)
    @NotEmpty
    //@Size(min=8, max=50)
    private String password;
    
    @Column(name="CONFIRMPASS")
    @NotEmpty
    //@Size(min=8, max=50)
    private String confirmPassword;
    
    @Column(name="ENABLED")
    private boolean isEnabled;
    
    @Column(name="ROLE", nullable=false)
    private String role;
    
    @Column(name="DATE_OF_BIRTH", nullable=false)
    private Date dateOfBirth;
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy="user", cascade=CascadeType.ALL)
    private List<UserSport> userSports = new LinkedList<UserSport>();
        
    //TODO do usunięcia chyba
    @OneToMany(mappedBy="evaluator")
    private List<Rating> evaluatedValues = new LinkedList<Rating>();
    
    //TODO to tez
    @OneToMany(mappedBy="rated")
    private List<Rating> recivedValues = new LinkedList<Rating>();
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="evaluatedUser", cascade=CascadeType.ALL)
    private List<Mark> evaluatedMarks = new LinkedList<Mark>();
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="evaluativeUser", cascade=CascadeType.ALL)
    private List<Mark> evaluativeMarks = new LinkedList<Mark>();
    
	@ManyToMany
	@JoinTable(name="EVENT_USERS",
			joinColumns=@JoinColumn(name="USER_ID", referencedColumnName="USER_ID"),
			inverseJoinColumns=@JoinColumn(name="EVENT_ID", referencedColumnName="EVENT_ID"))
	private List<Event> userEvents;
	
	public List<Event> getUserEvents() {
		return userEvents;
	}

	public void setUserEvents(List<Event> userEvents) {
		this.userEvents = userEvents;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public List<Rating> getEvaluatedValues() {
		return evaluatedValues;
	}

	public void setEvaluatedValues(List<Rating> evaluatedValues) {
		this.evaluatedValues = evaluatedValues;
	}

	public List<Rating> getRecivedValues() {
		return recivedValues;
	}

	public void setRecivedValues(List<Rating> recivedValues) {
		this.recivedValues = recivedValues;
	}

	public List<UserSport> getUserSports() {
		return userSports;
	}

	public void setUserSports(List<UserSport> userSports) {
		this.userSports = userSports;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
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
		PortalUser other = (PortalUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		return true;
	}	
}
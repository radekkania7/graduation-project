package lodz.uni.portal.model;

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
import javax.persistence.ManyToOne;
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
    private String nickname;
    
    @Column(name="FIRSTNAME", nullable=false)
    @NotEmpty
    private String firstName;
    
    @Column(name="LASTNAME", nullable=false)
    @NotEmpty
    private String lastName;
    
    @Column(name="EMAIL", nullable=false)
    @NotEmpty
    @Email
    private String email;

    @Column(name="PASSWORD", nullable=false)
    @NotEmpty
    private String password;
    
    @Column(name="CONFIRMPASS")
    private String confirmPassword;
    
    @Column(name="ENABLED")
    private boolean isEnabled;
    
    @Column(name="ROLE", nullable=false)
    private String role;
    
    @Column(name="DATE_OF_BIRTH", nullable=false)
    private Date dateOfBirth;
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="evaluatedUser", cascade=CascadeType.ALL)
    private List<Mark> evaluatedMarks = new LinkedList<Mark>();
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="evalutiveUser", cascade=CascadeType.ALL)
    private List<Mark> evaluativeMarks = new LinkedList<Mark>();
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ACCOUNT_STATUS_FK")
    private UserAccountStatus userAccountStatus;
    
	public UserAccountStatus getUserAccountStatus() {
		return userAccountStatus;
	}

	public void setUserAccountStatus(UserAccountStatus userAccountStatus) {
		this.userAccountStatus = userAccountStatus;
	}

	@ManyToMany
	@JoinTable(name="EVENT_USERS",
			joinColumns=@JoinColumn(name="USER_ID", referencedColumnName="USER_ID"),
			inverseJoinColumns=@JoinColumn(name="EVENT_ID", referencedColumnName="EVENT_ID"))
	private List<Event> userEvents;
	
	@ManyToMany
	@JoinTable(name="USER_SPORTS",
			joinColumns=@JoinColumn(name="USER_ID", referencedColumnName="USER_ID"),
			inverseJoinColumns=@JoinColumn(name="SPORT_ID", referencedColumnName="SPORT_ID"))
	private List<Sport> userSports;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "APP_USER_USER_PROFILE",
			joinColumns = { @JoinColumn(name = "USER_ID") },
			inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
	private List<UserProfile> userProfiles = new LinkedList<UserProfile>();
	
	public List<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(List<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

	public List<Sport> getUserSports() {
		return userSports;
	}

	public void setUserSports(List<Sport> userSports) {
		this.userSports = userSports;
	}

	public List<Mark> getEvaluatedMarks() {
		return evaluatedMarks;
	}

	public void setEvaluatedMarks(List<Mark> evaluatedMarks) {
		this.evaluatedMarks = evaluatedMarks;
	}

	public List<Mark> getEvaluativeMarks() {
		return evaluativeMarks;
	}

	public void setEvaluativeMarks(List<Mark> evaluativeMarks) {
		this.evaluativeMarks = evaluativeMarks;
	}

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
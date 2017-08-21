package lodz.uni.portal.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
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
	@Size(min=8, max=15)
    private String nickname;
    
    @Column(name="FIRSTNAME", nullable=false)
	@Size(min=3, max=20)
    private String firstName;

    @Column(name="LASTNAME", nullable=false)
	@Size(min=3, max=20)
	private String lastName;
    
    @Column(name="EMAIL", nullable=false)
    @Email
	@Size(min=5, max=25)
    private String email;

    @Column(name="PASSWORD", nullable=false)
	@NotNull
    private String password;
    
    @Column(name="CONFIRMPASS")
	@Size(min=8, max=12)
    private String confirmPassword;

    @Column(name="DATE_OF_BIRTH", nullable=false)
    private Date dateOfBirth;

	@OneToMany(fetch = FetchType.LAZY, mappedBy ="userCreator", cascade = CascadeType.ALL)
	private List<Event> createdEvents;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "evaluatedUser", cascade = CascadeType.ALL)
    private List<Mark> evaluatedMarks = new LinkedList<>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "evaluativeUser", cascade = CascadeType.ALL)
    private List<Mark> evaluativeMarks = new LinkedList<>();
    
    @ManyToOne(fetch = FetchType.LAZY)
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
	private List<UserProfile> userProfiles = new LinkedList<>();

	public List<Event> getCreatedEvents() {
		return createdEvents;
	}

	public void setCreatedEvents(List<Event> createdEvents) {
		this.createdEvents = createdEvents;
	}

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		PortalUser that = (PortalUser) o;

		return new EqualsBuilder()
				.append(id, that.id)
				.append(nickname, that.nickname)
				.append(email, that.email)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(id)
				.append(nickname)
				.append(email)
				.toHashCode();
	}
}
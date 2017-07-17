package lodz.uni.portal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="GAMES")
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="GAME_ID")
	private Integer id;
	
	@Column(name="DESCRIPTION")
	private String desc;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "HOST_USER")
	private PortalUser hostUser;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "GUEST_USER")
	private PortalUser guestUser;
	
	@Column(name = "CONFIRMATION")
	private Boolean confirm;
	
	@Column(name = "HOST_RESULT")
	private Integer hostResult;
	
	@Column(name = "GUEST_RESULT")
	private Integer guestResult;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="EVENT_ID")
	private Event event;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public PortalUser getHostUser() {
		return hostUser;
	}

	public void setHostUser(PortalUser hostUser) {
		this.hostUser = hostUser;
	}

	public PortalUser getGuestUser() {
		return guestUser;
	}

	public void setGuestUser(PortalUser guestUser) {
		this.guestUser = guestUser;
	}

	public Boolean getConfirm() {
		return confirm;
	}

	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}

	public Integer getHostResult() {
		return hostResult;
	}

	public void setHostResult(Integer hostResult) {
		this.hostResult = hostResult;
	}

	public Integer getGuestResult() {
		return guestResult;
	}

	public void setGuestResult(Integer guestResult) {
		this.guestResult = guestResult;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", desc=" + desc + ", hostUser=" + hostUser + ", guestUser=" + guestUser
				+ ", confirm=" + confirm + ", hostResult=" + hostResult + ", guestResult=" + guestResult + ", event="
				+ event + "]";
	}
	
}

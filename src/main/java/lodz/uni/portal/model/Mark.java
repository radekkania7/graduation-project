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
import javax.persistence.Table;

@Entity
@Table(name="MARK")
public class Mark implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MARK_ID")
	private Integer id;
	
	@JoinColumn(name="EVALUATIVE_USER_ID")
	@ManyToOne(fetch=FetchType.LAZY)
	private PortalUser evalutiveUser;
	
	@JoinColumn(name="EVALUATED_USER_ID")
	@ManyToOne(fetch=FetchType.LAZY)
	private PortalUser evaluatedUser;
	
	@Column(name="VALUE")
	private Integer value;
	
	@JoinColumn(name="EVENT_ID")
	@ManyToOne(fetch=FetchType.LAZY)
	private Event event;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PortalUser getEvalutiveUser() {
		return evalutiveUser;
	}

	public void setEvalutiveUser(PortalUser evalutiveUser) {
		this.evalutiveUser = evalutiveUser;
	}

	public PortalUser getEvaluatedUser() {
		return evaluatedUser;
	}

	public void setEvaluatedUser(PortalUser evaluatedUser) {
		this.evaluatedUser = evaluatedUser;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
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
		Mark other = (Mark) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
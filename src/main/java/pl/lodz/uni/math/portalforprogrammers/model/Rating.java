package pl.lodz.uni.math.portalforprogrammers.model;

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
@Table(name="RATINGS")
public class Rating implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RATING_ID")
	private Integer id;
	
	@Column(name="VALUE")
	private Integer value;
	
	@Column(name="DESCRIPTION")
	private String desc;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="EVALUATOR_USER_ID")
	private PortalUser evaluator;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RATED_USER_ID")
	private PortalUser rated;
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public PortalUser getEvaluator() {
		return evaluator;
	}

	public void setEvaluator(PortalUser evaluator) {
		this.evaluator = evaluator;
	}

	public PortalUser getRated() {
		return rated;
	}

	public void setRated(PortalUser rated) {
		this.rated = rated;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}

package lodz.uni.portal.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER_ACCOUNT_STATUS")
public class UserAccountStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="TYPE", unique=true)
	private String type;
	
	@OneToMany(mappedBy="userAccountStatus")
	private List<PortalUser> usersWithStatus = new LinkedList<PortalUser>();

	public List<PortalUser> getUsersWithThisStatus() {
		return usersWithStatus;
	}

	public void setUsersWithThisStatus(List<PortalUser> usersWithStatus) {
		this.usersWithStatus = usersWithStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

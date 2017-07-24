package lodz.uni.portal.dao;

import java.util.List;

import lodz.uni.portal.model.PortalUser;

public interface PortalUserDao {
	public PortalUser findByUsername(String username);
	
	public void save(PortalUser user);

	public PortalUser findById(Integer id);

	public void updateUser(PortalUser user);
	
	public List<PortalUser> findAllUsers(String ascOrderColumnName);

	public PortalUser getUserByEmail(String email);
}

package pl.lodz.uni.math.portalforprogrammers.dao;

import pl.lodz.uni.math.portalforprogrammers.model.PortalUser;

public interface PortalUserDao {
	public PortalUser findByUsername(String username);
	
	public void save(PortalUser user);

	public PortalUser findById(Integer id);
}

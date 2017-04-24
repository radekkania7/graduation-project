package pl.lodz.uni.math.portalforprogrammers.service;

import pl.lodz.uni.math.portalforprogrammers.model.PortalUser;

public interface UserService {
	public PortalUser findUserByUsername(String username);
	
	public void save(PortalUser user);
}

package pl.lodz.uni.math.portalforprogrammers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.lodz.uni.math.portalforprogrammers.dao.PortalUserDao;
import pl.lodz.uni.math.portalforprogrammers.model.PortalUser;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PortalUserDao userDao;

	@Override
	public PortalUser findUserByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public void save(PortalUser user) {
		userDao.save(user);
	}

}

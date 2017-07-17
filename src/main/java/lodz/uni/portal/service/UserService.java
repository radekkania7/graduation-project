package lodz.uni.portal.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lodz.uni.portal.dao.PortalUserDao;
import lodz.uni.portal.model.PortalUser;

@Service("userService")
@Transactional
public class UserService {
	private static final Logger logger = Logger.getLogger(UserService.class);
	
	@Autowired
	private PortalUserDao userDao;
	
	public PortalUser findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	public List<PortalUser> findAllActiveUsers(String ascOrderColumnName) {
		return userDao.findAllUsers(ascOrderColumnName);
	}
}

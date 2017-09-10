package lodz.uni.portal.service;

import java.util.List;

import lodz.uni.portal.dao.EventDao;
import lodz.uni.portal.model.Event;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
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

	@Autowired
	private EventDao eventDao;
	
	public PortalUser findByUsername(String username) {
		PortalUser user = userDao.findByUsername(username);
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public PortalUser findById(Integer id) {
		return userDao.findById(id);
	}

	public void update(PortalUser user) {
		userDao.updateUser(user);
	}

	public void save(PortalUser user) {
		userDao.save(user);
	}

	public boolean isNicknameUnique(String username) {
		PortalUser user = userDao.findByUsername(username);
		return (user != null);
	}

	public boolean isEmailUnique(String email) {
		PortalUser user = userDao.getUserByEmail(email);
		return (user != null);
	}

	public List<PortalUser> findAllActiveUsers(String ascOrderColumnName) {
		return userDao.findAllUsers(ascOrderColumnName);
	}

	public List<Event> getEventsByStatusForUser(String nickname, String statusType, Integer limit) {
		return eventDao.getEventsByStatusAndLimit(nickname, statusType, limit);
	}
}

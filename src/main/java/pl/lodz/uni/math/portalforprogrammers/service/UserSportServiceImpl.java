package pl.lodz.uni.math.portalforprogrammers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.lodz.uni.math.portalforprogrammers.dao.UserSportDao;
import pl.lodz.uni.math.portalforprogrammers.model.UserSport;

@Service("userSportService")
@Transactional
public class UserSportServiceImpl implements UserSportService {
	
	@Autowired
	private UserSportDao dao;

	@Override
	public void save(UserSport userSport) {
		dao.save(userSport);
	}

	@Override
	public UserSport findById(Integer id) {
		return dao.findById(id);
	}

}

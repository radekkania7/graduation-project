package pl.lodz.uni.math.portalforprogrammers.service;

import org.springframework.beans.factory.annotation.Autowired;

import pl.lodz.uni.math.portalforprogrammers.dao.UserSportDao;
import pl.lodz.uni.math.portalforprogrammers.model.UserSport;


public class UserSportServiceImpl implements UserSportService {
	
	@Autowired
	UserSportDao dao;

	@Override
	public void save(UserSport userSport) {
		dao.save(userSport);
	}

}

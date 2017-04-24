package pl.lodz.uni.math.portalforprogrammers.dao;

import org.springframework.stereotype.Repository;

import pl.lodz.uni.math.portalforprogrammers.model.UserSport;

@Repository("userSportDao")
public class UserSportDaoImpl extends AbstracDao<Integer, UserSport> implements UserSportDao{

	@Override
	public void save(UserSport userSport) {
		persist(userSport);
	}

}

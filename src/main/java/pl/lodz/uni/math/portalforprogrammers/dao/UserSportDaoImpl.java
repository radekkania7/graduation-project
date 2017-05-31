package pl.lodz.uni.math.portalforprogrammers.dao;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import pl.lodz.uni.math.portalforprogrammers.model.UserSport;

@Repository("userSportDao")
public class UserSportDaoImpl extends AbstracDao<Integer, UserSport> implements UserSportDao{
	
	@Override
	public void save(UserSport userSport) {
		persist(userSport);
	}
	
	public UserSport findById(Integer id) {
		UserSport userSport = getByKey(id);
		if (userSport != null) {
			Hibernate.initialize(userSport.getUser().getUserEvents());
			Hibernate.initialize(userSport.getUser());
			Hibernate.initialize(userSport.getSport().isTeamSport());
			Hibernate.initialize(userSport.getSport());
		}
		return userSport;
	}

}

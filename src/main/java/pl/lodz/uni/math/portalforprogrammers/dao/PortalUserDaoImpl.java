package pl.lodz.uni.math.portalforprogrammers.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import pl.lodz.uni.math.portalforprogrammers.model.PortalUser;

@Repository("userDao")
public class PortalUserDaoImpl extends AbstracDao<Integer, PortalUser> implements PortalUserDao {

	@Override
	public PortalUser findByUsername(String username) {
		Criteria criteria = getEntityCriteria();
		criteria.add(Restrictions.eq("nickname", username));
		PortalUser user = (PortalUser) criteria.uniqueResult();
		
		if (user != null) {
			Hibernate.initialize(user.getUserSports());
			Hibernate.initialize(user.getUserEvents());
		}
		
		return user;
	}

	@Override
	public void save(PortalUser user) {
		persist(user);
	}

	@Override
	public PortalUser findById(Integer id) {
		return getByKey(id);
	}

}

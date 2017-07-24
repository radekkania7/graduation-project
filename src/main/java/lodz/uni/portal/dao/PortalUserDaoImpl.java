package lodz.uni.portal.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import lodz.uni.portal.model.Event;
import lodz.uni.portal.model.PortalUser;

@Repository("userDao")
public class PortalUserDaoImpl extends BaseDao<Integer, PortalUser> implements PortalUserDao {

	@Override
	public PortalUser findByUsername(String username) {
		Criteria criteria = getEntityCriteria();
		criteria.add(Restrictions.eq("nickname", username));
		PortalUser user = (PortalUser) criteria.uniqueResult();
		
		if (user != null) {
			Hibernate.initialize(user.getUserSports());
			Hibernate.initialize(user.getUserEvents());
			Hibernate.initialize(user.getEvaluativeMarks());
		}
		return user;
	}

	@Override
	public void save(PortalUser user) {
		persist(user);
	}

	@Override
	public PortalUser findById(Integer id) {
		PortalUser user = getByKey(id);
		if (user != null) {
			Hibernate.initialize(user.getUserEvents());
			for (Event e : user.getUserEvents()) {
				Hibernate.initialize(e.getEventUsers());
				Hibernate.initialize(e.getEventMarks());
			}
			Hibernate.initialize(user.getEvaluatedMarks());
		}
		return user;
	}

	@Override
	public void updateUser(PortalUser user) {
		super.update(user);
	}
	
	public List<PortalUser> findAllUsers(String ascOrderColumnName) {
		return super.getAllElements(ascOrderColumnName);
	}

	@Override
	public PortalUser getUserByEmail(String propertyValue) {
		return getByField("email", propertyValue);
	}

}
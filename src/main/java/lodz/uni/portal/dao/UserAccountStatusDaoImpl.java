package lodz.uni.portal.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import lodz.uni.portal.model.UserAccountStatus;

@Repository("userAccountStatusDao")
public class UserAccountStatusDaoImpl extends BaseDao<Integer, UserAccountStatus> implements UserAccountStatusDao {
	
	public UserAccountStatus findByType(String type) {
		Criteria criteria = getEntityCriteria();
		criteria.add(Restrictions.eq("type", type));
		UserAccountStatus status = (UserAccountStatus) criteria.uniqueResult();
		
		if (status != null) {
			Hibernate.initialize(status.getUsersWithThisStatus());
		}
		
		return status;
	}
}

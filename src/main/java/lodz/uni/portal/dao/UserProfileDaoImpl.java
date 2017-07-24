package lodz.uni.portal.dao;

import lodz.uni.portal.model.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("userProfileDao")
public class UserProfileDaoImpl extends BaseDao<Integer, UserProfile> implements UserProfileDao{

    @Override
    public UserProfile getProfileByType(String type) {
        Criteria criteria = getEntityCriteria();
        criteria.add(Restrictions.eq("type", type));
        UserProfile profile = (UserProfile) criteria.uniqueResult();
        return profile;
    }
}

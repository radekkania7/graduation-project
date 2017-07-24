package lodz.uni.portal.service;

import lodz.uni.portal.dao.UserProfileDao;
import lodz.uni.portal.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileDao userProfileDao;

    public UserProfile getProfileByType(String type) {
        return userProfileDao.getProfileByType(type);
    }

}

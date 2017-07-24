package lodz.uni.portal.dao;

import lodz.uni.portal.model.UserProfile;

public interface UserProfileDao {

    public UserProfile getProfileByType(String type);
}

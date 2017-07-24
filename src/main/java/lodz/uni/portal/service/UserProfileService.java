package lodz.uni.portal.service;

import lodz.uni.portal.model.UserProfile;

public interface UserProfileService {

    public UserProfile getProfileByType(String type);
}

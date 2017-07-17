package lodz.uni.portal.dao;

import lodz.uni.portal.model.UserAccountStatus;

public interface UserAccountStatusDao {
	public UserAccountStatus findByType(String type);
}

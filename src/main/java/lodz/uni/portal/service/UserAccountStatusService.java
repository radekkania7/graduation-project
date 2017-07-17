package lodz.uni.portal.service;

import lodz.uni.portal.model.UserAccountStatus;

public interface UserAccountStatusService {
	public UserAccountStatus getStatusByType(String type);
}

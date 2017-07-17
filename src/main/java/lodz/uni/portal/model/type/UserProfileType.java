package lodz.uni.portal.model.type;

import java.io.Serializable;

public enum UserProfileType implements Serializable {
	USER("USER"),
	ACTIVE_ACCOUNT("ACTIVE"),
	ADMIN("ADMIN");
	
	String userProfileType;
	
	private UserProfileType(String userProfileType) {
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType() {
		return userProfileType;
	}
}

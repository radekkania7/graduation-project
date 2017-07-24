package lodz.uni.portal.model.type;

import java.io.Serializable;

public enum UserProfileType implements Serializable {
	USER("USER"),
	ADMIN("ADMIN");
	
	String userProfileType;
	
	UserProfileType(String userProfileType) {
		this.userProfileType = userProfileType;
	}
	
	public String getType() {
		return userProfileType;
	}
}

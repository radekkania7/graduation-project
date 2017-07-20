package lodz.uni.portal.model.type;

public enum UserAccountStatusType {
	CREATED("CREATED"),
	ACTIVE("ACTIVE"),
	DISABLE("DISABLE");
	
	String accountStatusType;
	
	private UserAccountStatusType(String accountStatusType) {
		this.accountStatusType = accountStatusType;
	}
	
	public String getType() {
		return accountStatusType;
	}
}

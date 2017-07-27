package lodz.uni.portal.model.type;

public enum EventStatusType {
	CREATED("CREATED"),
	TODAYS_EVENT("TODAY"),
	INCOMING("INCOMING"),
	DIRECTLY_AFTER_EVENT("DIRECTLY_AFTER"),
	TIME_FOR_JUDGE_OPONENTS("TIME_FOR_JUDGE"),
	TIME_FOR_CONFIRM_RESULTS("TIME_FOR_CONFIRM"),
	CLOSED("CLOSED");
	
	String type;
	
	EventStatusType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}

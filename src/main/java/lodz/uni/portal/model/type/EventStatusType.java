package lodz.uni.portal.model.type;

public enum EventStatusType {
	CREATED("CREATED"),
	TODAY_UPCOMING("TODAY"),
	INCOMING("INCOMING"),
	DIRECTLY_AFTER_EVENT("DIRECTLY_AFTER"),
	TIME_FOR_JUDGE_OPONENTS("TIME_FOR_JUDGE"),
	TIME_FOR_CONFIRM_RESULTS("TIME_FOR_CONFIRM"),
	CLOSED("CLOSED");
	
	String eventStatusType;
	
	private EventStatusType(String eventStatusType) {
		this.eventStatusType = eventStatusType;
	}
	
	public String getEventStatusType() {
		return eventStatusType;
	}
}

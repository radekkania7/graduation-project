package lodz.uni.portal.model;

public enum EventStatus {
	NOT_ACTUAL(0), 
	UPCOMING(1),
	UPCOMING_TODAY(2),
	ONGOING(3),
	TO_MARK(4);
	
	Integer status;
	
	private EventStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getValue() {
		return this.status;
	}
}

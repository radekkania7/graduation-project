package lodz.uni.portal.model.type;

public enum EventStatusType {
	//po utworzeniu,
	CREATED("CREATED"),
	DURING("DURING"),
	//czas na oceny i dodawanie wynikow
	AFTER("AFTER"),
	//zamkniete
	CLOSED("CLOSED");
	
	String type;
	
	EventStatusType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}

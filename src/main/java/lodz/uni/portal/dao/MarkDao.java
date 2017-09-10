package lodz.uni.portal.dao;

import java.util.List;

import lodz.uni.portal.model.Mark;

public interface MarkDao {
	
	void saveMark(Mark mark);

	List<Mark> getMarkByParams(String evaluativeUsername, String evaluatedUser, Integer eventId);

	public List<Mark> getMarksByEvaluatedUserAndEvent(String evaluatedUser, Integer eventId);
}
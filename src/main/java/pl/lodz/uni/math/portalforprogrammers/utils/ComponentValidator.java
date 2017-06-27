package pl.lodz.uni.math.portalforprogrammers.utils;

import pl.lodz.uni.math.portalforprogrammers.model.Mark;

public class ComponentValidator {
	
	public static boolean isValidateMark(Mark mark) {
		
		if (mark.getEvaluatedUser() == null) {
			return false;
		} else if (mark.getEvalutiveUser() == null) {
			return false;
		} else if (mark.getEvent() == null) {
			return false;
		} else if (mark.getValue() == null) {
			return false;
		}
		
		return true;
	}
}

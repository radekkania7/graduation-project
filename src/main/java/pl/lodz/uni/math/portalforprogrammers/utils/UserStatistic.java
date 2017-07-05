package pl.lodz.uni.math.portalforprogrammers.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import pl.lodz.uni.math.portalforprogrammers.model.Event;
import pl.lodz.uni.math.portalforprogrammers.model.Mark;
import pl.lodz.uni.math.portalforprogrammers.model.PortalUser;
import pl.lodz.uni.math.portalforprogrammers.model.Sport;

public class UserStatistic {
	
	private static final Logger logger = Logger.getLogger(UserStatistic.class);
	
	private final PortalUser user;
	private int markCount;
	
	public UserStatistic(PortalUser user) {
		this.user = user;
	}
	
	public double getAvgBySport(Sport sport) {
		List<Mark> marks = user.getEvaluatedMarks();
	
		Double valuesSum = 0.0;
		Double avg = 0.0;
		int marksCount = 0;
		for (Mark m : marks) {
			Sport eventSport = m.getEvent().getEventSport();

			if (eventSport.equals(sport)) {
				valuesSum += m.getValue();
				marksCount++;
			}
		}
		
		if (marksCount != 0) {
			avg = valuesSum/marksCount;
		}
		
		this.markCount = marksCount;
		return avg;
	} 
	
	public Map<Event, Double> getMapAvgPerEeachEventAtSport(Sport sport) {
		Map<Event, Double> resultMap = new HashMap<Event, Double>();
		
		List<Event> events = user.getUserEvents();
		List<Event> filteredEvents = filterSportEvent(events, sport);
		for (Event e : filteredEvents) {
			Double avg = avarageMarks(e.getEventMarks());
			resultMap.put(e, avg);
		}
		return resultMap;
	}
	
	private List<Event> filterSportEvent(List<Event> events, Sport sport) {
		List<Event> results = new LinkedList<Event>();
		for (Event e : events) {
			if (e.getEventSport().equals(sport)) {
				results.add(e);
			}
		}
		return results;
	}
	
	private Double avarageMarks(List<Mark> marks) {
		Double sum = 0.0;
		Double avg = 0.0;
		int count = 0;
		
		for (Mark m : marks) {
			if (m.getEvaluatedUser().equals(user)) {
				sum += m.getValue();
			}
		}
		
		if (count != 0.0) {
			avg = sum/count;
		}
		
		return avg;
	}
	
	public int getMarkCount() {
		return markCount;
	}
}

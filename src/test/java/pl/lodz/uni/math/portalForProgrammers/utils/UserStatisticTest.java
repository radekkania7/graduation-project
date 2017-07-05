package pl.lodz.uni.math.portalForProgrammers.utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import pl.lodz.uni.math.portalforprogrammers.model.Event;
import pl.lodz.uni.math.portalforprogrammers.model.Mark;
import pl.lodz.uni.math.portalforprogrammers.model.PortalUser;
import pl.lodz.uni.math.portalforprogrammers.model.Sport;
import pl.lodz.uni.math.portalforprogrammers.utils.UserStatistic;

public class UserStatisticTest {
	
	private static final Logger logger = Logger.getLogger(UserStatisticTest.class);
	
	private PortalUser user;
	private Sport sportParam;
	private Sport sportMark;
	private List<Mark> marks;
	private UserStatistic statistic;
	
	@Before
	public void setUp() {
		prepareSportsMock();
		prepareMarkMocks();
		prepareUserMock();
		statistic = new UserStatistic(user);
	}
	
	@After
	public void clean() {
		user = null;
		marks = null;
		sportMark = null;
		sportParam = null;
		statistic = null;
	}
	
	private void prepareUserMock() {
		user = Mockito.mock(PortalUser.class);
		Mockito.when(user.getEvaluatedMarks()).thenReturn(marks);
	}
	
	private void prepareMarkMocks() {
		marks = new LinkedList<Mark>();
		for (int i = 0; i < 5; i++) {
			marks.add(Mockito.mock(Mark.class));
		}
		
		int value = 0;
		for (Mark m : marks) {
			Mockito.when(m.getValue()).thenReturn(value);
			Event e = Mockito.mock(Event.class);
			Mockito.when(m.getEvent()).thenReturn(e);
			Mockito.when(e.getEventSport()).thenReturn(sportMark);
			value += 1;
		}
	}
	
	private void prepareSportsMock() {
		sportParam = Mockito.mock(Sport.class);
		sportMark = sportParam;
	}
	
	@Test
	public void shouldCalculateAvarage() {
		Double expected = 2.0;
		Double actual = statistic.getAvgBySport(sportParam);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void shouldNotReturnEmptyMap() {
		prepareMocksForTest();
		int expectedSize = 1;
		int actualSize = statistic.getMapAvgPerEeachEventAtSport(sportParam).size();
		Assert.assertEquals(expectedSize, actualSize);
	}
	
	private void prepareMocksForTest() {
		Mark mark1 = Mockito.mock(Mark.class);
		Mark mark2 = Mockito.mock(Mark.class);
		Mockito.when(mark1.getValue()).thenReturn(1);
		Mockito.when(mark2.getValue()).thenReturn(5);
		Mockito.when(mark1.getEvaluatedUser()).thenReturn(user);
		Mockito.when(mark2.getEvaluatedUser()).thenReturn(user);
		List<Mark> marks = Arrays.asList(mark1, mark2);
		
		Event event1 = Mockito.mock(Event.class);
		Mockito.when(event1.getEventMarks()).thenReturn(marks);
		Mockito.when(event1.getEventSport()).thenReturn(sportParam);
		
		List<Event> events = Arrays.asList(event1);
		Mockito.when(event1.getEventSport()).thenReturn(sportParam);
		Mockito.when(user.getUserEvents()).thenReturn(events);
	}
	
}

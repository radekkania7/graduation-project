package pl.lodz.uni.math.portalforprogrammers.utils;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class AppDateUtils {
	
	/**
	 * Metohod converts String to java.sql.Date;
	 * @param dateText
	 * @return java.sql.Date;
	 */
	public static Map<String, Object> getDateAndTime(String dateString) {
		Map<String, Object> map = new HashMap<String, Object>();
		String [] dataAndTime = dateString.split("T");
		java.sql.Date date = Date.valueOf(dataAndTime[0]);
		java.sql.Time time = Time.valueOf(dataAndTime[1]);
		map.put("date", date);
		map.put("time", time);
		return map;
	}
		
	public static Timestamp getTimestamp(String str) {
		String time = str.replace("T", " ");
		Timestamp stamp = Timestamp.valueOf(time);
		return stamp;
	}

	/**
	 * @return actual Date
	 * @throws ParseException 
	 */
	public static java.sql.Date getActualDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String dateToString = sdf.format(new java.util.Date());
		try {
			java.util.Date utilDate = sdf.parse(dateToString);
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			return sqlDate;
		} catch (ParseException e) {}
		return null;
	}
	
	public static java.sql.Date getDate(String date) {
		return null;
	}
	
}
	

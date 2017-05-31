package pl.lodz.uni.math.portalforprogrammers.service.converter;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToTimeConverter implements Converter<Object, java.sql.Time> {

	@Override
	public Time convert(Object element) {
		String time = (String) element;
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		try {
			long ms = sdf.parse(time).getTime();
			Time t = new Time(ms);
			return t;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}

package pl.lodz.uni.math.portalforprogrammers.service.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDateConverter implements Converter<Object, java.sql.Date> {

	@Override
	public java.sql.Date convert(Object element) {
		String date = (String) element;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date utilDate = sdf.parse(date);
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			return sqlDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}

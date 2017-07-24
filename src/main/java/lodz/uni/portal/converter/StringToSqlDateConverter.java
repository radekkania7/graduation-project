package lodz.uni.portal.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class StringToSqlDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd mm yyyy");
        LocalDate date = LocalDate.parse(source);
        return Date.valueOf(date);
    }
}

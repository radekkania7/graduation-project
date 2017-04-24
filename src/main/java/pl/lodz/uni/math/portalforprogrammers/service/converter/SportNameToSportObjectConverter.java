package pl.lodz.uni.math.portalforprogrammers.service.converter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pl.lodz.uni.math.portalforprogrammers.model.Sport;
import pl.lodz.uni.math.portalforprogrammers.service.SportService;

@Component
public class SportNameToSportObjectConverter implements Converter<Object, Sport> {
	
	Logger logger = Logger.getLogger(SportNameToSportObjectConverter.class);
	
	@Autowired
	private SportService service;
	
	@Override
	public Sport convert(Object element) {
		Integer id = Integer.parseInt((String)element);
		Sport sport = service.findSportById(id);
		logger.debug("Found sport with id=" + id + ", sportName=" + sport.getName());
		return sport;
	}
	
}

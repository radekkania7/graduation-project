package pl.lodz.uni.math.portalforprogrammers.service.converter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pl.lodz.uni.math.portalforprogrammers.model.Town;
import pl.lodz.uni.math.portalforprogrammers.service.TownService;

@Component
public class TownNameToTownObjectConverter implements Converter<Object, Town> {

	private Logger logger = Logger.getLogger(TownNameToTownObjectConverter.class);
	
	@Autowired
	private TownService service;
	
	@Override
	public Town convert(Object element) {
		Integer id = Integer.parseInt((String)element);
		Town town = service.findTownById(id);
		logger.debug("Found town with id=" + id + ", tonwName=" + town.getName());
		return town;
	}

}

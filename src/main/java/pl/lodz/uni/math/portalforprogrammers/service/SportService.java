package pl.lodz.uni.math.portalforprogrammers.service;

import java.util.List;

import pl.lodz.uni.math.portalforprogrammers.model.Sport;

public interface SportService {
	public void save(Sport sport);
	
	public List<Sport> findAllSports();
	
	public Sport findSportByName(String name);
	
	public Sport findSportById(Integer id);
}

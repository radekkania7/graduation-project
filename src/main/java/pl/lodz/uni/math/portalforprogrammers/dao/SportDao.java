package pl.lodz.uni.math.portalforprogrammers.dao;

import java.util.List;

import pl.lodz.uni.math.portalforprogrammers.model.Sport;

public interface SportDao {
	public void save(Sport sport);
	
	public List<Sport> findAll();
	
	public Sport findSportByName(String name);
	
	public Sport findSportById(Integer id);
}

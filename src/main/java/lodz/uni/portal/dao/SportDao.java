package lodz.uni.portal.dao;

import java.util.List;

import lodz.uni.portal.model.Sport;

public interface SportDao {
	public void save(Sport sport);
	
	public List<Sport> findAll();
	
	public Sport findSportByName(String name);
	
	public Sport findSportById(Integer id);
}

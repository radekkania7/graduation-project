package lodz.uni.portal.dao;

import java.util.List;

import lodz.uni.portal.model.Town;

public interface TownDao {
	public List<Town> findAllTowns();
	
	public Town findTownByName(String name);

	public Town findTownById(Integer id);

	public void save(Town town);
}

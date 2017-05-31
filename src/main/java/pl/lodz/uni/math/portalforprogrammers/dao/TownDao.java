package pl.lodz.uni.math.portalforprogrammers.dao;

import java.util.List;

import pl.lodz.uni.math.portalforprogrammers.model.Town;

public interface TownDao {
	public List<Town> findAllTowns();
	
	public Town findTownByName(String name);

	public Town findTownById(Integer id);

	public void save(Town town);
}

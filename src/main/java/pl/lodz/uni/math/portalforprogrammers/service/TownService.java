package pl.lodz.uni.math.portalforprogrammers.service;

import java.util.List;

import pl.lodz.uni.math.portalforprogrammers.model.Town;

public interface TownService {

	public List<Town> findAllTown();
	
	public Town getTownByName(String name);

	public Town findTownById(Integer id);
	
	public void save(Town town);
}

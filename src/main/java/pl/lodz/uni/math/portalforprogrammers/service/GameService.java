package pl.lodz.uni.math.portalforprogrammers.service;

import pl.lodz.uni.math.portalforprogrammers.model.Game;

public interface GameService {
	public void save(Game game);
	
	public Game findGameById(Integer id);
}

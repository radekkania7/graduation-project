package pl.lodz.uni.math.portalforprogrammers.dao;

import pl.lodz.uni.math.portalforprogrammers.model.Game;

public interface GameDao {
	public void save(Game game);

	public Game findById(Integer id);
}

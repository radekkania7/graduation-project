package lodz.uni.portal.dao;

import lodz.uni.portal.model.Game;

public interface GameDao {
	public void save(Game game);
	
	public void updateGame(Game game);

	public Game findById(Integer id);
}

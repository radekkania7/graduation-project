package pl.lodz.uni.math.portalforprogrammers.dao;

import org.springframework.stereotype.Repository;

import pl.lodz.uni.math.portalforprogrammers.model.Game;

@Repository("gameDao")
public class GameDaoImpl extends AbstracDao<Integer, Game> implements GameDao {

	@Override
	public void save(Game game) {
		persist(game);
	}

	@Override
	public Game findById(Integer id) {
		return getByKey(id);
	}

	@Override
	public void updateGame(Game game) {
		update(game);
	}

}

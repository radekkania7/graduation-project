package lodz.uni.portal.dao;

import org.springframework.stereotype.Repository;

import lodz.uni.portal.model.Game;

@Repository("gameDao")
public class GameDaoImpl extends BaseDao<Integer, Game> implements GameDao {

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

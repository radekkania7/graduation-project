package pl.lodz.uni.math.portalforprogrammers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.lodz.uni.math.portalforprogrammers.dao.GameDao;
import pl.lodz.uni.math.portalforprogrammers.model.Game;

@Repository("gameService")
@Transactional
public class GameServiceImpl implements GameService {
	
	@Autowired
	private GameDao gameDao;

	@Override
	public void save(Game game) {
		gameDao.save(game);
	}

	@Override
	public Game findGameById(Integer id) {
		return gameDao.findById(id);
	}

}

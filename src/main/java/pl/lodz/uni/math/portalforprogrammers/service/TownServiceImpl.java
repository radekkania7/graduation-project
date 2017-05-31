package pl.lodz.uni.math.portalforprogrammers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.lodz.uni.math.portalforprogrammers.dao.TownDao;
import pl.lodz.uni.math.portalforprogrammers.model.Town;


@Service("townService")
@Transactional
public class TownServiceImpl implements TownService {

	@Autowired
	private TownDao dao;
	
	@Override
	public List<Town> findAllTown() {
		return dao.findAllTowns();
	}

	@Override
	public Town getTownByName(String name) {
		return dao.findTownByName(name);
	}

	@Override
	public Town findTownById(Integer id) {
		return dao.findTownById(id);
	}

	@Override
	public void save(Town town) {
		dao.save(town);
	}

}

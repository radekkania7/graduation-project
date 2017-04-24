package pl.lodz.uni.math.portalforprogrammers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.lodz.uni.math.portalforprogrammers.dao.SportDao;
import pl.lodz.uni.math.portalforprogrammers.model.Sport;

@Service("sportService")
@Transactional
public class SportServiceImpl implements SportService {
	
	@Autowired
	private SportDao dao;

	@Override
	public void save(Sport sport) {
		dao.save(sport);
	}

	@Override
	public List<Sport> findAllSports() {
		return dao.findAll();
	}

	@Override
	public Sport findSportByName(String name) {
		return dao.findSportByName(name);
	}

	@Override
	public Sport findSportById(Integer id) {
		return dao.findSportById(id);
	}

}

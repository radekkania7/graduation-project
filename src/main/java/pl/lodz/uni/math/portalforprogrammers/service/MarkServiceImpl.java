package pl.lodz.uni.math.portalforprogrammers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.lodz.uni.math.portalforprogrammers.dao.MarkDao;
import pl.lodz.uni.math.portalforprogrammers.model.Mark;

@Repository("markService")
@Transactional
public class MarkServiceImpl implements MarkService {

	@Autowired
	MarkDao markDao;
	
	@Override
	public void save(Mark mark) {
		markDao.saveMark(mark);
	}

}

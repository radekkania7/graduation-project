package pl.lodz.uni.math.portalforprogrammers.dao;

import org.springframework.stereotype.Repository;

import pl.lodz.uni.math.portalforprogrammers.model.Mark;

@Repository("markDao")
public class MarkDaoImpl extends AbstracDao<Integer, Mark> implements MarkDao {

	@Override
	public void saveMark(Mark mark) {
		persist(mark);
	}
}

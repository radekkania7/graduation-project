package lodz.uni.portal.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import lodz.uni.portal.model.Mark;

@Repository("markDao")
public class MarkDaoImpl extends BaseDao<Integer, Mark> implements MarkDao {

	@Override
	public void saveMark(Mark mark) {
		persist(mark);
	}
	
	public void nic() {
		
	}

}

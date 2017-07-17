package lodz.uni.portal.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import lodz.uni.portal.model.Sport;

@Repository("sportDao")
public class SportDaoImpl extends BaseDao<Integer, Sport> implements SportDao {

	@Override
	public void save(Sport sport) {
		persist(sport);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Sport> findAll() {
		Criteria criteria = getEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Sport> sports = (List<Sport>) criteria.list();
		return sports;
	}

	@Override
	public Sport findSportByName(String name) {
		Criteria criteria = getEntityCriteria();
		criteria.add(Restrictions.eq("name", name));
		Sport sport = (Sport) criteria.uniqueResult();
		return sport;
	}

	@Override
	public Sport findSportById(Integer id) {
		return getByKey(id);
	}

}

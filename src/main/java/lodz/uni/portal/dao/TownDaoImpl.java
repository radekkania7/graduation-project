package lodz.uni.portal.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import lodz.uni.portal.model.Town;

@Repository("townDao")
public class TownDaoImpl extends BaseDao<Integer, Town> implements TownDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Town> findAllTowns() {
		Criteria criteria = getEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Town> towns = (List<Town>) criteria.list();
		return towns;
	}

	@Override
	public Town findTownByName(String name) {
		Criteria criteria = getEntityCriteria();
		criteria.add(Restrictions.eq("name", name));
		Town town = (Town) criteria.uniqueResult();
		return town;
	}

	@Override
	public Town findTownById(Integer id) {
		return getByKey(id);
	}

	@Override
	public void save(Town town) {
		persist(town);
	}

}

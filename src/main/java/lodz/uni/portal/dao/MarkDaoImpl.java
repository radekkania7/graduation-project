package lodz.uni.portal.dao;


import com.sun.org.apache.regexp.internal.RE;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import lodz.uni.portal.model.Mark;

import java.util.List;

@Repository("markDao")
public class MarkDaoImpl extends BaseDao<Integer, Mark> implements MarkDao {

	@Override
	public void saveMark(Mark mark) {
		persist(mark);
	}

	@Override
	public List<Mark> getMarkByParams(String evaluativeUsername, String evaluatedUsername, Integer eventId) {
		Criteria criteria = getEntityCriteria();

		criteria.createAlias("evaluativeUser", "user1Als");
		criteria.createAlias("evaluatedUser", "user2Als");
		criteria.createAlias("event", "eventAls");

		criteria.add(Restrictions.eq("user1Als.nickname", evaluativeUsername));
		criteria.add(Restrictions.eq("user2Als.nickname", evaluatedUsername));
		criteria.add(Restrictions.eq("eventAls.id", eventId));

		return (List<Mark>) criteria.list();
	}

	public List<Mark> getMarksByEvaluatedUserAndEvent(String evaluatedUser, Integer eventId) {
		Criteria criteria = getEntityCriteria();

		criteria.createAlias("evaluatedUser", "userAls");
		criteria.createAlias("event", "eventAls");

		criteria.add(Restrictions.eq("userAls.nickname", evaluatedUser));
		criteria.add(Restrictions.eq("eventAls.id", eventId));

		return (List<Mark>) criteria.list();
	}
}

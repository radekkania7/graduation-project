package lodz.uni.portal.dao;


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
		Criteria criteria = getSession().createCriteria(Mark.class, "mark");

		criteria.createAlias("mark.evaluativeUser.nickname", "evaluativeUser");
		criteria.createAlias("mark.evaluatedUser.nickname", "evaluatedUser");
		criteria.createAlias("mark.event.id", "eventId");

		Criterion evaluativeCriterion = Restrictions.eq("evaluativeUser", evaluatedUsername);
		Criterion evaluatedCriterion = Restrictions.eq("evaluatedUser", evaluatedUsername);
		Criterion eventCriterion = Restrictions.eq("eventId", eventId);

		Criterion subExpression = Restrictions.and(evaluatedCriterion, evaluativeCriterion);
		Criterion finalExpression = Restrictions.and(subExpression, eventCriterion);

		criteria.add(finalExpression);
		return (List<Mark>) criteria.list();
	}
}

package lodz.uni.portal.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDao<PK extends Serializable, T> {
	
	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public BaseDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}
	
	public void persist(T entity) {
		getSession().persist(entity);
	}
	
	public void update(T entity) {
		getSession().update(entity);
	}
	
	public void delete(T entity) {
		getSession().delete(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAllElements(String ascOrderColumnName) {
		Criteria criteria = getEntityCriteria().addOrder(Order.asc(ascOrderColumnName));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<T>) criteria.list();
	}

	public T getByField(String propertyName, String propertyValue) {
		Criteria criteria = getEntityCriteria();
		criteria.add(Restrictions.eq(propertyName, propertyValue));
		return (T) criteria.uniqueResult();
	}
	
	protected Criteria getEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}
}

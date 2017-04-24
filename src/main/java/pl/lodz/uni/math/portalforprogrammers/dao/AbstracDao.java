package pl.lodz.uni.math.portalforprogrammers.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstracDao<PK extends Serializable, T> {
	
	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstracDao() {
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
	
	protected Criteria getEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}
}

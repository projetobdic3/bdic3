package br.com.ita.bdic3.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

 
/**
 * Classe Genérica responsável por fornecer um CRUD básico para todas as classes que a estenderem.
 * @author Paulo Vitor Faria Fortes Rezende
 */
 
@Transactional(propagation=Propagation.REQUIRED)
public class GenericDao<PK extends Serializable, T> {

	private Class<T> type;
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public GenericDao(Class<T> type) {
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	public PK save(T o) {
		return (PK) getSession().save(o);
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public T findById(PK id) {
		T value = (T) getSession().get(type, id);
		if (value == null) {
            return null;
        }

        if (value instanceof HibernateProxy) {
			Hibernate.initialize(value);
	        value = (T) ((HibernateProxy) value).getHibernateLazyInitializer().getImplementation();
        }
        return value;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<T> findAll() {
		Criteria crit = getSession().createCriteria(type);
		return crit.list();
	}
	
	public void update(T o) {
		getSession().update(o);
	}

	public void delete(T o) {
		getSession().delete(o);
	}
	
	public void delete(PK pk) {
		Query deleteQuery = getSession().createQuery("DELETE FROM " + type.getName() + " WHERE id = :pk");
		deleteQuery.setParameter("pk", pk);
		deleteQuery.executeUpdate();
	}
	
	public Session getSession() {
	    return sessionFactory.openSession();
	}

	public void setSessionFactory(SessionFactory session) {
		this.sessionFactory = session;
	}
}
package br.com.codate.cadrural.util.generic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe generica para Data Access Object.
 * 
 * @author Charles
 * 
 * @param <ENTITY>
 *            Entidade que sera persistida
 */
@SuppressWarnings("unchecked")
public class GenericDAO<ENTITY> {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    private EntityManager entityManager;

    private Class<ENTITY> clazzBiz;

    public GenericDAO() {
    }

    public GenericDAO(Class<ENTITY> pclazzBiz, EntityManager em) {
	clazzBiz = pclazzBiz;
	entityManager = em;
    }

    public ENTITY insert(ENTITY entity) {
	EntityManager em = getEntityManager();
	entity = em.merge(entity);
	em.flush();
	logger.debug("Create successful");
	return (ENTITY) entity;
    }

    public ENTITY update(ENTITY entity) {
	EntityManager em = getEntityManager();
	entity = em.merge(entity);
	em.flush();
	logger.debug("Update successful");
	return (ENTITY) entity;
    }

    public void remove(Long pk) {
	EntityManager em = getEntityManager();
	em.remove(findById(pk));
	logger.debug("Remove successful");
    }

    public void detach(ENTITY entity) {
	EntityManager em = getEntityManager();
	em.detach(entity);
	logger.debug("Remove successful");
    }

    public ENTITY findById(Long pk) {
	return (ENTITY) getEntityManager().find(getClazzBiz(), pk);
    }

    public List<ENTITY> findAll() {
	String str = " FROM " + getClazzBiz().getName();
	List<ENTITY> list = getEntityManager().createQuery(str).getResultList();
	return list;
    }

    public List<ENTITY> queryList(String namedQuery, int numMax, int offset, Object... parameters) {
	EntityManager em = this.getEntityManager();
	Query query = em.createNamedQuery(namedQuery);
	int i = 1;
	if (parameters != null) {
	    for (Object object : parameters) {
		if (object != null) {
		    query.setParameter(i++, object);
		}
	    }
	}
	query.setFirstResult(offset);
	query.setMaxResults(numMax);
	return query.getResultList();
    }

    public List<ENTITY> queryList(String namedQuery, Object... parameters) {
	EntityManager em = this.getEntityManager();
	Query query = em.createNamedQuery(namedQuery);
	if (parameters != null) {
	    int i = 1;
	    for (Object object : parameters) {
		if (object != null) {
		    query.setParameter(i++, object);
		}
	    }
	}
	List<ENTITY> listaEntity = query.getResultList();
	return listaEntity;
    }

    public ENTITY querySingle(String namedQuery, Object... parameters) {
	try {
	    EntityManager em = this.getEntityManager();
	    Query query = em.createNamedQuery(namedQuery);

	    if (parameters != null) {
		int i = 1;
		for (Object object : parameters) {
		    if (object != null) {
			query.setParameter(i++, object);
		    }
		}
	    }
	    query.setMaxResults(1);
	    ENTITY entity = (ENTITY) query.getSingleResult();
	    return entity;
	} catch (NoResultException e) {
	    return null;
	}
    }

    public void executeUpdate(String namedQuery, Object... parameters) {
	EntityManager em = this.getEntityManager();
	Query query = em.createNamedQuery(namedQuery);

	if (parameters != null) {
	    int i = 1;
	    for (Object object : parameters) {
		if (object != null) {
		    query.setParameter(i++, object);
		}
	    }
	}
	query.executeUpdate();
    }

    public Long count() {
	Query query = getEntityManager().createQuery("SELECT COUNT(entity) " + "FROM " + getClazzBiz().getName() + " entity ");
	return (Long) query.getSingleResult();
    }

    public EntityManager getEntityManager() {
	return entityManager;
    }

    public void setEntityManager(EntityManager pEntityManager) {
	this.entityManager = pEntityManager;
    }

    public Class<ENTITY> getClazzBiz() {
	return clazzBiz;
    }

    public void setClazzBiz(Class<ENTITY> pClazzBiz) {
	this.clazzBiz = pClazzBiz;
    }

}
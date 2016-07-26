package com.applicatiobot.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.applicationbot.util.HibernateUtil;

//@Component
public class DAO{

    //@Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    public DAO() {
    	sessionFactory = HibernateUtil.getSessionFactory();
    	//sessionFactory.openSession();
    }

    @SuppressWarnings("unchecked")
	public <T> T save(final T o){
    	Session sess = sessionFactory.openSession();
   	 Transaction tx = null;
   	 try {
   	     tx = sess.beginTransaction();
   	     sess.save(o);
   	     tx.commit();
   	 }
   	 catch (Exception e) {
   	     if (tx!=null) tx.rollback();
   	     throw e;
   	 }
   	 finally {
   	     sess.close();
   	 }
   	 return o;
    }


    public void delete(final Object object){
      sessionFactory.getCurrentSession().delete(object);
    }

    /***/
    @SuppressWarnings("unchecked")
	public <T> T get(final Class<T> type, final String id){
    	Session sess = sessionFactory.openSession();
      T t= (T) sess.get(type, id);
      sess.close();
      return t;
    }

    /***/
    @SuppressWarnings("unchecked")
	public <T> T merge(final T o)   {
      return (T) sessionFactory.getCurrentSession().merge(o);
    }

    /***/
    public <T> void saveOrUpdate(final T o){
    	Session sess = sessionFactory.openSession();
    	 Transaction tx = null;
    	 try {
    	     tx = sess.beginTransaction();
    	     sess.saveOrUpdate(o);
    	     tx.commit();
    	 }
    	 catch (Exception e) {
    	     if (tx!=null) tx.rollback();
    	     throw e;
    	 }
    	 finally {
    	     sess.close();
    	 }
      
    }

    @SuppressWarnings("unchecked")
	public <T> List<T> getAll(final Class<T> type) {
      final Session session = sessionFactory.getCurrentSession();
      final Criteria crit = session.createCriteria(type);
  return crit.list();
    }
    
}

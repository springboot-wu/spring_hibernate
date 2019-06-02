package com.test.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class BaseDao<T> extends HibernateDaoSupport {

    @Autowired
    private SessionFactory sessionFactory;

    Class<T> clazz;

    @Autowired
   public void setSessionFactoryOverride(){
       super.setSessionFactory(sessionFactory);
   }

    public BaseDao() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz  = (Class<T>) pt.getActualTypeArguments()[0];
    }
    public void save(){
        this.getHibernateTemplate().save(clazz);
    }
    public void update(){
        this.getHibernateTemplate().update(clazz);
    }

    public void delete(Serializable id){
        this.getHibernateTemplate().delete(findObjectById(id));
    }

   public T findObjectById(Serializable id){

        return (T) getHibernateTemplate().get(clazz, id);
   }
  /* public List<T> findObjects(){
       System.out.println("baseDao 打印 "+ clazz);
       Query query = getSession().createQuery("from" + clazz.getSimpleName());
       return query.list();
   }*/



}

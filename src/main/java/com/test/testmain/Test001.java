package com.test.testmain;

import com.hibernate.bean.EmpEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.util.List;

public class Test001 {


    @Test
    public void test(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("dataSource-all.xml");
        SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
        Connection connection = null;
        try {
            String hql = "select e from EmpEntity e ";
            Session session = sessionFactory.openSession();
            Query query = session.createQuery(hql);
            List<EmpEntity> list = query.list();
            for (EmpEntity e:list){
                System.out.println(e.getEname());
            }
            System.out.println(list);

        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}

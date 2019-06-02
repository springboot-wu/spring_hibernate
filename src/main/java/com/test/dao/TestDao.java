package com.test.dao;

import com.hibernate.bean.EmpEntity;
import com.test.utils.PagingQueryResult;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.*;

@Repository
public class TestDao extends BaseDao<EmpEntity> {

    @Override
    public void save() {
        super.save();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void delete(Serializable id) {
        super.delete(id);
    }

    @Override
    public EmpEntity findObjectById(Serializable id) {
        return super.findObjectById(id);
    }

  /*  @Override
    public List<EmpEntity> findObjects() {
        System.out.println("进入 testDao 层");
        return super.findObjects();
    }*/

    // 使用hql  查询 list 集合

    public PagingQueryResult queryEmpList() {

      return  this.getHibernateTemplate().execute(new HibernateCallback<PagingQueryResult>() {
            public PagingQueryResult doInHibernate(Session session) throws HibernateException {
                PagingQueryResult result = new PagingQueryResult();
                StringBuffer sqlsb = new StringBuffer("select e.ename ,e.job,e.mgr from  EmpEntity e");
                StringBuffer sqlcount = new StringBuffer("select count(1) from EmpEntity e");
                Map conditions = new HashMap();
                StringBuffer sql = new StringBuffer();
                // 增加判断条件 这里就直接写了  7698
                sql.append(" e.mgr=:mgr");
                conditions.put("mgr", 7698);

                if (sql.length() > 0) {
                    sqlsb.append(" where").append(sql);
                    sqlcount.append(" where").append(sql);
                }
                String sqlStr = sqlsb.toString();
                String sqlcountStr = sqlcount.toString();
                Query query = session.createQuery(sqlStr);

                // 此处可以设置分页
                //query.setFirstResult();
                //query.setMaxResults();
                Iterator it = conditions.entrySet().iterator();
                List pagingResult = new ArrayList<EmpEntity>();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    String key = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        query.setString(key, (String) value);
                    }else if(value instanceof  Integer){
                        query.setInteger(key, (Integer) value);
                    }else if(value instanceof  Long){
                        query.setLong(key, (Long) value);
                    }
                    // 编写其它的
                    Iterator iterator = query.list().iterator();
                    while (iterator.hasNext()) {
                        Object[] empObject = (Object[]) iterator.next();
                        EmpEntity empEntity = new EmpEntity();
                        empEntity.setEname((String) empObject[0]);
                        empEntity.setJob((String) empObject[1]);
                        empEntity.setMgr((Long) empObject[2]);
                        pagingResult.add(empEntity);
                    }
                    result.setPagingResult(pagingResult);

             /*       // 查询数量的待补充
                   session.createQuery(sqlcountStr);*/
                }
                return result;
            }
        });
    }

}

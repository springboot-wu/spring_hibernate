package com.test.service;

import com.hibernate.bean.EmpEntity;
import com.test.dao.JdbcBaseDao;
import com.test.dao.TestDao;
import com.test.utils.PagingQueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    Logger  log = LoggerFactory.getLogger(TestService.class);
    @Autowired
    private TestDao testDao;

    @Autowired
    private JdbcBaseDao jdbcBaseDao;

    public PagingQueryResult testList() {

         log.info("进入 testList方法中了////");

        List<EmpEntity> list = new ArrayList<EmpEntity>();
        long a = 7499;
        EmpEntity objectById = testDao.findObjectById(a);
        EmpEntity emp = jdbcBaseDao.findEmp(a);
        System.out.println("打印emp的值"+emp.getEname());
        PagingQueryResult pagingQueryResult = testDao.queryEmpList();
        list.add(objectById);
        list.add(emp);
        return pagingQueryResult;

    }
}

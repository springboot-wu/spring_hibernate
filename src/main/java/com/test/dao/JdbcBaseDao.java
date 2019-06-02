package com.test.dao;

import com.hibernate.bean.EmpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcBaseDao extends JdbcDaoSupport {


    @Autowired
    private DataSource dataSource;

    @Autowired
    private  void setDataSourcetest(DataSource dataSource){
        System.out.println("打印datasource:"+dataSource);
        super.setDataSource(dataSource);
    }


    public EmpEntity findEmp(Long id) {

        EmpEntity empEntity = null;

        empEntity = (EmpEntity) this.getJdbcTemplate()
                .execute("select * from emp where empno=" + id, new PreparedStatementCallback() {
                    public Object doInPreparedStatement(PreparedStatement p) throws SQLException, DataAccessException {
                        ResultSet resultSet = null;
                        EmpEntity empEntity = new EmpEntity();
                        try {
                            resultSet = p.executeQuery();
                            while (resultSet.next()) {
                                empEntity.setEmpno(resultSet.getLong("empno"));
                                empEntity.setEname(resultSet.getString("ename"));
                            }

                        } catch (SQLException e) {

                        } finally {
                            try {
                                if (resultSet != null) {
                                    resultSet.close();
                                }
                            } catch (Exception e) {
                                System.out.println("程序异常了！！！！");
                            }
                        }
                        System.out.println("返回之前打印empEntity"+empEntity.getEname());
                        return empEntity;
                    }
                });
        return empEntity;
    }
}

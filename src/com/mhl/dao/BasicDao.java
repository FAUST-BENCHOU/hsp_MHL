package com.mhl.dao;


import com.mhl.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.util.List;

/**
 * @author FAUST
 * @version 1.0
 * @date 2023/6/17 16:29
 */
public class BasicDao<T> {
    private QueryRunner queryRunner=new QueryRunner();
    public int update(String sql,Object... parameters) {
        Connection connection=null;
        try {
            connection= JDBCUtilsByDruid.getConnection();
            int update=queryRunner.update(connection,sql,parameters);
            return update;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }
    }

    public List<T> queryMulti(String sql, Class<T> tClass, Object... parameters){
        Connection connection=null;
        try {
            connection= JDBCUtilsByDruid.getConnection();
            return queryRunner.query(connection,sql,new BeanListHandler<T>(tClass),parameters);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }
    }

    public T querySingle(String sql, Class<T> tClass, Object... parameters){
        Connection connection=null;
        try {
            connection= JDBCUtilsByDruid.getConnection();
            return queryRunner.query(connection,sql,new BeanHandler<T>(tClass),parameters);


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }

    }

    public Object queryScalar(String sql,  Object... parameters){
        Connection connection=null;
        try {
            connection= JDBCUtilsByDruid.getConnection();
            return queryRunner.query(connection,sql,new ScalarHandler<>(),parameters);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }

    }

}

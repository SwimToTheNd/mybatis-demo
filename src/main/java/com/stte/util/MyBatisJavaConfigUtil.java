package com.stte.util;

import com.stte.mapper.ClassStudentMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * create by BloodFly at 2020/7/1
 */
public class MyBatisJavaConfigUtil {


    private static SqlSessionFactory sqlSessionFactory;


    public static SqlSessionFactory getSqlSessfactory() {
        if (sqlSessionFactory == null) {
            try {
                sqlSessionFactory = buildSqlSessionFactory();
            } catch (IOException e) {
                System.out.println("%%% Error create SqlSessionFactory  %%%");
                e.printStackTrace();
            }
        }
        return sqlSessionFactory;
    }

    private static SqlSessionFactory buildSqlSessionFactory() throws IOException {
        // 1. 构建Environment对象 需要id, TransactionFactory, DataSource
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        DataSource dataSource = new PooledDataSource();
        Properties properties = new Properties();
        InputStream resourceAsStream = Object.class.getResourceAsStream("/db.properties");
        properties.load(resourceAsStream);
        ((PooledDataSource) dataSource).setDriver(properties.getProperty("dataSource.driverClass"));
        ((PooledDataSource) dataSource).setUrl(properties.getProperty("dataSource.jdbcUrl"));
        ((PooledDataSource) dataSource).setUsername(properties.getProperty("dataSource.user"));
        ((PooledDataSource) dataSource).setPassword(properties.getProperty("dataSource.password"));
        Environment environment = new Environment("dev", transactionFactory, dataSource);
        // 2. 根据Environment构建Configuration对象
        Configuration configuration = new Configuration(environment);
        configuration.setLogImpl(Log4jImpl.class);
//        configuration.addMappers("com.stte.mapper");
        configuration.addMapper(ClassStudentMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }

    public static SqlSession getSqlSession() {
        return getSqlSessfactory().openSession();
    }

    public static SqlSession getSqlSession(Boolean isAutoCommit) {
        return getSqlSessfactory().openSession(isAutoCommit);
    }

}

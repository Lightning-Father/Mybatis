package com.lightning.mybatis.v2.statement;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.lightning.mybatis.v2.config.Configuration;
import com.lightning.mybatis.v2.config.MapperRegistory;
import com.lightning.mybatis.v2.result.ResultSetHandler;

/**
 * Created by James
 * From 咕泡学院出品
 * 老师咨询 QQ 2904270631
 *
 * 数据库操作和结果处理分发
 */
public class StatementHandler {
    private final Configuration configuration;

    private final ResultSetHandler resultSetHandler;

    public StatementHandler(Configuration configuration) {
        this.configuration = configuration;
        resultSetHandler = new ResultSetHandler(configuration);
    }

    public <E> E query(String sqlStatement, Object parameter, Class<E> clazz) throws Exception {
        try {
            //JDBC
            Connection conn = getConnection();
            //TODO ParamenterHandler
            PreparedStatement pstmt = conn.prepareStatement(String.format(sqlStatement, Integer.parseInt(String.valueOf(parameter))));
            pstmt.execute();
            //ResultSetHandler
            return (E)resultSetHandler.handle(pstmt,clazz);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public Connection getConnection() throws SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/gp?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "123";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}

package com.lightning.mybatis.v1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleExecutorV1 implements ExecutorV1 {

	@Override
	public Object selectOne(String statement, String parameter) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        TestV1 test = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gp?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "123");
            preparedStatement = connection.prepareStatement(String.format(statement, Integer.parseInt(parameter)));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                test = new TestV1();
                test.setId(rs.getInt(1));
                test.setNums(rs.getInt(2));
                test.setName(rs.getString(3));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return test;
	}

}

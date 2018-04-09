package com.lightning.mybatis.v2;

import java.io.IOException;

import com.lightning.mybatis.v2.beans.Test;
import com.lightning.mybatis.v2.config.Configuration;
import com.lightning.mybatis.v2.config.mappers.TestMapper;
import com.lightning.mybatis.v2.executor.ExecutorFactory;
import com.lightning.mybatis.v2.session.SqlSession;

/**
 * Created by James on 2017-07-05. From 咕泡学院出品 老师咨询 QQ 2904270631
 */
public class BootStrap {
	public static void main(String[] args) throws IOException {
		start();
	}

	private static void start() throws IOException {
		Configuration configuration = new Configuration();
		configuration.scanPath("com.lightning.mybatis.v2.config.mappers");
		configuration.build();
		SqlSession sqlSession = new SqlSession(configuration,
				ExecutorFactory.get(ExecutorFactory.ExecutorType.CACHING.name(), configuration));
		TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
		long start = System.currentTimeMillis();
		Test test = testMapper.selectByPrimaryKey(1);
		System.out.println("Fist time cost:" + (System.currentTimeMillis() - start));
		
		long start2 = System.currentTimeMillis();
		Test test2 = testMapper.selectByPrimaryKey(1);
		System.out.println("Second time cost:" + (System.currentTimeMillis() - start2));
		System.out.println("The returned Objects in twice are same :" + (test == test2));
		
	}
}

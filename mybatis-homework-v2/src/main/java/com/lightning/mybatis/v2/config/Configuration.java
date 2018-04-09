package com.lightning.mybatis.v2.config;

import java.io.IOException;
import java.lang.reflect.Proxy;

import com.lightning.mybatis.v2.beans.Test;
import com.lightning.mybatis.v2.mapper.MapperProxy;
import com.lightning.mybatis.v2.session.SqlSession;

import lombok.Data;

/**
 * Created by James on 2017-07-01. From 咕泡学院出品 老师咨询 QQ 2904270631
 */
@Data
public class Configuration {

	private String scanPath;

	private MapperRegistory mapperRegistory;

	public Configuration scanPath(String scanPath) throws IOException {
		this.scanPath = scanPath;
		String methodQualifiedName = "com.lightning.mybatis.v2.config.mappers.TestMapper.selectByPrimaryKey";
		String sqlStatement = "select * from test where id = %d";
		Class<?> clazz = Test.class;
		this.mapperRegistory = new MapperRegistory(methodQualifiedName,sqlStatement,clazz);
		return this;
	}

	public void build() throws IOException {
		if (null == scanPath || scanPath.length() < 1) {
			throw new RuntimeException("scan path is required .");
		}
	}


	public MapperRegistory getMapperRegistory() {
		return mapperRegistory;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T getMapper(SqlSession sqlSession, Class<T> clazz) {
		ClassLoader loader = clazz.getClassLoader();
		Class<?>[] interfaces = new Class[] { clazz };
		MapperProxy mapperProxy = new MapperProxy(sqlSession, clazz);
		T newProxyInstance = (T) Proxy.newProxyInstance(loader, interfaces, mapperProxy);
		return newProxyInstance;
	}

}

package com.lightning.mybatis.v1;

import java.lang.reflect.Proxy;
import java.util.HashMap;

public class ConfigurationV1 {
	
	private SqlSessionV1 sqlSession;

	public ConfigurationV1(SqlSessionV1 sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getMapper(Class<T> clazz) {
		ClassLoader loader = clazz.getClassLoader();
		Class<?>[] interfaces = new Class[]{clazz};
		MapperProxyV1 mapperProxy = new MapperProxyV1(sqlSession);
		T newProxyInstance = (T)Proxy.newProxyInstance(loader, interfaces, mapperProxy);
		return newProxyInstance;
	}
	
	static class SqlMapperMapping{
		public static String mapperNameSpace = "com.lightning.mybatis.v1.TestMapperV1";
		public static HashMap<String, String> sqlMapperMap = new HashMap<String,String>();
		
		static {
			sqlMapperMap.put(mapperNameSpace + "." + "selectTestById", "SELECT * FROM test WHERE id = %d");
		}
	} 

}

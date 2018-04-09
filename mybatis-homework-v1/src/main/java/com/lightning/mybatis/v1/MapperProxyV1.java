package com.lightning.mybatis.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxyV1 implements InvocationHandler {

	private SqlSessionV1 sqlSession;

	public MapperProxyV1(SqlSessionV1 sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		if(method.getDeclaringClass().getName() == ConfigurationV1.SqlMapperMapping.mapperNameSpace) {
			String statement = ConfigurationV1.SqlMapperMapping.sqlMapperMap.get(ConfigurationV1.SqlMapperMapping.mapperNameSpace + "." +method.getName());
			return sqlSession.selectOne(statement,args[0].toString());
		}
		
		return method.invoke(this, args);
	}

}

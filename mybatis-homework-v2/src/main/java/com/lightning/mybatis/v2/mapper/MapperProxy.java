package com.lightning.mybatis.v2.mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.lightning.mybatis.v2.annotations.SelectV2;
import com.lightning.mybatis.v2.session.SqlSession;

/**
 * Created by James on 2017-07-01. From 咕泡学院出品 老师咨询 QQ 2904270631
 */
public class MapperProxy<T> implements InvocationHandler {
	private final SqlSession sqlSession;
	private final Class<T> mappperInterface;

	public MapperProxy(SqlSession sqlSession, Class<T> clazz) {
		this.sqlSession = sqlSession;
		this.mappperInterface = clazz;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (method.getAnnotations() != null){
            for (Annotation annotation : method.getAnnotations()) {
                System.out.println(annotation.annotationType());
                if(annotation.annotationType().equals(SelectV2.class)){
                    String sql = ((SelectV2)annotation).value();
                    return sqlSession.selectOne(sql, String.valueOf(args[0]), method.getReturnType());
                }
            }
        }
		
		return method.invoke(sqlSession, args);
	}
}

package com.lightning.mybatis.v2.executor;

/**
 * Created by James on 2017-07-01.
 * From 咕泡学院出品
 * 老师咨询 QQ 2904270631
 */
public interface Executor {

    <T> T query(String sqlStatement, Object parameter, Class<T> clazz) throws Exception;
}

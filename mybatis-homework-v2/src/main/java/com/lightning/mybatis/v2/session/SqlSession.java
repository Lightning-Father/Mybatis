package com.lightning.mybatis.v2.session;



import com.lightning.mybatis.v2.config.Configuration;
import com.lightning.mybatis.v2.executor.Executor;

/**
 * Created by James on 2017-07-01.
 * From 咕泡学院出品
 * 老师咨询 QQ 2904270631
 */
public class SqlSession {
    private Configuration configuration;
    private Executor executor;

    public Configuration getConfiguration() {
        return configuration;
    }

    //关联起来
    public SqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public <T> T getMapper(Class<T> clazz) {
        return configuration.getMapper(this,clazz);
    }

    public <T> T selectOne(String statement, Object parameter, Class<T> clazz) throws Exception {
        return executor.query(statement, parameter, clazz);
    }
}

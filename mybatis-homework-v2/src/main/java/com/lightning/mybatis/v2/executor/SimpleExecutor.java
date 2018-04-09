package com.lightning.mybatis.v2.executor;


import com.lightning.mybatis.v2.config.Configuration;
import com.lightning.mybatis.v2.statement.StatementHandler;

public class SimpleExecutor implements Executor {
    private Configuration configuration;

    public SimpleExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public <E> E query(String sqlStatement, Object parameter, Class<E> clazz)
            throws Exception {
            //初始化StatementHandler --> ParameterHandler --> ResultSetHandler
            StatementHandler handler = new StatementHandler(configuration);
            return (E) handler.query(sqlStatement, parameter,clazz);
    }
}
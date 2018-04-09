package com.lightning.mybatis.v2.executor;

import java.util.HashMap;
import java.util.Map;

import com.lightning.mybatis.v2.config.Configuration;
import com.lightning.mybatis.v2.statement.StatementHandler;

public class CachingExecutor implements Executor {
    private Configuration configuration;

    private SimpleExecutor delegate;

    private Map<String,Object> localCache = new HashMap();

    public CachingExecutor(SimpleExecutor delegate) {
        this.delegate = delegate;
    }

    public CachingExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    public <E> E query(String sqlStatement, Object parameter, Class<E> clazz)
            throws Exception {
        //初始化StatementHandler --> ParameterHandler --> ResultSetHandler
        StatementHandler handler = new StatementHandler(configuration);
        Object result = localCache.get(sqlStatement);
        if( null != result){
            System.out.println("缓存命中");
            return (E)result;
        }
        result =  (E) delegate.query(sqlStatement,parameter,clazz);
        localCache.put(sqlStatement,result);
        return (E)result;
    }
}
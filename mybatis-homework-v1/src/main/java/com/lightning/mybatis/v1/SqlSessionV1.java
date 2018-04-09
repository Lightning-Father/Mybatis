package com.lightning.mybatis.v1;

public class SqlSessionV1 {

	private ConfigurationV1 configuration;
	
	private ExecutorV1 executor;
	
	public SqlSessionV1() {
		this.configuration = new ConfigurationV1(this);
		this.executor = new SimpleExecutorV1();
	}
	
	public <T> T getMapper(Class<T> clazz) {
		return configuration.getMapper(clazz);
	}

	public Object selectOne(String statement,  String parameter) {
		
		return executor.selectOne(statement, parameter);
	}
}

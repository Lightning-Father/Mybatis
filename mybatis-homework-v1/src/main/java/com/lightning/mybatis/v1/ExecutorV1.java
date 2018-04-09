package com.lightning.mybatis.v1;

public interface ExecutorV1 {

	public <T> T selectOne(String statement,  String parameter);

}

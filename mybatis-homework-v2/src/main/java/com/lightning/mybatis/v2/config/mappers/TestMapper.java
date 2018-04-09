package com.lightning.mybatis.v2.config.mappers;


import com.lightning.mybatis.v2.annotations.SelectV2;
import com.lightning.mybatis.v2.beans.Test;

public interface TestMapper { 
	
	@SelectV2("select * from test where id = %d")
    public Test selectByPrimaryKey(Integer userId);
}
package com.lightning.mybatis.v1;

public class Entry {

	public static void main(String[] args) {
		SqlSessionV1 sessionV1 = new SqlSessionV1();
		TestMapperV1 testMapperV1 = sessionV1.getMapper(TestMapperV1.class);
		TestV1 test = (TestV1)testMapperV1.selectTestById("1");
		System.out.println(test);
	}
}

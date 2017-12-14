package com.hao.storage;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class Test {

	public static void main(String[] args) throws IOException {
		
//		String resource = "myBatis-config.xml";
//		InputStream inputStream = Resources.getResourceAsStream(resource);
//		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//		SqlSession session = sqlSessionFactory.openSession();
//
//		UserInfoMapper userInfoMapper = session.getMapper(UserInfoMapper.class);
//
//		try {
//			List<UserInfo> list = userInfoMapper.list();
//			for (UserInfo userInfo : list) {
//				System.out.println(userInfo);
//			}
//		} finally {
//			session.close();
//		}
		
		
		Operations operations = new OperationsImpl();
		List<UserInfo> list = operations.list();
		for (UserInfo userInfo : list) {
			System.out.println(userInfo);
		}
	}

}

package com.hao.storage;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class OperationsImpl implements Operations {

	private SqlSession getSession() throws IOException {
		String resource = "myBatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}

	public void createTable() {
		
		SqlSession session = null;
		try {
			session = getSession();
			UserInfoMapper userInfoMapper = session.getMapper(UserInfoMapper.class);

			userInfoMapper.createTable();

			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void add(UserInfo userInfo) {
		
		SqlSession session = null;
		try {
			session = getSession();
			UserInfoMapper userInfoMapper = session.getMapper(UserInfoMapper.class);

			userInfoMapper.add(userInfo);

			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void del(int id) {

		SqlSession session = null;
		try {
			session = getSession();
			UserInfoMapper userInfoMapper = session.getMapper(UserInfoMapper.class);

			userInfoMapper.del(id);

			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update(UserInfo userInfo) {

		SqlSession session = null;
		try {
			session = getSession();
			UserInfoMapper userInfoMapper = session.getMapper(UserInfoMapper.class);

			userInfoMapper.update(userInfo);

			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public UserInfo getUserInfo(int id) {
		
		SqlSession session = null;
		UserInfo userInfo = null;
		try {
			session = getSession();
			UserInfoMapper userInfoMapper = session.getMapper(UserInfoMapper.class);

			userInfo = userInfoMapper.getUserInfo(id);

			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	public List<UserInfo> list() {
		
		SqlSession session = null;
		List<UserInfo> list = null;
		try {
			session = getSession();
			UserInfoMapper userInfoMapper = session.getMapper(UserInfoMapper.class);

			list = userInfoMapper.list();

			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

}

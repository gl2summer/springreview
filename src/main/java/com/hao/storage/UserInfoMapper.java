package com.hao.storage;

import java.util.List;

public interface UserInfoMapper {

	public void createTable();
	public void add(UserInfo userInfo);
	public void del(int id);
	public void update(UserInfo userInfo);
	public UserInfo getUserInfo(int id);
	public List<UserInfo> list();
}

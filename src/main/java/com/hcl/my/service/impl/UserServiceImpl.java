package com.hcl.my.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.my.dao.UserDao;
import com.hcl.my.pojo.User;
import com.hcl.my.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Resource   
	private UserDao userDao;
	
	public List<User> findList() {
		// TODO Auto-generated method stub
		return userDao.findList();
	}
	
	public void addUser(User user) throws Exception{
		userDao.insert(user);
		user.setUserName("2222");
		throw new RuntimeException("11");
//		userDao.updateByPrimaryKey(user);
	}
	
	

}

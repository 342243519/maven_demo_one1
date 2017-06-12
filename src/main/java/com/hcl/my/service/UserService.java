package com.hcl.my.service;

import java.util.List;

import com.hcl.my.pojo.User;

public interface UserService {

	List<User> findList();

	void addUser(User user) throws Exception;

}

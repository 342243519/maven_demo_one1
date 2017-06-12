package com.hcl.my.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hcl.my.pojo.User;
import com.hcl.my.service.UserService;
import com.hcl.my.util.ResponseUtils;

@Controller
@RequestMapping("/user") 
public class UserController {
	
	@Resource
	private UserService userService;
	@Resource
	private MemcachedClient memcachedClient; 

	@RequestMapping("/userList.do")
    public void userList(HttpServletRequest request,HttpServletResponse response, ModelMap model) { 
    	System.out.println("ceshi");
    	try {
			List<User> list = memcachedClient.get("userList");
			if(list == null || list.size()<=0){
				list = userService.findList();
			}
			if(list != null && list.size()>0){
				
			}
			
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	ResponseUtils.renderJson(response, "222");
    	
    	
    	
//    	model.put("result", "查询成功 ");
//    	new ModelAndView("user/list",model);
//    	return "user/list";
//        return new ModelAndView("user/list",model);
    }
	
	
	@RequestMapping("/addUser.do")
    public String addUser(User user,HttpServletRequest request, ModelMap model) { 
    	System.out.println("ceshi");
    	
    	try {
    		userService.addUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	model.put("result", "查询成功 ");
//    	new ModelAndView("user/list",model);
    	return "user/list";
//        return new ModelAndView("user/list",model);
    }
	
	
	@RequestMapping("/userListInit.do")
    public String userListInit(HttpServletRequest request, ModelMap model) { 
    	System.out.println("userListInit");
    	List<User> list = userService.findList();
    	
    	try {
    		memcachedClient.add("aaa", 2000, "222");
    		System.out.println(memcachedClient.get("aaa"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	model.put("result", "查询成功 ");
//    	new ModelAndView("user/list",model);
    	return "user/list";
//        return new ModelAndView("user/list",model);
    }
	
	  
//	public Map<String, Object> queryFromCache(List<String> keys) {  
//	        Map<String, User> users = new HashMap<String, User>();  
//	        for (String key : keys) {  
//	            try {  
//	                User user = memcachedClient.get(key);  
//	                users.put(key, user);  
//	            } catch (TimeoutException e) {  
//	                e.printStackTrace();  
//	            } catch (InterruptedException e) {  
//	                e.printStackTrace();  
//	            } catch (MemcachedException e) {  
//	                e.printStackTrace();  
//	            }  
//	        }  
//	        return users;  
//	    }  
	
	
}

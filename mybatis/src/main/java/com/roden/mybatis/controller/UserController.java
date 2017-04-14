package com.roden.mybatis.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.roden.mybatis.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.roden.mybatis.pojo.User;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;
	
	@RequestMapping("/{id}")
	public String toIndex(HttpServletRequest request,@PathVariable("id") Integer id, Model model){
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "showUser";
	}
	@RequestMapping("/add")
	@ResponseBody
	public Object add(HttpServletRequest request,User user){
		userService.addUser(user);
		return "success";
	}
}

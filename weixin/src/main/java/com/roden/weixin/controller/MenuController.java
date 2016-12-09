package com.roden.weixin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.roden.weixin.entity.BaseResult;
import com.roden.weixin.entity.Menu;
import com.roden.weixin.entity.MenuButtons;
import com.roden.weixin.entity.MenuButtons.Button;
import com.roden.weixin.util.HttpUtil;
import com.roden.weixin.util.WeiXinUtil;


@Controller
@RequestMapping("/menu")
public class MenuController {
	Logger logger = LoggerFactory.getLogger(MenuController.class);	
	public static final String menuGet="https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	public static final String menuCreate = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    @RequestMapping
    public ModelAndView menu() {     	
        ModelAndView mv = new ModelAndView();       
        mv.setViewName("menu");       
        return mv;
    }
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody    
    public Object get(String name) throws IOException, Exception { 
    	String url=menuGet.replace("ACCESS_TOKEN", WeiXinUtil.getToken(name));
    	String json=HttpUtil.httpRequest(url, "GET", "");
    	Menu menu=JSON.parseObject(json, Menu.class);
    	Map<String,Object> map=new HashMap<>();
    	map.put("status", 1);
    	map.put("data", menu); 
    	if (menu.isSuccess()){
    		map.put("msg", "查询菜单成功");  
    	}else{
    		map.put("msg", "查询菜单失败："+JSON.toJSONString(menu));  
    	}    	
        return map;
    } 
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody    
    public Object create(HttpServletRequest request,String name) throws IOException, Exception {     	
    	Button[] bu=new Button[3];
    	for(int i=0;i<=2;i++){
    		Button button=new Button();
    		button.setUrl(getParameter(request,"url"+i));
    		button.setType(getParameter(request,"type"+i));
    		button.setKey(getParameter(request,"key"+i));
    		button.setName(getParameter(request,"name"+i));
    		if(StringUtils.isEmpty(getParameter(request,"url"+i))){
	    		List<Button> subButton=new ArrayList<Button>();
	    		for(int j=0;j<=4;j++){    			
	        		Button b=new Button();
	        		b.setUrl(getParameter(request,"url"+i+j));
	        		b.setName(getParameter(request,"name"+i+j));
	        		b.setKey(getParameter(request,"key"+i+j));
	        		b.setType(getParameter(request,"type"+i+j));
	        		if(!StringUtils.isEmpty(getParameter(request,"url"+i+j))){
	        			subButton.add(b);
	        		}
	    		}
    		button.setSub_button(subButton);
    		}
    		bu[i]=button;    		  		
    	}    	
    	MenuButtons menuButtons=new MenuButtons();
    	menuButtons.setButton(bu);		
    	
		String url = menuCreate.replace("ACCESS_TOKEN", WeiXinUtil.getToken(name));
		String method = "POST";		
		BaseResult baseResult= JSON.parseObject(HttpUtil.httpRequest(url, method, JSON.toJSONString(menuButtons)),BaseResult.class);		   	
    	Map<String,Object> map=new HashMap<>();
    	map.put("status", 1);
    	map.put("data", menuButtons);
    	if (baseResult.isSuccess()){
    		map.put("msg", "菜单创建成功，请重新关注公众号查看是否生效");  
    	}else{
    		map.put("msg", "菜单创建失败："+JSON.toJSONString(baseResult));  
    	}    	
       return map;
    } 
    
    private String getParameter(HttpServletRequest request,String param){
    	String str=request.getParameter(param);
    	if(StringUtils.isEmpty(str)){
    		return null;
    	}
    	return str;
    }

}
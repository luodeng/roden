package com.roden.mybatis;

import com.roden.mybatis.pojo.User;
import com.roden.mybatis.service.IUserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:/spring-mvc.xml","classpath*:/spring-mybatis.xml"})
@Rollback(value = true)
@Transactional
public class UserControllerTest{
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private IUserService userService;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();
    }
    @Test
    public void get() {
        User user = userService.getUserById(1);
        Assert.assertEquals(user.getId().toString(), "1");//判断两个参数是否相同，返回true的话则测试通过，不然控制台会亮红灯。
    }
    @Test
    public void getUser() throws Exception {
        //请求方式为post
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/user/1");
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void addUser() throws Exception {
        //请求方式为post
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/user/add");
        //添加请求参数
        mockHttpServletRequestBuilder.param("userName", "addtest");
        mockHttpServletRequestBuilder.param("password", "123456");
        mockHttpServletRequestBuilder.param("age", "18");
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andDo(print());
    }

}
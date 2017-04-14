package com.roden.mybatis;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.roden.mybatis.pojo.Blog;
import mapper.BlogMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class BlogTest {

    @Test  
    public void dynamicForeachTest() {  
        SqlSession session = Util.getSqlSessionFactory().openSession();
        BlogMapper blogDao = session.getMapper(BlogMapper.class);
        List<Integer> ids = new ArrayList<Integer>();  
        ids.add(1);  
        ids.add(3);  
        ids.add(6);  
        List<Blog> blogs = blogDao.dynamicForeachTest(ids);
        for (Blog blog : blogs)  
            System.out.println(blog);  
        session.close();  
    }  
    @Test  
    public void dynamicForeach2Test() {  
        SqlSession session = Util.getSqlSessionFactory().openSession();  
        BlogMapper blogDao = session.getMapper(BlogMapper.class);  
        int[] ids = new int[] {1,3,6,9};  
        List<Blog> blogs = blogDao.dynamicForeach2Test(ids);  
        for (Blog blog : blogs)  
            System.out.println(blog);  
        session.close();  
    }  
    @Test  
    public void dynamicForeach3Test() {  
        SqlSession session = Util.getSqlSessionFactory().openSession();  
        BlogMapper blogDao = session.getMapper(BlogMapper.class);  
        final List<Integer> ids = new ArrayList<Integer>();  
        ids.add(1);  
        ids.add(2);  
        ids.add(3);  
        ids.add(6);  
        ids.add(7);  
        ids.add(9);  
        Map<String, Object> params = new HashMap<String, Object>();  
        params.put("ids", ids);  
        params.put("title", "中国");  
        List<Blog> blogs = blogDao.dynamicForeach3Test(params);  
        for (Blog blog : blogs)  
            System.out.println(blog);  
        session.close();  
    }

    /**
     * 新增记录
     */
    @Test
    public void testInsertBlog() {
        SqlSession session = Util.getSqlSessionFactory().openSession();
        Blog blog = new Blog();
        blog.setTitle("中国人");
        blog.setContent("五千年的风和雨啊藏了多少梦");
        blog.setOwner("天天");
        session.insert("mapper.BlogMapper.insertBlog", blog);
        session.commit();
        session.close();
    }

    /**
     * 查询单条记录
     */
    @Test
    public void testSelectOne() {
        SqlSession session = Util.getSqlSessionFactory().openSession();
        Blog blog = (Blog)session.selectOne("mapper.BlogMapper.selectBlog", 8);
        System.out.println(blog);
        session.close();
    }

    /**
     * 修改记录
     */
    @Test
    public void testUpdateBlog() {
        SqlSession session = Util.getSqlSessionFactory().openSession();
        Blog blog = new Blog();
        blog.setId(7);//需要修改的Blog的id
        blog.setTitle("中国人2");//修改Title
        blog.setContent("黄色的脸，黑色的眼，不变是笑容");//修改Content
        blog.setOwner("天天2");//修改Owner
        session.update("mapper.BlogMapper.updateBlog", blog);
        session.commit();
        session.close();
    }

    /**
     * 查询所有的记录
     */
    @Test
    public void testSelectAll() {
        SqlSession session = Util.getSqlSessionFactory().openSession();
        List<Blog> blogs = session.selectList("mapper.BlogMapper.selectAll");
        for (Blog blog:blogs)
            System.out.println(blog);
        session.close();
    }

    /**
     * 模糊查询
     */
    @Test
    public void testFuzzyQuery() {
        SqlSession session = Util.getSqlSessionFactory().openSession();
        String title = "中国";
        List<Blog> blogs = session.selectList("mapper.BlogMapper.fuzzyQuery", title);
        for (Blog blog:blogs)
            System.out.println(blog);
        session.close();
    }

    /**
     * 删除记录
     */
    @Test
    public void testDeleteBlog() {
        SqlSession session = Util.getSqlSessionFactory().openSession();
        session.delete("mapper.BlogMapper.deleteBlog", 8);
        session.commit();
        session.close();
    }

    /**
     * 新增记录
     */
    @Test
    public void testInsertBlog2() {
        SqlSession session = Util.getSqlSessionFactory().openSession();
        Blog blog = new Blog();
        blog.setTitle("中国人");
        blog.setContent("五千年的风和雨啊藏了多少梦");
        blog.setOwner("天天");
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);
        blogMapper.insertBlog(blog);
        session.commit();
        session.close();
    }

    /**
     * 查询单条记录
     */
    @Test
    public void testSelectOne2() {
        SqlSession session = Util.getSqlSessionFactory().openSession();
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);
        Blog blog = blogMapper.selectBlog(7);
        System.out.println(blog);
        session.close();
    }

    /**
     * 修改记录
     */
    @Test
    public void testUpdateBlog2() {
        SqlSession session = Util.getSqlSessionFactory().openSession();
        Blog blog = new Blog();
        blog.setId(9);// 需要修改的Blog的id
        blog.setTitle("中国人2");// 修改Title
        blog.setContent("黄色的脸，黑色的眼，不变是笑容");// 修改Content
        blog.setOwner("天天2");// 修改Owner
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);
        blogMapper.updateBlog(blog);
        session.commit();
        session.close();
    }

    /**
     * 查询所有记录
     */
    @Test
    public void testSelectAll2() {
        SqlSession session = Util.getSqlSessionFactory().openSession();
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);
        List<Blog> blogs = blogMapper.selectAll();
        for (Blog blog : blogs)
            System.out.println(blog);
        session.close();
    }

    /**
     * 模糊查询
     */
    @Test
    public void testFuzzyQuery2() {
        SqlSession session = Util.getSqlSessionFactory().openSession();
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);
        String title = "中国";
        List<Blog> blogs = blogMapper.fuzzyQuery(title);
        for (Blog blog : blogs)
            System.out.println(blog);
        session.close();
    }

    /**
     * 删除记录
     */
    @Test
    public void testDeleteBlog2() {
        SqlSession session = Util.getSqlSessionFactory().openSession();
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);
        blogMapper.deleteBlog(10);
        session.commit();
        session.close();
    }

    /**
     * 新增记录
     */
    @Test
    public void testInsert3() {
        SqlSession session = Util.getSqlSessionFactory().openSession();
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);
        Blog blog = new Blog();
        blog.setTitle("title2");
        blog.setContent("content2");
        blog.setOwner("owner2");
        blogMapper.insertBlog2(blog);
        session.commit();
        session.close();
    }

    /**
     * 查找单条记录
     */
    @Test
    public void testSelectOne3() {
        SqlSession session = Util.getSqlSessionFactory().openSession();
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);
        Blog blog = blogMapper.selectBlog2(1);
        System.out.println(blog);
        session.close();
    }

    /**
     * 查找多条记录，返回结果为一集合
     */
    @Test
    public void testSelectAll3() {
        SqlSession session = Util.getSqlSessionFactory().openSession();
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);
        List<Blog> blogs = blogMapper.selectAll2();
        for (Blog blog:blogs)
            System.out.println(blog);
        session.close();
    }

    /**
     * 修改某条记录
     */
    @Test
    public void testUpdate3() {
        SqlSession session = Util.getSqlSessionFactory().openSession();
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);
        Blog blog = new Blog();
        blog.setId(3);
        blog.setTitle("title3");
        blog.setContent("content3");
        blog.setOwner("owner3");
        blogMapper.updateBlog2(blog);
        session.commit();
        session.close();
    }

    /**
     * 删除记录
     */
    @Test
    public void testDelete3() {
        SqlSession session = Util.getSqlSessionFactory().openSession();
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);
        blogMapper.deleteBlog2(5);
        session.commit();
        session.close();
    }

    @Test
    public void testFuzzyQuery3() {
        SqlSession session = Util.getSqlSessionFactory().openSession();
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);
        List<Blog> blogs = blogMapper.fuzzyQuery2("中国");
        for (Blog blog:blogs)
            System.out.println(blog);
        session.close();
    }
}

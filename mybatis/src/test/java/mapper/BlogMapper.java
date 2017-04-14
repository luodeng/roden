package mapper;

import java.util.List;
import java.util.Map;

import com.roden.mybatis.pojo.Blog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;  
import org.apache.ibatis.annotations.Select;  
import org.apache.ibatis.annotations.Update;

/** 
 * 以下的操作1都是把SQL写在配置文件里面的，而操作2都是直接用注解标明要执行的SQL语句 
 * 因为该Mapper的全名跟BlogMapper.xml文件里面的namespace是一样的，所以不能在这里面 
 * 用注解定义一个与BlogMapper.xml文件里面同名的映射 
 * @author andy 
 * 
 */  
public interface BlogMapper {  

    public Blog selectBlog(int id);
      
    @Select("select * from t_blog where id = #{id}")  
    public Blog selectBlog2(int id);  
      
    public void insertBlog(Blog blog);  
      
    @Insert("insert into t_blog(title,content,owner) values(#{title},#{content},#{owner})")  
    public void insertBlog2(Blog blog);  
      
    public void updateBlog(Blog blog);  
      
    @Update("update t_blog set title=#{title},content=#{content},owner=#{owner} where id=#{id}")  
    public void updateBlog2(Blog blog);  
      
    public void deleteBlog(int id);   
      
    @Delete("delete from t_blog where id = #{id}")  
    public void deleteBlog2(int id);  
      
    public List<Blog> selectAll();  
      
    @Select("select * from t_blog")  
    public List<Blog> selectAll2();  
      
    public List<Blog> fuzzyQuery(String title);  
      
    @Select("select * from t_blog where title like \"%\"#{title}\"%\"")  
    public List<Blog> fuzzyQuery2(String title);  
    
    
    public List<Blog> dynamicForeachTest(List<Integer> ids);  
    public List<Blog> dynamicForeach2Test(int[] ids);  
    public List<Blog> dynamicForeach3Test(Map<String, Object> params); 
      
}  
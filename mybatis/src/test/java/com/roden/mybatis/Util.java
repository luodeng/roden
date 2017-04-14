package com.roden.mybatis;
import java.io.IOException;  
import java.io.InputStream;  

import org.apache.ibatis.io.Resources;  
import org.apache.ibatis.session.SqlSessionFactory;  
import org.apache.ibatis.session.SqlSessionFactoryBuilder;  

public class Util {  
      
    private static SqlSessionFactory sqlSessionFactory = null;  
      
    static {  
        try {  
            InputStream is = Resources.getResourceAsStream("mybatis_conf.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          
    }  
      
    public static SqlSessionFactory getSqlSessionFactory() {  
        return sqlSessionFactory;  
    }  
      
}  
package com.roden.spring.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonServiceImpl implements PersonService {
    /*private DataSource dataSource;    
    private JdbcTemplate jdbcTemplate; 
    // 设置数据源
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }*/
	/*
	 * 可通过继承JdbcDaoSupport后通过getJdbcTemplate()来获取模板
	 * 此时可注入DataSource，或者注入jdbcTemplate都可正常运行
	 */
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Override
    public void save(Person person) { 
        jdbcTemplate.update("insert into person(name,age,sex)values(?,?,?)",
                new Object[] { person.getName(), person.getAge(),person.getSex() }, 
                new int[] { java.sql.Types.VARCHAR,java.sql.Types.INTEGER, java.sql.Types.VARCHAR });
    }

    public void update(Person person) {
        jdbcTemplate.update("update person set name=?,age=?,sex=? where id=?",
                new Object[] { person.getName(), person.getAge(), person.getSex(), person.getId() },
                new int[] {java.sql.Types.VARCHAR, java.sql.Types.INTEGER,java.sql.Types.VARCHAR, java.sql.Types.INTEGER });

    }

    public Person getPerson(Integer id) {
        Person person =jdbcTemplate.queryForObject("select * from person where id=?",
                new Object[] { id },
                new int[] { java.sql.Types.INTEGER }, new PersonRowMapper());
        return person;
    }
    public List<Person> getPerson() {
        List<Person> list = jdbcTemplate.query("select * from person", new PersonRowMapper());
        return list;

    }
    public void delete(Integer id) {
        jdbcTemplate.update("delete from person where id = ?",
                new Object[] { id },
                new int[] { java.sql.Types.INTEGER });

    }
    
   /*********************************************************************************************************************************/ 
    public void add(Person person){
    	jdbcTemplate.update("insert into person(name,age,sex)values(?,?,?)",person.getName(),person.getAge(),person.getSex());
    }
    public Person getById(int pid){
    	return jdbcTemplate.queryForObject( "select id,name,age,sex from person where id=?",new PersonRowMapper() {
			@Override
			public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
				Person p=new Person();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setAge(rs.getInt(3));
				p.setSex(rs.getString(4));
				return p;
			}    		
		},pid);
    }
    //直接返回对象
    public Person getObjectById(int pid){
    	return jdbcTemplate.queryForObject( "select id,name,age,sex from person where id=?",BeanPropertyRowMapper.newInstance(Person.class),pid);
    } 
    //使用命名参数
    public void addPerson(Person person){
    	Map<String,Object> params=new HashMap<String,Object>();
    	params.put("name", person.getName());
    	params.put("age", person.getAge());
    	params.put("sex", person.getSex());    	
    	jdbcTemplate.update("insert into person(name,age,sex)values(:name,:age,:sex)",params);
    }
    //可以返回ID的添加
    public Person savePerson(final Person person) {
        final String sql = "insert into person(name,age,sex)values(?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int updatecount=jdbcTemplate.update(new PreparedStatementCreator() {
        	@Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, person.getName());
                ps.setInt(2, person.getAge());
                ps.setString(3, person.getSex());
                return ps;
            }
        }, keyHolder);
        int id = keyHolder.getKey().intValue();
        person.setId(id);
       return person;
    }
}
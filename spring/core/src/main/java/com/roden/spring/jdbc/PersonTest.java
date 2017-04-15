package com.roden.spring.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PersonTest {
	@Autowired
	PersonService personService;
	@Test
	@Transactional   //标明此方法需使用事务
	@Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
	public void testPerson(){
		//ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
		//PersonService personService = (PersonService) act.getBean("personService");
		Person person = new Person();
		person.setName("苏东坡");
		person.setAge(21);
		person.setSex("男");
		// 新增一条记录
		//personService.save(person);
		int id=personService.savePerson(person).getId();
		System.out.println(personService.getObjectById(id).getName());
		List<Person> persons = personService.getPerson();
		System.out.println("++++++++得到所有Person");
		for (Person p : persons) {
			System.out.println(p.getId() + "  " + p.getName()
					+ "   " + p.getAge() + "  " + p.getSex());
		}
		Person updatePerson = new Person();
		updatePerson.setName("Divide");
		updatePerson.setAge(20);
		updatePerson.setSex("男");
		updatePerson.setId(id);
		// 更新一条记录
		personService.update(updatePerson);
		System.out.println("******************");
		// 获取一条记录
		Person onePerson = personService.getPerson(id);
		System.out.println(onePerson.getId() + "  " + onePerson.getName()
				+ "  " + onePerson.getAge() + "  " + onePerson.getSex());
		// 删除一条记录
		personService.delete(id);
	}
}
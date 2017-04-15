package com.roden.spring.jdbc;
import java.util.List;
public interface PersonService {

    public  void save(Person person);

    public  void update(Person person);

    public  Person getPerson(Integer id);

    public  List<Person> getPerson();

    public  void delete(Integer id);
    
    public Person savePerson(Person person);
    
    public Person getObjectById(int pid);   
   

}
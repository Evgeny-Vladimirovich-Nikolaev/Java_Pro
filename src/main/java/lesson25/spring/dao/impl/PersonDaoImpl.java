package lesson25.spring.dao.impl;

import lesson25.spring.dao.PersonDao;

//@Component
public class PersonDaoImpl implements PersonDao {

    @Override
    public void save() {
        System.out.println("Person has been successfully saved");
    }
}

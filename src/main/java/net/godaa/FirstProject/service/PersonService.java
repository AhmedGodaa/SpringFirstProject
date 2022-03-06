package net.godaa.FirstProject.service;

import net.godaa.FirstProject.dao.PersonDao;
import net.godaa.FirstProject.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPerson() {
        return personDao.getAllPeople();
    }

    public Optional<Person> selectPersonById(UUID id) {
        return personDao.selectPersonById(id);
    }

    public int updatePerson(UUID uuid, Person person) {
        return personDao.updatePersonById(uuid, person);
    }

    public int deletePersonById(UUID uuid) {
        return personDao.deletePersonById(uuid);
    }

}

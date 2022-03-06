package net.godaa.FirstProject.dao;

import net.godaa.FirstProject.models.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    int insertPerson(UUID id, Person person);

    List<Person> getAllPeople();

    int deletePersonById(UUID id);

    int updatePersonById(UUID uuid,Person person);

    Optional<Person> selectPersonById(UUID id);


    //insert person without id  --> random id generated
    default int insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

}

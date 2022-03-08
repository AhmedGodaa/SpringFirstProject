package net.godaa.FirstProject.Repositories;

import net.godaa.FirstProject.dao.PersonDao;
import net.godaa.FirstProject.models.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonRepository implements PersonDao {
    private static List<Person> personList = new ArrayList<>();


    @Override
    public int insertPerson(UUID id, Person person) {
        personList.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> getAllPeople() {
        return personList;
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if (personMaybe.isEmpty()) {
            return 0;
        } else {
            personList.remove(personMaybe.get());
        }
        return 1;

    }

    @Override
    public int updatePersonById(UUID uuid, Person person) {
        return selectPersonById(uuid)
                .map(p -> {
                    int indexOfPerson = personList.indexOf(p);
                    if (indexOfPerson >= 0) {
                        personList.set(indexOfPerson, new Person(uuid, person.getName()));
                        return 1;
                    }
                    return 0;
                }).orElse(0);
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return personList.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }


}

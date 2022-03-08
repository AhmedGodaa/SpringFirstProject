package net.godaa.FirstProject.Repositories;

import net.godaa.FirstProject.dao.PersonDao;
import net.godaa.FirstProject.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonRepository implements PersonDao {
    private static List<Person> personList = new ArrayList<>();

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertPerson(UUID id, Person person) {
        personList.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> getAllPeople() {
        final String sql = "SELECT id,name FROM person";
        List<Person> person = jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(id, name);
        });
        return person;

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
        final String sql = "SELECT id,name FROM person WHERE id = ? ";

        Person person = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID personId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    return new Person(personId, name);
                }
        );

        return Optional.ofNullable(person);


    }


}

package net.godaa.FirstProject.api;

import net.godaa.FirstProject.models.Person;
import net.godaa.FirstProject.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// For Api Controller
@RequestMapping("/person")
@RestController
public class PersonApi {
    private final PersonService personService;

    //    Constructor
    //    Annotation for injection
    @Autowired
    public PersonApi(PersonService personService) {
        this.personService = personService;
    }

    // Post http request
    @PostMapping
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPerson() {
        return personService.getAllPerson();
    }

    @GetMapping(path = "{id}")
    public Person selectPersonById(@PathVariable("id") UUID id) {
        return personService.selectPersonById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID uuid) {
        personService.deletePersonById(uuid);
    }

    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id,@RequestBody Person person) {
        personService.updatePerson(id, person);
    }
}

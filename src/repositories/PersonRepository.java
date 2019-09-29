package repositories;

import models.Person;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"CanBeFinal", "unused"})
class PersonRepository {

    private List<Person> persons;

    public PersonRepository() {
        this.persons = new ArrayList<>();
    }

    public List<Person> getPersons() {
        return persons;
    }
}


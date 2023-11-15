package com.project.springcourse.DAO;

import com.project.springcourse.entities.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonRowMapper());
    }

    public Person findById(int person_id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE person_id=?", new PersonRowMapper(), person_id)
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(person_name, birthday) VALUES(?, ?)", person.getPerson_name(), person.getBirthday());
    }

    public void update(Person person) {
        jdbcTemplate.update("UPDATE person SET person_name=?, birthday=? WHERE person_id=?", person.getPerson_name(),
                person.getBirthday(), person.getPerson_id());
    }

    public void deleteById(int person_id) {
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?", person_id);
    }

    public Optional<Person> findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM person WHERE person_name=?", new PersonRowMapper(), name)
                .stream().findAny();
    }
}

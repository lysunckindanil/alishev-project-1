package com.project.springcourse.DAO;

import com.project.springcourse.entities.Book;
import com.project.springcourse.entities.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public List<Book> findAll() {
        System.out.println(jdbcTemplate.query("SELECT * FROM book", new BookRowMapper()));
        return jdbcTemplate.query("SELECT * FROM book", new BookRowMapper());
    }

    public Book findById(int book_id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id=?", new BookRowMapper(), book_id)
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(book_name, author, year) VALUES(?, ?, ?)", book.getBook_name(), book.getAuthor(), book.getYear());
    }

    public List<Book> findAllByPersonId(int person_id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id=?", new BookRowMapper(), person_id);
    }

    public void update(Book book) {
        jdbcTemplate.update("UPDATE book SET book_name=?, author=?, year=? WHERE book_id=?", book.getBook_name(), book.getAuthor(), book.getYear(), book.getId());
    }

    public void updateBookPerson(int book_id, int person_id) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?", person_id, book_id);
    }

    public void deleteBookPerson(int book_id) {
        jdbcTemplate.update("UPDATE book SET person_id=NULL WHERE book_id=?", book_id);
    }

    public void deleteById(int book_id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", book_id);
    }
}

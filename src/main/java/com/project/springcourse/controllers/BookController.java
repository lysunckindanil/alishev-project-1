package com.project.springcourse.controllers;

import com.project.springcourse.DAO.BookDAO;
import com.project.springcourse.DAO.PersonDAO;
import com.project.springcourse.entities.Book;
import com.project.springcourse.entities.Person;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.findAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String book(@PathVariable int id, Model model) {
        Book book = bookDAO.findById(id);
        model.addAttribute("book", bookDAO.findById(id));
        if (book != null && book.getPerson_id() != 0) {
            model.addAttribute("person", personDAO.findById(book.getPerson_id()));
        } else {
            model.addAttribute("person", new Person());
            model.addAttribute("people", personDAO.findAll());
        }
        return "books/profile";
    }

    @GetMapping("/new")
    public String newBookGet(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping("/new")
    public String newBookPost(@ModelAttribute @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "books/new";
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBookGet(@PathVariable int id, Model model) {
        model.addAttribute("book", bookDAO.findById(id));
        return "books/edit";
    }

    @PostMapping("/{id}/edit")
    public String editBookPost(@PathVariable int id, @ModelAttribute @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        Book book_original = bookDAO.findById(id);
        book_original.setBook_name(book.getBook_name());
        book_original.setAuthor(book.getAuthor());
        book_original.setYear(book.getYear());
        bookDAO.update(book_original);
        return "redirect:/books";
    }

    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable int id) {
        bookDAO.deleteById(id);
        return "redirect:/books";
    }

    @PostMapping("/{id}/free")
    public String freeBook(@PathVariable int id) {
        bookDAO.deleteBookPerson(id);
        return "redirect:/books";
    }

    @PostMapping("/{book_id}/set")
    public String setPersonBook(@PathVariable int book_id, @ModelAttribute Person person) {
        bookDAO.updateBookPerson(book_id, person.getPerson_id());
        return "redirect:/books/" + book_id;
    }
}

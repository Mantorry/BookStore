package com.example.bookstore.Controllers;

import com.example.bookstore.Data.Book;
import com.example.bookstore.Repo.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Book")
@Controller
@SessionAttributes({"books","account"})
public class BookController {
    private final BookRepository repo;

    @Autowired
    public BookController(BookRepository repo) {this.repo = repo;}


    //Метод чтения данных с таблицы и отображения страницы добавления
    @GetMapping
    public String read(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Book book = new Book();
        model.addAttribute("book",book);
        Iterable<Book> books = repo.findAll();
        model.addAttribute("books", books);
        model.addAttribute("account", userDetails==null ? "Вход" : userDetails.getUsername()+": Выход");
        return "Book/Book";}


    //Метод сохранения новой записи в БД
    @PostMapping(value = "/create")
    public String create(@Valid @ModelAttribute(value = "book") Book book, BindingResult result) {
        if (result.hasErrors()){return "Book/Book";}
        else{repo.save(book);return "redirect:/Book";}}

    //Метод удаления записи из БД
    @PostMapping(value = "/delete")
    public String delete(@ModelAttribute(value = "var") Book book) {
        repo.deleteById(book.getId()); return "redirect:/Book";}

    //Метод отображения страницы редактирования
    @PostMapping(value = "/update_one")
    public String update_one(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute(value = "book") Book book, Model model) {
        model.addAttribute("book",book);
        Iterable<Book> books = repo.findAll();
        model.addAttribute("books", books);
        model.addAttribute("account", userDetails==null ? "Вход" : userDetails.getUsername()+": Выход");
        return "Book/BookUpdate";}

    //Метод сохранения изменений записи в БД
    @PostMapping(value = "/update_two")
    public String update_two(@Valid @ModelAttribute(value = "book") Book book,  BindingResult result) {
        if (result.hasErrors()){return "Book/BookUpdate";}
        repo.save(book);
        return "redirect:/Book";}}
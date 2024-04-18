package com.example.bookstore.Controllers;

import com.example.bookstore.Data.Book;
import com.example.bookstore.Data.Customer;
import com.example.bookstore.Data.Delivery;
import com.example.bookstore.Data.Sale;
import com.example.bookstore.Repo.BookRepository;
import com.example.bookstore.Repo.CustomerRepository;
import com.example.bookstore.Repo.DeliveryRepository;
import com.example.bookstore.Repo.SaleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Delivery")
@Controller
@SessionAttributes({"deliverys", "sales", "books", "customers","account"})
public class DeliveryController {
    private final DeliveryRepository repo;
    private final SaleRepository saleRepo;
    private final CustomerRepository customerRepo;
    private final BookRepository bookRepo;

    @Autowired
    public DeliveryController(DeliveryRepository repo, SaleRepository saleRepo, CustomerRepository customerRepo, BookRepository bookRepo) {
        this.repo = repo;
        this.saleRepo = saleRepo;
        this.bookRepo = bookRepo;
        this.customerRepo = customerRepo;
    }

    @GetMapping
    public String read(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("delivery",new Delivery());
        Iterable<Sale> sales = saleRepo.findAll();
        Iterable<Book> books = bookRepo.findAll();
        Iterable<Customer> customers = customerRepo.findAll();
        Iterable<Delivery> deliverys = repo.findAll();
        model.addAttribute("deliverys", deliverys);
        model.addAttribute("sales", sales);
        model.addAttribute("books", books);
        model.addAttribute("customers", customers);
        model.addAttribute("account", userDetails==null ? "Вход" : userDetails.getUsername()+": Выход");
        return "Delivery/Delivery";}

    @PostMapping(value = "/create")
    public String create(@Valid @ModelAttribute(value = "delivery") Delivery delivery, BindingResult result, Model model) {
        if (result.hasErrors()){return "Delivery/Delivery";}
        else{repo.save(delivery);return "redirect:/Delivery";}}

    @PostMapping(value = "/delete")
    public String delete(@ModelAttribute(value = "var") Delivery delivery) {
        repo.deleteById(delivery.getId()); return "redirect:/Delivery";}

    @PostMapping(value = "/update_one")
    public String update_one(@ModelAttribute(value = "delivery") Delivery delivery, Model model) {
        model.addAttribute("delivery", delivery);
        model.addAttribute("deliverys", repo.findAll());
        model.addAttribute("sales", saleRepo.findAll());
        model.addAttribute("books", bookRepo.findAll());
        model.addAttribute("customers", customerRepo.findAll());
        return "Delivery/DeliveryUpdate";}

    @PostMapping(value = "/update_two")
    public String update_two(@Valid @ModelAttribute(value = "delivery") Delivery delivery, BindingResult result) {
        if (result.hasErrors()){return "Delivery/DeliveryUpdate";}
        repo.save(delivery);
        return "redirect:/Delivery";}}
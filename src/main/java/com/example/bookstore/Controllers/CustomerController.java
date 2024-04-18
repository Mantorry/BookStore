package com.example.bookstore.Controllers;

import com.example.bookstore.Data.Customer;
import com.example.bookstore.Repo.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Customer")
@Controller
@SessionAttributes({"customers","account"})
public class CustomerController {
    private final CustomerRepository repo;

    @Autowired
    public CustomerController(CustomerRepository repo) {this.repo = repo;}

    @GetMapping
    public String read(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer",customer);
        Iterable<Customer> customers = repo.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("account", userDetails==null ? "Вход" : userDetails.getUsername()+": Выход");
        return "Customer/Customer";}

    @PostMapping(value = "/create")
    public String create(@Valid @ModelAttribute(value = "customer") Customer customer, BindingResult result) {
        if (result.hasErrors()){return "Customer/Customer";}
        else{repo.save(customer);return "redirect:/Customer";}}

    @PostMapping(value = "/delete")
    public String delete(@ModelAttribute(value = "var") Customer customer) {
        repo.deleteById(customer.getId()); return "redirect:/Customer";}

    @PostMapping(value = "/update_one")
    public String update_one(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute(value = "customer") Customer customer, Model model) {
        model.addAttribute("customer",customer);
        Iterable<Customer> customers = repo.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("account", userDetails==null ? "Вход" : userDetails.getUsername()+": Выход");
        return "Customer/CustomerUpdate";}

    @PostMapping(value = "/update_two")
    public String update_two(@Valid @ModelAttribute(value = "customer") Customer customer, BindingResult result) {
        if (result.hasErrors()){return "Customer/CustomerUpdate";}
        repo.save(customer);
        return "redirect:/Customer";}}
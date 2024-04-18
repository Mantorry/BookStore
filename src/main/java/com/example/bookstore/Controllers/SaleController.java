package com.example.bookstore.Controllers;

import com.example.bookstore.Data.Sale;
import com.example.bookstore.Repo.SaleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Sale")
@Controller
@SessionAttributes({"sales", "account"})
public class SaleController {
    private final SaleRepository repo;

    @Autowired
    public SaleController(SaleRepository repo) {this.repo = repo;}

    @GetMapping
    public String read(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Sale sale = new Sale();
        model.addAttribute("sale",sale);
        Iterable<Sale> sales = repo.findAll();
        model.addAttribute("sales", sales);
        model.addAttribute("account", userDetails==null ? "Вход" : userDetails.getUsername()+": Выход");
        return "Sale/Sale";}

    @PostMapping(value = "/create")
    public String create(@Valid @ModelAttribute(value = "sale") Sale sale, BindingResult result, Model model) {
        if (result.hasErrors()){return "Sale/Sale";}
        else{repo.save(sale);return "redirect:/Sale";}}

    @PostMapping(value = "/delete")
    public String delete(@ModelAttribute(value = "var") Sale sale) {
        repo.deleteById(sale.getId()); return "redirect:/Sale";}

    @PostMapping(value = "/update_one")
    public String update_one(@ModelAttribute(value = "var") Sale sale, Model model) {
        model.addAttribute("sale",sale);
        Iterable<Sale> sales = repo.findAll();
        model.addAttribute("sales", sales);
        return "Sale/SaleUpdate";}

    @PostMapping(value = "/update_two")
    public String update_two(@Valid @ModelAttribute(value = "sale") Sale sale, BindingResult result) {
        if (result.hasErrors()){return "Sale/SaleUpdate";}
        repo.save(sale);
        return "redirect:/Sale";}}
package org.learning.pizzeria.spring.la.mia.pizzeria.crud.controller;

import jakarta.validation.Valid;
import org.learning.pizzeria.spring.la.mia.pizzeria.crud.model.Pizza;
import org.learning.pizzeria.spring.la.mia.pizzeria.crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class PizzeriaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(Model model){

        List<Pizza> pizzaList = pizzaRepository.findAll();
        model.addAttribute("pizze",pizzaList);

        return "pizze";
    }

    @GetMapping("/show/{pizzaId}")
    public String show (@PathVariable("pizzaId") Integer id, Model model){
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);

        if (pizzaOptional.isPresent()){
        Pizza pizzaFromDB = pizzaOptional.get();
        model.addAttribute("pizza",pizzaFromDB);
        return "/detail";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/create")
    public String create (Model model){
        model.addAttribute("pizza", new Pizza());
        return "/form";
    }

    @PostMapping("/create")
    public String doCreate (@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/form";
        }

        pizzaRepository.save(formPizza);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit (@PathVariable Integer id,Model model){
        Optional<Pizza> result = pizzaRepository.findById(id);

        if (result.isPresent()){
            model.addAttribute("pizza", result.get());
            return "/edit";
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza formBook, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "/edit";
        }
        pizzaRepository.save(formBook);
        return "redirect:/";
    }

}

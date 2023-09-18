package org.learning.pizzeria.spring.la.mia.pizzeria.crud.controller;

import org.learning.pizzeria.spring.la.mia.pizzeria.crud.model.Pizza;
import org.learning.pizzeria.spring.la.mia.pizzeria.crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

}

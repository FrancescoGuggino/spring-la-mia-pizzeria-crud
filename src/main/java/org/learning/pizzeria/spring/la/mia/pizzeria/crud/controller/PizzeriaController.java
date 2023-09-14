package org.learning.pizzeria.spring.la.mia.pizzeria.crud.controller;

import org.learning.pizzeria.spring.la.mia.pizzeria.crud.model.Pizza;
import org.learning.pizzeria.spring.la.mia.pizzeria.crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

}

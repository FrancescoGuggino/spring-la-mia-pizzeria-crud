package org.learning.pizzeria.spring.la.mia.pizzeria.crud.repository;

import org.learning.pizzeria.spring.la.mia.pizzeria.crud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza,Integer> {
}

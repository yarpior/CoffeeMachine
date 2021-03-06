package ru.pinchuk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.pinchuk.entity.Coffee;
import ru.pinchuk.entity.CoffeeFactory;
import ru.pinchuk.entity.coffeeMachine.CoffeeMachine;

@Controller
@RestController
public class MainController {

    @Autowired
    CoffeeMachine coffeeMachine;

    @GetMapping("/getCoffee")
    public CoffeeMachine cappuccino(@RequestParam("type") String type) throws Exception {
        Coffee coffee = CoffeeFactory.createCoffeeByType(type);
        coffeeMachine.getCupOfCoffee(coffee);
        return coffeeMachine;
    }

    @GetMapping("/clean")
    public CoffeeMachine clean() {
        coffeeMachine.cleanMachine();
        return coffeeMachine;
    }

    @GetMapping("/addMaterial")
    public CoffeeMachine addMaterial(@RequestParam("type") String type, @RequestParam("amount") int amount) {
        if("water".equals(type)) {
            coffeeMachine.addWater(amount);
        } else if("milk".equals(type)) {
            coffeeMachine.addMilk(amount);
        } else if("beans".equals(type)) {
            coffeeMachine.addBeans(amount);
        }
        return coffeeMachine;
    }
}

package com.sbt.javaschool.losev.lesson18.builder;

import org.junit.Test;
import java.util.Set;
import static org.junit.Assert.*;

public class PizzaTest {
    @Test
    public void NYPizzaTest(){
        NYPizza nyPizza = new NYPizza.Builder(NYPizza.Size.MEDIUM)
                .addTopping(Pizza.Topping.SAUSAGE)
                .addTopping(Pizza.Topping.ONION)
                .addTopping(Pizza.Topping.PEPPER)
                .build();

        Set<Pizza.Topping> toppings = nyPizza.toppings;
        assertTrue(toppings.contains(Pizza.Topping.SAUSAGE));
        assertTrue(toppings.contains(Pizza.Topping.ONION));
        assertTrue(toppings.contains(Pizza.Topping.PEPPER));
        assertFalse(toppings.contains(Pizza.Topping.MUSHROOM));
        assertFalse(toppings.contains(Pizza.Topping.HAM));
    }

    @Test
    public void CalzoneTest(){
        Calzone calzone = new Calzone.Builder()
                .addTopping(Pizza.Topping.HAM)
                .addTopping(Pizza.Topping.SAUSAGE)
                .addTopping(Pizza.Topping.ONION)
                .sauceInside()
                .build();

        Set<Pizza.Topping> toppings = calzone.toppings;
        assertTrue(toppings.contains(Pizza.Topping.SAUSAGE));
        assertTrue(toppings.contains(Pizza.Topping.ONION));
        assertTrue(toppings.contains(Pizza.Topping.HAM));
        assertFalse(toppings.contains(Pizza.Topping.PEPPER));
        assertFalse(toppings.contains(Pizza.Topping.MUSHROOM));
    }
}
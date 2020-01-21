package com.sbt.javaschool.losev.lesson18.builder;

public class Main {
    public static void main(String[] args) {
        NYPizza nyPizza = new NYPizza.Builder(NYPizza.Size.SMALL)
                .addTopping(Pizza.Topping.SAUSAGE)
                .addTopping(Pizza.Topping.ONION)
                .build();
        Calzone calzone = new Calzone.Builder()
                .addTopping(Pizza.Topping.HAM)
                .sauceInside()
                .build();
    }
}

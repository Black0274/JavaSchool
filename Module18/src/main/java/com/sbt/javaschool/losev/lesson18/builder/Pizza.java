package com.sbt.javaschool.losev.lesson18.builder;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

// Pattern builder example from "Effective Java" (Joshua Bloch)
public abstract class Pizza {
    public enum Topping{
        HAM, MUSHROOM, ONION, PEPPER, SAUSAGE
    }
    final Set<Topping> toppings;

    public abstract static class Builder<T extends Builder<T>>{
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping){
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();
        // Подклассы должны перекрывать этот метод, возвращая себя
        protected abstract T self();
    }

    Pizza(Builder<?> builder){
        toppings = builder.toppings.clone();
    }
}

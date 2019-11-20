package com.sbt.javaschool.losev.lesson6;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Handler;

public class Main {

    public static boolean AllStringConstantsEqValues(Class clazz){
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getModifiers() >= 24 &&
                    field.getModifiers() <= 28 &&
                    field.getType() == String.class){
                field.setAccessible(true);
                try {
                    if (!field.getName().equals(field.get(clazz))){
                        return false;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("All methods of Person:");
        Method[] methods = Person.class.getDeclaredMethods();
        for (Method method: methods){
            System.out.println(method.getName());
        }
        try {
            System.out.println(Person.class.getMethod("increaseAge", null).getName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println("\nAll getters of Person:");
        for (Method method: methods){
            if (method.getName().length() > 3 &&
                    method.getName().substring(0, 3).equals("get") &&
                    method.getParameterCount() == 0 &&
                    method.getReturnType() != void.class){
                System.out.println(method.getName());
            }
        }

        System.out.println("\nAll String constant names are equivalent to their names: " +
                AllStringConstantsEqValues(Person.class));

        System.out.println("\nCaching proxy:");
        CacheHandler handler = new CacheHandler(new CalculatorImpl());
        Calculator calculator = (Calculator) Proxy.newProxyInstance(Calculator.class.getClassLoader(),
                new Class[] {Calculator.class}, handler);
        System.out.println(calculator.calc("1 + 4 + 3"));
        System.out.println(calculator.calc("1 + 4 + 2"));
        System.out.println(calculator.calc("1"));
        System.out.println(calculator.calc("1 + 4 + 2"));
        System.out.println(calculator.calc("5 + 1 + 1"));
        System.out.println(calculator.calc("1"));
        System.out.println(calculator.calcNoCache("1 + 4 + 2"));
        System.out.println(calculator.calcNoCache("1"));
    }
}

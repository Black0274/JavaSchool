package com.sbt.javaschool.losev.lesson6.presentation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static Method[] getAllGetters(Class clazz){
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> getters = new ArrayList<>();
        for (Method method: methods){
            if (method.getName().length() > 3 &&
                    method.getName().substring(0, 3).equals("get") &&
                    method.getParameterCount() == 0 &&
                    method.getReturnType() != void.class){
                getters.add(method);
            }
        }
        Method[] result = new Method[getters.toArray().length];
        getters.toArray(result);
        return result;
    }

    public static Method[] getAllSetters(Class clazz){
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> setters = new ArrayList<>();
        for (Method method: methods){
            if (method.getName().length() > 3 &&
                    method.getName().substring(0, 3).equals("set") &&
                    method.getParameterCount() == 1 &&
                    method.getReturnType() == void.class){
                setters.add(method);
            }
        }
        Method[] result = new Method[setters.toArray().length];
        setters.toArray(result);
        return result;
    }

    public static boolean allStringConstantsEqValues(Class clazz){
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

        System.out.println("\nAll getters of Person:");
        methods = getAllGetters(Person.class);
        for (Method method: methods){
            System.out.println(method.getName());
        }

        System.out.println("\nAll String constant names are equivalent to their names: " +
                allStringConstantsEqValues(Person.class));

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

package com.sbt.javaschool.losev.lesson6.presentation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CacheHandler implements InvocationHandler {

    private Calculator original;
    private Map<String, Integer> cache = new HashMap<>();

    public CacheHandler(Calculator original){
        this.original = original;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String expression = (String) args[0];
        if (method.isAnnotationPresent(Cache.class)) {
            if (cache.containsKey(expression)) {
                System.out.print(expression + " CACHED ");
                return cache.get(expression);
            } else {
                int result = (Integer) method.invoke(original, args);
                cache.put(expression, result);
                System.out.print(expression + " NOT CACHED ");
                return result;
            }
        } else{
            System.out.print(expression + " DEFAULT ");
            return method.invoke(original, args);
        }

    }
}

package com.sbt.javaschool.losev.lesson9.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheProxy implements InvocationHandler {

    private Service original;
    private Map<String, List<String>> cache = new HashMap<>();

    public CacheProxy(Service original){
        this.original = original;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String item = (String) args[0];
        if (method.isAnnotationPresent(Cache.class)){
            Cache annotation = method.getAnnotation(Cache.class);
            if (annotation.cacheType() == CacheType.MEMORY) {
                if (cache.containsKey(item)) {
                    System.out.println("CACHED:");
                    return cache.get(item);
                } else {
                    System.out.println("NOT CACHED:");
                    List<String> result = (List<String>) method.invoke(original, args);
                    if (result.size() > annotation.listMaxLength()){
                        result = result.subList(0, annotation.listMaxLength());
                    }
                    cache.put(item, result);
                    return result;
                }
            }
        }
        System.out.print("DEFAULT:");
        return method.invoke(original, args);
    }
}

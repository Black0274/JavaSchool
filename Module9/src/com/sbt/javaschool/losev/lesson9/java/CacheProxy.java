package com.sbt.javaschool.losev.lesson9.java;

import com.sbt.javaschool.losev.lesson9.exceptions.IncorrectMethodDefinitionException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheProxy<T> implements InvocationHandler {

    private Service original;
    private Map<T, List<T>> cache = new HashMap<>();

    public CacheProxy(){ }

    public Service cache(Service service){
        this.original = service;
        return (Service) Proxy.newProxyInstance(Service.class.getClassLoader(),
                new Class[] {Service.class}, this);
    }


    /**
     * works for methods defined as List<T> methodName(T item) if method has @Cache annotation,
     * call basic method otherwise
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        T item = (T) args[0];
        if (method.isAnnotationPresent(Cache.class)){
            Cache annotation = method.getAnnotation(Cache.class);
            if (annotation.cacheType() == CacheType.MEMORY) {
                try{
                    if (cache.containsKey(item)) {
                        //System.out.println("CACHED:");
                        return cache.get(item);
                    } else {
                        //System.out.println("NOT CACHED:");
                        List<T> result = (List<T>) method.invoke(original, args);
                        if (result.size() > annotation.listMaxLength()) {
                            result = result.subList(0, annotation.listMaxLength());
                        }
                        cache.put(item, result);
                        return result;
                    }
                } catch (ClassCastException e){
                    throw new IncorrectMethodDefinitionException();
                }
            }
        }
        //System.out.print("DEFAULT:");
        return method.invoke(original, args);
    }
}

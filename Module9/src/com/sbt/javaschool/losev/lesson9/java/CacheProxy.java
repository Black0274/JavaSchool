package com.sbt.javaschool.losev.lesson9.java;

import com.sbt.javaschool.losev.lesson9.exceptions.IncorrectMethodDefinitionException;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class CacheProxy<T> implements InvocationHandler {

    private Service original;
    private Map<T, List<T>> cache = new HashMap<>();
    private Set<T> cacheValues = new HashSet<>();
    private String path = "./Module9/res/";
    private String cacheFileName = "cache";

    public CacheProxy() throws IOException, ClassNotFoundException {
        boolean fileExists = createCacheFile();
        if (fileExists) {
            cacheValues = deserializeCacheValues(path + cacheFileName);
        }
        System.out.println(cacheValues);
    }

    public CacheProxy(String path) throws IOException, ClassNotFoundException {
        this.path = path;
        boolean fileExists = createCacheFile();
        if (fileExists) {
            cacheValues = deserializeCacheValues(path + cacheFileName);
        }
    }

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
            else{
                String prefix = annotation.fileNamePrefix();
                if (cacheValues.contains(item)){
                    System.out.println("DESERIALIZE");
                    return deserialize(path + prefix + "_" + item);
                }
                else{
                    List<T> result = (List<T>) method.invoke(original, args);
                    if (result.size() > annotation.listMaxLength()) {
                        result = result.subList(0, annotation.listMaxLength());
                    }
                    String filename = createFile(prefix, item.toString());
                    System.out.println("SERIALIZE");
                    serialize(filename, result);
                    cacheValues.add(item);
                    serializeCacheValues(path + cacheFileName, cacheValues);
                    return result;
                }
            }
        }
        //System.out.println("DEFAULT:");
        return method.invoke(original, args);
    }

    private boolean createCacheFile() throws IOException {
        File file = new File(path, cacheFileName);
        boolean fileExists = file.exists() && file.length() > 0;
        if (!fileExists){
            file.createNewFile();
        }
        return fileExists;
    }

    private String createFile(String prefix, String itemName) throws IOException {
        File file = new File(path, prefix + "_" + itemName);
        if (!file.exists()){
            file.createNewFile();
        }
        return file.getPath();
    }

    private void serialize(String filename, List<T> list) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fos)){
            out.writeObject(list);
        }
    }

    private List<T> deserialize(String filename) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fis)){
            return (List<T>) in.readObject();
        }
    }

    private void serializeCacheValues(String filename, Set<T> set) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fos)){
            out.writeObject(set);
        }
    }

    private Set<T> deserializeCacheValues(String filename) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fis)){
            return (Set<T>) in.readObject();
        }
    }
}

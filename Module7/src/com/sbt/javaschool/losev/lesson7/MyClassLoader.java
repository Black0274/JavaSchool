package com.sbt.javaschool.losev.lesson7;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class MyClassLoader extends URLClassLoader {

    public MyClassLoader(File directory) throws MalformedURLException {
        super(new URL[]{directory.toURI().toURL()});
    }

    /**
     * Загружает класс и возвращает его если в нем есть метод с названием methodName,
     * иначе возвращает null.
     * Если класс не найден, возвращает null.
     */
    public Class<?> loadClass(String name, String methodName) {
        try {
            Class<?> clazzSecond;
            clazzSecond = this.loadClass(name);
            if (searchMethod(clazzSecond, methodName)){
                return clazzSecond;
            }
            else{
                return null;
            }
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private boolean searchMethod(Class<?> clazz, String methodName) {
        try {
            Method say = clazz.getMethod(methodName);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }
}

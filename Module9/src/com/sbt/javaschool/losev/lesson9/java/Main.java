package com.sbt.javaschool.losev.lesson9.java;

import java.lang.reflect.Proxy;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CacheProxy cacheProxy = new CacheProxy(new ServiceImpl());
        Service service = (Service) Proxy.newProxyInstance(Service.class.getClassLoader(),
                new Class[] {Service.class}, cacheProxy);

        List<String> substrings = service.work("ghci");
        substrings.forEach(System.out::println);
        System.out.println();

        substrings = service.work("kirov");
        substrings.forEach(System.out::println);
        System.out.println();

        substrings = service.work("ghci");
        substrings.forEach(System.out::println);
        System.out.println();

        substrings = service.work("абвгдеёжзийклмнопрстуфхцчшщъыьэюя");
        substrings.forEach(System.out::println);
        System.out.println();
    }
}

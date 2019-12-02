package com.sbt.javaschool.losev.lesson9.java;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        CacheProxy cacheProxy = new CacheProxy();
        Service service = cacheProxy.cache(new ServiceImpl());

        List<String> substrings = service.substrings("ghci");
        substrings.forEach(System.out::println);
        System.out.println();

        List<Integer> numbers = service.divisors(14);
        numbers.forEach(System.out::println);
        System.out.println();

        List<String> substringsF = service.substringsF("test");
        substringsF.forEach(System.out::println);
        System.out.println();

        List<Integer> numbersF = service.divisorsF(64);
        numbersF.forEach(System.out::println);
        System.out.println();
    }
}

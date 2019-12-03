package com.sbt.javaschool.losev.lesson10.java;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class Streams<T> {

    private List<T> collection;

    private Streams(List<T> list){

        this.collection = list;
    }

    public static Streams of(List list){
        return new Streams(new ArrayList(list));
    }

    public List<T> toList(){
        return collection;
    }

    public Streams<T> filter(Predicate<? super T> predicate) {
        List<T> newList = new ArrayList<>();
        for (T value: collection) {
            if (predicate.test(value)){
                newList.add(value);
            }
        }
        collection = newList;
        return this;
    }

    public <E> Streams<E> transform(Function<? super T, ? extends E> function) {
        List<E> newList = new ArrayList<>();
        for (T value: collection){
            newList.add(function.apply(value));
        }
        return new Streams<>(newList);
    }

    public <K, V> Map<K, V> toMap(Function<T, K> keyF, Function<T, V> valueF) {
        Map<K, V> map = new HashMap<>();
        for (T element: collection){
            K key = keyF.apply(element);
            V value = valueF.apply(element);
            map.put(key, value);
        }
        return map;
    }
}


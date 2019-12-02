package com.sbt.javaschool.losev.lesson10.java;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Streams<T> {

    static Streams of(List list){
        return null;
    }

    Streams filter(Predicate<? super T> predicate);

    <E> Streams<E> transform(Function<T, E> function);

    <K, V> Map<K, V> toMap(Function<T, K> keyF, Function<T, V> valueF);
}

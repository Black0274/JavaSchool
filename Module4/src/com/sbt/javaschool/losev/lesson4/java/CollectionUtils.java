package com.sbt.javaschool.losev.lesson4.java;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CollectionUtils {

    public static<T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static<T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static<T> int indexOf(List<? extends T> source, T element) {
        return source.indexOf(element);
    }

    public static<T> List<? extends T> limit(List<? extends T> source, int size) {
        return source.subList(0, size - 1);
    }

    public static<T> void add(List<? super T> source, T value) {
        source.add(value);
    }

    public static<T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static<T> boolean containsAll(List<? extends T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    public static<T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
        for (T elem : c1) {
            if (c2.contains(elem)){
                return true;
            }
        }
        return false;
    }

    public static<T extends Comparable<T>> List<T> range(List<? extends T> list, T min, T max) {
        List<T> result = newArrayList();
        for (T elem : list) {
            if (elem.compareTo(min) <= 0 || elem.compareTo(max) >= 0){
                continue;
            }
            result.add(elem);
        }
        return result;
    }

    public static<T extends Comparable<T>> List<T> range(List<? extends T> list, T min, T max, Comparator<T> comparator) {
        List<T> result = newArrayList();
        for (T elem : list) {
            if (comparator.compare(elem, min) <= 0 || comparator.compare(elem, max) >= 0){
                continue;
            }
            result.add(elem);
        }
        return result;
    }

}

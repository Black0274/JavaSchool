package com.sbt.javaschool.losev.lesson4.java;

import java.util.HashMap;
import java.util.Map;

public class MyCountMap<T> implements CountMap<T> {
    private Map<T, Integer> map = new HashMap<>();

    @Override
    public void add(T element) {
        if (!map.containsKey(element)){
            map.put(element, 1);
        }
        else{
            map.put(element, map.get(element) + 1);
        }
    }

    @Override
    public int getCount(T element) {
        if (map.containsKey(element)){
            return map.get(element);
        }
        return 0;
    }

    @Override
    public int remove(T element) {
        int count = 0;
        if (map.containsKey(element)) {
            count = map.get(element);
            map.remove(element);
        }
        return count;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<? extends T> source) {
        Map<? extends T, Integer> sourceMap = source.toMap();
        for (T element : sourceMap.keySet()) {
            if (!map.containsKey(element)){
                map.put(element, sourceMap.get(element));
            }
            else{
                map.put(element, map.get(element) + sourceMap.get(element));
            }
        }
    }

    @Override
    public Map<T, Integer> toMap() {
        return new HashMap<>(map);
    }

    @Override
    public void toMap(Map<? super T, ? super Integer> destination) {
        destination.clear();
        destination.putAll(map);
    }

    public void print(){
        map.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}

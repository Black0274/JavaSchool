package com.sbt.javaschool.losev.lesson19.decorator;

import java.util.Collection;
import java.util.Set;

// Pattern decorator example from "Effective Java" (Joshua Bloch)
public class InstrumentedSet<E> extends ForwardingSet<E> {
    private int addCount = 0; // Number of attempts to add an item

    public InstrumentedSet(Set<E> s) {
        super(s);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}

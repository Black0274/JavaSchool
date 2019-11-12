package com.sbt.javaschool.losev.lesson3.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<E> extends ArrayList<E> {

    @Override
    public Iterator<E> iterator(){ return new MyIterator();}

    private class MyIterator implements Iterator<E> {
        private int curIndex = size() - 1;

        MyIterator(){}

        @Override
        public boolean hasNext() {
            return curIndex >= 0;
        }

        @Override
        public E next() {
            if (curIndex < 0){
                throw new NoSuchElementException();
            }
            return get(curIndex--);
        }
    }
}

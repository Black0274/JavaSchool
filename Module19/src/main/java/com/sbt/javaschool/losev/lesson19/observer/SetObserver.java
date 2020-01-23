package com.sbt.javaschool.losev.lesson19.observer;

@FunctionalInterface
public interface SetObserver<E> {
    // Called when element adds to set
    void added(ObservableSet<E> set, E element);
}

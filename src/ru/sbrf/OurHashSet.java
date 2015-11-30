package ru.sbrf;

/**
 * Created by sbt-rogushkov-mv on 30.11.2015.
 */
public interface OurHashSet {
    int size();
    boolean add(Object o);
    void clear();
    boolean remove(Object o);
    boolean isEmpty();
    boolean contains(Object o);
    String toString();
}

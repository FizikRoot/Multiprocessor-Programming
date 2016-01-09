package ru.sbrf.implementations.HashSetImpl;

import ru.sbrf.OurHashSet;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.lang.Object;


/**
 * Created by Fizik on 09.01.2016.
 */
public class HashSet <T> extends AbstractCollection<T> implements OurHashSet {

    @Override
    public Iterator<T> iterator() {
        //todo don't need todo now
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    // i love c style
    private static final int DEFAULT_TABLE_SIZE = 101;

    private int currentSize = 0;
    private int occupied = 0;
    private int modCount = 0;
    private HashEntry[] array;
}

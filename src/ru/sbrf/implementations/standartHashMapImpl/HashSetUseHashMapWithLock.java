package ru.sbrf.implementations.standartHashMapImpl;

import ru.sbrf.OurHashSet;

/**
 * Created by sbt-rogushkov-mv on 30.11.2015.
 */
public class HashSetUseHashMapWithLock implements OurHashSet {
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


}

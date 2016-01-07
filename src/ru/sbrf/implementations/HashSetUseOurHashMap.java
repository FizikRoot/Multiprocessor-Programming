package ru.sbrf.implementations;

import ru.sbrf.OurHashMap;
import ru.sbrf.OurHashSet;

public class HashSetUseOurHashMap implements OurHashSet {

    private OurHashMap<Object, Object> map;

    public HashSetUseOurHashMap(){
       map = new OurHashMap<>();
    }

    @Override
    public synchronized int size() {
        return map.size();
    }

    @Override
    public synchronized boolean add(Object o) {
        return map.put(o, null);
    }

    @Override
    public void clear() {

    }

    @Override
    public synchronized boolean contains(Object o) {
        return map.contains(o) != null;
    }

    @Override
    public synchronized boolean remove(Object o) {
        return map.remove(o);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public synchronized String toString(){
        return map.strSet();
    }
}

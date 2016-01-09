package ru.sbrf.implementations;

import ru.sbrf.OurHashSet;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class HashSetUseConcurrentHashMap implements OurHashSet {

    private final ConcurrentMap<Object, Object> map;

    public HashSetUseConcurrentHashMap() {
        this.map = new ConcurrentHashMap<>();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean add(Object o) {
        return map.put(o, Boolean.TRUE) == null;
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) != null;
    }

    @Override
    public void clear() {
        map.clear();
    }


    public String toString() {
        Object[] values = map.keySet().toArray();
        String str = "[";
        for (Object value : values) {
            str = str + value.toString() + ", ";
        }
        str = str.substring(0, str.length()-2) + ']';
        return str;
    }
}

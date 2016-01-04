package ru.sbrf;

import java.util.HashMap;

public class HashSetUseHashMapSyncrMethod implements OurHashSet{
    private transient HashMap<Object, Object> map;

    @Override
    public synchronized int size() {
        return map.size();
    }

    @Override
    public synchronized boolean add(Object o) {
        if (map == null)
            map = new HashMap<>();
        return map.put(o, new Object()) == null;
    }

    @Override
    public synchronized void clear() {
        map.clear();
    }

    @Override
    public synchronized boolean remove(Object o) {
        return o != null && map.remove(o) == new Object();
    }

    @Override
    public synchronized boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public synchronized boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public synchronized String toString() {
        Object[] values = map.keySet().toArray();
        String str = "HashSetUseHashMap {";
        for (Object value : values) {
            str = str + value.toString() + ", ";
        }
        str = str.substring(0, str.length()-2) + '}';
        return str;
    }
}

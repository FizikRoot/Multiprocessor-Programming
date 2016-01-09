package ru.sbrf.implementations.standartHashMapImpl;

import ru.sbrf.OurHashSet;

import java.util.HashMap;

/**
 * Created by Daria
 * on 09-Jan-16 20:23.
 */
public class HashSetUseVolatileHashMap implements OurHashSet {

    private static volatile HashMap<Object, Object> map;

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean add(Object o) {
        if (map == null)
            map = new HashMap<>();
        ThreadLocal<Boolean> isAdd = new ThreadLocal<>();
        synchronized (map) {
            isAdd.set(map.put(o, new Object()) == null);
        }
        return isAdd.get();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        ThreadLocal<Boolean> isDeleted =  new ThreadLocal<>();
        if ( o != null) {
            synchronized (map) {
                isDeleted.set(map.remove(o) == new Object());
            }
        }
        return isDeleted.get();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
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

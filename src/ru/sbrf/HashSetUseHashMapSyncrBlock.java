package ru.sbrf;

import java.util.HashMap;

public class HashSetUseHashMapSyncrBlock implements OurHashSet{
    private static transient HashMap<Object, Object> map;

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean add(Object o) {
        if (map == null)
            map = new HashMap<>();
        ThreadLocal<Boolean> isAdd = new ThreadLocal<>();
        synchronized (this) {
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
        //todo
        return o != null && map.remove(o) == new Object();
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
        String str = "HashSetUseHashMap {";
        for (Object value : values) {
            str = str + value.toString() + ", ";
        }
        str = str.substring(0, str.length()-2) + '}';
        return str;
    }
}

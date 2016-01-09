package ru.sbrf.implementations.standartHashMapImpl;

import ru.sbrf.OurHashSet;

import java.util.HashMap;

public class HashSetUseHashMap<E> implements OurHashSet {
    protected transient HashMap<E, Object> map;

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean add(Object o) {
        if (map==null) map= new HashMap<>();
        return map.put((E) o,new Object()) == null;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        if (o != null) {
            return map.remove((E) o) == new Object();
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey((E) o);
    }
}

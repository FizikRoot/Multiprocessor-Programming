package ru.sbrf;

import java.util.HashMap;

/**
 * Created by sbt-rogushkov-mv on 30.11.2015.
 */
public class HashSetUseHashMap<E> implements OurHashSet{
    private transient HashMap<E, Object> map;

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

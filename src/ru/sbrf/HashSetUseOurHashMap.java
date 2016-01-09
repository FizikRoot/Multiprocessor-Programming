package ru.sbrf;

public class HashSetUseOurHashMap implements OurHashSet{

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
    public synchronized void clear() {
        map.clear();
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
    public synchronized boolean isEmpty() {
        return map.size()==0;
    }

    public synchronized String toString(){
        return map.strSet();
    }
}

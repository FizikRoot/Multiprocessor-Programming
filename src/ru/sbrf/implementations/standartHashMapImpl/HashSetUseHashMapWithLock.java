package ru.sbrf.implementations.standartHashMapImpl;

import ru.sbrf.OurHashSet;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by sbt-rogushkov-mv on 30.11.2015.
 */
public class HashSetUseHashMapWithLock<T> extends HashSetUseHashMap<T> {
    Lock lock = new ReentrantLock();
    @Override
    public int size() {
        ThreadLocal<Integer> size =  new ThreadLocal<>();
        lock.lock();
        try {
            size.set(map.size());
        }
        finally {
            lock.unlock();
        }
        return size.get();
    }

    @Override
    public boolean add(Object o) {
        ThreadLocal<Boolean> isAdd =  new ThreadLocal<>();
        lock.lock();
        try {
            isAdd.set(super.add(o));
        }
        finally {
            lock.unlock();
        }
        return isAdd.get();
    }

    @Override
    public boolean remove(Object o) {
        ThreadLocal<Boolean> isDel =  new ThreadLocal<>();
        lock.lock();
        try {
            isDel.set(super.remove(o));
        }
        finally {
            lock.unlock();
        }
        return isDel.get();
    }

    @Override
    public boolean contains(Object o) {
        ThreadLocal<Boolean> isContain =  new ThreadLocal<>();
        lock.lock();
        try {
            isContain.set(super.contains(o));
        }
        finally {
            lock.unlock();
        }
        return isContain.get();
    }


}

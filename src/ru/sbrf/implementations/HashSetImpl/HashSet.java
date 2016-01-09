package ru.sbrf.implementations.HashSetImpl;

import ru.sbrf.OurHashSet;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.lang.Object;


/**
 * Created by Fizik on 09.01.2016.
 */
public class HashSet extends AbstractCollection implements OurHashSet {

    public HashSet() {
        allocateArray(DEFAULT_TABLE_SIZE);
        clear();
    }

    @Override
    @Deprecated
    public Iterator iterator() {
        //todo don't need todo now
        return null;
    }

    @Override
    public synchronized int size() {
        return currentSize;
    }

    @Override
    public synchronized boolean add(Object o) {
        int currentPos = findPosition(0);
        if (contains(o))
            return false;

        if (array[currentPos] == null)
            occupied++;
        array[currentPos] = new HashEntry(o, true);
        currentSize++;
        modCount++;

        if (occupied > array.length / 2)
            reSize();

        return true;
    }

    @Override
    public synchronized void clear() {
        currentSize = occupied = 0;
        modCount++;
        for (int i = 0; i < array.length; i++)
            array[i] = null;
    }

    @Override
    public synchronized boolean remove(Object o) {
        int currentPos = findPosition(o);
        if (!contains(o))
            return false;

        array[currentPos].isActive = false;
        currentSize--;
        modCount++;

        return true;
    }

    @Override
    public synchronized boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public synchronized boolean contains(Object o) {
        int pos = findPosition(o);
        return array[pos] != null && array[pos].isActive;
    }

    // i love c style
    private void allocateArray(int arraySize) {
        array = new HashEntry[nextPrime(arraySize)];
    }

    private int findPosition(Object x) {
        int offset = 1;
        int currentPos = (x == null) ? 0 : Math.abs(x.hashCode() % array.length);

        while (array[currentPos] != null) {
            if (x == null) {
                if (array[currentPos].element == null)
                    break;
            } else if (x.equals(array[currentPos].element))
                break;

            currentPos += offset;                  // Compute ith probe
            offset += 2;
            if (currentPos >= array.length)       // Implement the mod
                currentPos -= array.length;
        }

        return currentPos;
    }

    private static int nextPrime(int n) {
        boolean isPrime = false;
        int start = 3;
        if (n % 2 == 0) {
            n = n + 1;
        }
        while (!isPrime) {
            isPrime = true;
            int m = (int) Math.ceil(Math.sqrt(n));
            for (int i = start; i <= m; i = i + 2) {
                if (n % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (!isPrime) {
                n = n + 2;
            }
        }
        return n;
    }

    private void reSize() {
        HashEntry[] oldArray = array;

        allocateArray(nextPrime(4 * size()));
        currentSize = 0;
        occupied = 0;

        for (int i = 0; i < oldArray.length; i++)
            if (oldArray[i] != null && oldArray[i].isActive)
                add(oldArray[i].element);
    }

    private static final int DEFAULT_TABLE_SIZE = 103;

    private int currentSize = 0;
    private int occupied = 0;
    private int modCount = 0;
    private HashEntry[] array;
}

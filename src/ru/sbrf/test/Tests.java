package ru.sbrf.test;

import org.junit.Before;
import org.junit.Test;
import ru.sbrf.test.Checker;

import java.util.Arrays;
import java.util.HashSet;

import static junit.framework.TestCase.assertEquals;

public class Tests {

    String res;
    Checker c;

    @Before
    public void setUp() throws Exception {
        res = new HashSet<>(Arrays.asList("A", "B", "C", "D")).toString();
        c = new Checker();
    }

    @Test
    public void testConcurrentHashMap() throws InterruptedException {
        assertEquals(res, c.check("ConcurrentHashMap"));
    }

    @Test
    public void testOurHashMap() throws InterruptedException {
        assertEquals(res, c.check("OurHashMap"));
    }

    @Test
    public void testHashMapSyncBlock() throws InterruptedException {
        assertEquals(res, c.check("SyncBlock"));
    }

    @Test
    public void testHashMapSyncMethod() throws InterruptedException {
        assertEquals(res, c.check("SyncMethod"));
    }

    @Test
    public void testHashMapWithLocks() throws InterruptedException {
        assertEquals(res, c.check("WithLocks"));
    }
}


package ru.sbrf;

import org.junit.Test;

import ru.sbrf.OurHashSet;
import ru.sbrf.implementations.HashSetImpl.HashSet;
import ru.sbrf.implementations.HashSetUseConcurrentHashMap;
import ru.sbrf.implementations.HashSetUseOurHashMap;
import ru.sbrf.implementations.standartHashMapImpl.HashSetUseHashMapSyncrBlock;
import ru.sbrf.implementations.standartHashMapImpl.HashSetUseHashMapSyncrMethod;
import ru.sbrf.implementations.standartHashMapImpl.HashSetUseHashMapWithLock;
import ru.sbrf.implementations.standartHashMapImpl.HashSetUseVolatileHashMap;

import java.util.concurrent.*;


public class ConcurrentHashSetTest {

    public final static int THREAD_POOL_SIZE = 2;
    public final static int CAPACITY = 10000;
    public final static int TEST_NUMBER = 5;

    private static long overheadTime = 1021945; //ns

    @Test
    public void testHashSetUseConcurrentHashMap() throws Exception {
        OurHashSet hs = new HashSetUseConcurrentHashMap();
        throughtputCount(hs);
        latencyCount(hs);
    }

    @Test
    public void testHashSetUseOurHashMap() throws Exception {
        OurHashSet hs = new HashSetUseOurHashMap();
        throughtputCount(hs);
        latencyCount(hs);
    }

    @Test
    public void testHashSetUseHashMapSyncBlock() throws Exception {
        OurHashSet hs = new HashSetUseHashMapSyncrBlock();
        throughtputCount(hs);
        latencyCount(hs);
    }

    @Test
    public void testHashSetUseHashMapSyncMethod() throws Exception {
        OurHashSet hs = new HashSetUseHashMapSyncrMethod();
        throughtputCount(hs);
        latencyCount(hs);
    }

    @Test
    public void testHashSetUseHashMapWithLock() throws Exception {
        OurHashSet hs = new HashSetUseHashMapWithLock();
        throughtputCount(hs);
        latencyCount(hs);
    }

    @Test
    public void testHashSetUseVolatileHashMap() throws Exception {
        OurHashSet hs = new HashSetUseVolatileHashMap();
        throughtputCount(hs);
        latencyCount(hs);
    }

    @Test
    public void testHashSet() throws Exception {
        OurHashSet hs = new HashSet();
        throughtputCount(hs);
        latencyCount(hs);
    }

    public long getThroughtputOverheadTime() throws InterruptedException {

        long time = 0;

        for (int i = 0; i < TEST_NUMBER * 100; i++) {

            long startTime = System.nanoTime();

            ExecutorService es = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
            for (int j = 0; j < THREAD_POOL_SIZE; j++) {
                es.execute(new Runnable() {
                    public void run() {

                    }
                });
            }
            es.shutdown();
            es.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

            long endTime = System.nanoTime();
            time = time + (endTime - startTime);
        }

        return time / (TEST_NUMBER * 100);

    }

    public static void throughtputCount(final OurHashSet hs) throws InterruptedException {

        System.out.println("Test started for: " + hs.getClass().getSimpleName());

        long totalTime;
        long sumTime = 0;

        for (int i = 0; i < TEST_NUMBER; i++) {

            long startTime = System.nanoTime();

            ExecutorService es = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

            for (int j = 0; j < THREAD_POOL_SIZE; j++) {
                es.execute(new Runnable() {
                    @SuppressWarnings("unused")
                    @Override
                    public void run() {
                        for (int i = 0; i < CAPACITY; i++) {
                            Integer r = (int) Math.ceil(Math.random() * CAPACITY);
                            hs.add(r);
                        }
                    }
                });
            }

            es.shutdown();

            es.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

            long entTime = System.nanoTime();

            totalTime = ((entTime - startTime) - overheadTime) / 1000000L;
            sumTime += totalTime;
            //System.out.println(CAPACITY + " elem added in " + totalTime + " ms");
        }

        long avgTime = sumTime / TEST_NUMBER;
        int throughtput = (int) (CAPACITY / avgTime);

        System.out.println("For " + hs.getClass().getSimpleName() + " throughput is " + throughtput + " elem/ms");
    }

    public static void latencyCount(final OurHashSet hs) throws InterruptedException {

        final Thread myThread = new Thread() {
            public void run() {

                long totalTime;
                long sumTime = 0;

                for (int j = 0; j < TEST_NUMBER; j++) {

                    long startTime = System.nanoTime();

                    for (int i = 0; i < CAPACITY; i++) {
                        Integer r = (int) Math.ceil(Math.random() * CAPACITY);
                        hs.add(r);
                    }

                    long entTime = System.nanoTime();

                    totalTime = (entTime - startTime);
                    sumTime += totalTime;
                    //System.out.println(CAPACITY + " elem added in " + totalTime + " ms");
                }

                int latency = (int) ((sumTime / CAPACITY) / TEST_NUMBER);

                System.out.println("For " + hs.getClass().getSimpleName() + " latency is " + latency + " ns");
            }
        };

        myThread.start();
        myThread.join();

    }
}
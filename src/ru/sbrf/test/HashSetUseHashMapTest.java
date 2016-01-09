package ru.sbrf.test;

import org.junit.Test;

import ru.sbrf.OurHashSet;
import ru.sbrf.implementations.HashSetUseConcurrentHashMap;
import ru.sbrf.implementations.HashSetUseOurHashMap;
import ru.sbrf.implementations.standartHashMapImpl.HashSetUseHashMapSyncrBlock;
import ru.sbrf.implementations.standartHashMapImpl.HashSetUseHashMapSyncrMethod;
import ru.sbrf.implementations.standartHashMapImpl.HashSetUseHashMapWithLock;
import ru.sbrf.implementations.standartHashMapImpl.HashSetUseVolatileHashMap;

import java.util.concurrent.*;


public class HashSetUseHashMapTest {

    public final static int THREAD_POOL_SIZE = 4;
    public final static int CAPACITY = 5000;
    public final static int TEST_NUMBER = 10;

    private static long overheadTime = 1021945; //ns

    public void test(final OurHashSet hs){
        Executor e = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        for(int i = 0; i < CAPACITY; i++){
            e.execute(new Runnable(){
                public void run(){
                    hs.add(new Object());
                }
            });
        }
    }

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

    public long getThroughtputOverheadTime() throws InterruptedException {

        long time = 0;

        for (int i  = 0 ; i < TEST_NUMBER*100 ; i++) {

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

        return time / (TEST_NUMBER*100) ;

    }

    public static void throughtputCount(final OurHashSet hs) throws InterruptedException {

        System.out.println("Test started for: " + hs.getClass());

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
        String[] classPath = hs.getClass().toString().split("\\.");

        long avgTime = sumTime / TEST_NUMBER;
        int throughtput = (int) (CAPACITY / avgTime);


        if (classPath.length == 5)
            System.out.println("For " + classPath[4] + " throughput is " + throughtput + " elem/ms\n");
        else
            System.out.println("For " + classPath[3] + " throughput is " + throughtput + " elem/ms\n");
    }

    public static void latencyCount(final OurHashSet hs) throws InterruptedException {

        System.out.println("Test started for: " + hs.getClass());


        final Thread myThread = new Thread(){
            public void run(){

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

                String[] classPath = hs.getClass().toString().split("\\.");

                int latency = (int) (sumTime / CAPACITY);

                if (classPath.length == 5)
                    System.out.println("For " + classPath[4] + " latency is " + latency + " ns\n");
                else
                    System.out.println("For " + classPath[3] + " latency is " + latency + " ns\n");

            }
        };

        myThread.start();
        myThread.join();

    }
}
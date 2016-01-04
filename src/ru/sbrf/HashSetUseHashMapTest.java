package ru.sbrf;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.*;

import static org.junit.Assert.*;


public class HashSetUseHashMapTest {
    public final static int THREAD_POOL_SIZE = 5;

    @org.junit.Before
    public void setUp() throws Exception {

    }

    public void test(final Map<Object,Object> test){
        Executor e = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        for(int i =0; i < 5000; i++){
            e.execute(new Runnable(){
                public void run(){
                    test.put(new Object(),new Object());
                }
            });
        }
    }

    @org.junit.Test
    public void testHashMap() throws Exception {
        HashMap<Object, Object> hashMap = new HashMap<>();
        test(hashMap); //will probably go into an infinite loop
        System.out.println(hashMap.size());
    }


    @org.junit.Test
    public void testConcurrentHashMap() throws Exception {
        ConcurrentHashMap<Object, Object> hashMap = new ConcurrentHashMap<>();
        test(hashMap); //will *never* go into an infinite loop
        System.out.println(hashMap.size());
    }

    @org.junit.Test
    public void testCrunchifyPerformHash() throws Exception {
        Map<String, Integer> crunchifySynchronizedMapObject = new Hashtable<String, Integer>();
        crunchifyPerform(crunchifySynchronizedMapObject);
    }


    @org.junit.Test
    public void testCrunchifyPerformConcurrentHash() throws Exception {
        Map<String, Integer> crunchifySynchronizedMapObject = new ConcurrentHashMap<String, Integer>();
        crunchifyPerform(crunchifySynchronizedMapObject);
    }


    @org.junit.Test
    public void testConcurrentSynchroHashMap() throws Exception {
        Map<String, Integer> crunchifySynchronizedMapObject = Collections.synchronizedMap(new HashMap<String, Integer>());
        crunchifyPerform(crunchifySynchronizedMapObject);
    }

    public static void crunchifyPerform(final Map<String, Integer> crunchifyThreads) throws InterruptedException {

        System.out.println("Test started for: " + crunchifyThreads.getClass());
        long averageTime = 0;
        for (int i = 0; i < 5; i++) {

            long startTime = System.nanoTime();
            ExecutorService crunchifyExServer = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

            for (int j = 0; j < THREAD_POOL_SIZE; j++) {
                crunchifyExServer.execute(new Runnable() {
                    @SuppressWarnings("unused")
                    @Override
                    public void run() {

                        for (int i = 0; i < 500000; i++) {
                            Integer crunchifyRandomNumber = (int) Math.ceil(Math.random() * 550000);

                            // Retrieve value. We are not using it anywhere
                            Integer crunchifyValue = crunchifyThreads.get(String.valueOf(crunchifyRandomNumber));

                            // Put value
                            crunchifyThreads.put(String.valueOf(crunchifyRandomNumber), crunchifyRandomNumber);
                        }
                    }
                });
            }

            // Make sure executor stops
            crunchifyExServer.shutdown();

            // Blocks until all tasks have completed execution after a shutdown request
            crunchifyExServer.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

            long entTime = System.nanoTime();
            long totalTime = (entTime - startTime) / 1000000L;
            averageTime += totalTime;
            System.out.println("2500K entried added/retrieved in " + totalTime + " ms");
        }
        System.out.println("For " + crunchifyThreads.getClass() + " the average time is " + averageTime / 5 + " ms\n");
    }
}
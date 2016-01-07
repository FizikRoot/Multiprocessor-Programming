package ru.sbrf.test;

import ru.sbrf.OurHashSet;
import ru.sbrf.implementations.HashSetUseConcurrentHashMap;
import ru.sbrf.implementations.HashSetUseOurHashMap;
import ru.sbrf.implementations.standartHashMapImpl.HashSetUseHashMapSyncrBlock;
import ru.sbrf.implementations.standartHashMapImpl.HashSetUseHashMapSyncrMethod;
import ru.sbrf.implementations.standartHashMapImpl.HashSetUseHashMapWithLock;


public class Checker {

    private OurHashSet hs;

    public String check(String type) throws InterruptedException {

        switch (type){
            case "ConcurrentHashMap" :
                hs = new HashSetUseConcurrentHashMap();
                break;
            case "OurHashMap" :
                hs = new HashSetUseOurHashMap();
                break;
            case "SyncBlock" :
                hs = new HashSetUseHashMapSyncrBlock();
                break;
            case "SyncMethod" :
                hs = new HashSetUseHashMapSyncrMethod();
                break;
            case "WithLock" :
                hs = new HashSetUseHashMapWithLock();
                break;

        }

        hs.add('A');
        hs.add('B');

        Thread t1 = new Thread(){
            public void run(){
                hs.add('C');
                hs.add('E');
                //System.out.println(this.getName());
                //System.out.println(hs);
            }
        };

        Thread t2 = new Thread(){
            public void run(){
                while(!hs.contains('E')) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                hs.remove('E');
                hs.add('D');
                //System.out.println(this.getName());
                //System.out.println(hs);
            }
        };

        t1.setName("th1");
        t2.setName("th2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        return hs.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        Checker c = new Checker();
        System.out.println(c.check("SyncMethod"));
    }

}



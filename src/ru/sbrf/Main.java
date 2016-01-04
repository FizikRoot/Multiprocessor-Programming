package ru.sbrf;

public class Main {

    public static final Integer numberOfThreads = 2;

    public static void main(String[] args) {

        final OurHashSet hs = new HashSetUseHashMap();

        hs.add('A');
        hs.add('B');

        Thread t1 = new Thread(){
            public void run(){
                hs.add('C');
                System.out.println(this.getName());
                System.out.println(hs);
            }
        };

        Thread t2 = new Thread(){
            public void run(){
                hs.remove('C');
                hs.add('D');
                System.out.println(this.getName());
                System.out.println(hs);
            }
        };

        t1.setName("th1");
        t2.setName("th2");

        t1.start();
        t2.start();

    }
}

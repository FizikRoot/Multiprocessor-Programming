package ru.sbrf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by sbt-rogushkov-mv on 30.11.2015.
 */
public class Main {
    public static final Integer numberOfCore = 4;
    public static void main(String[] args) {
        OurHashSet hs = new HashSetUseHashMap<Object>();

        hs.add("A");
        hs.add(10);
        System.out.println(hs);
        for (int i = 0; i < numberOfCore ; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                }
            });
            thread.start();
        }
    }
}

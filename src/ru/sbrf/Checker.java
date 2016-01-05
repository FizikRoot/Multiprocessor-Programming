import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Checker {

    public static String check() {

        final OurHashSet hs = new HashSetUseOurHashMap();

        hs.add('A');
        hs.add('B');

        ExecutorService th1 = Executors.newSingleThreadExecutor();
        Future<OurHashSet> result1 = th1.submit(new Callable<OurHashSet>() {
            public OurHashSet call() throws Exception {
                hs.add('C');
                hs.add('E');
                return hs;
            }
        });

        ExecutorService th2 = Executors.newSingleThreadExecutor();
        Future<OurHashSet> result2 = th2.submit(new Callable<OurHashSet>() {
            public OurHashSet call() throws Exception {
                while(!hs.contains('E')) {
                    try {
                        wait(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                hs.remove('E');
                hs.add('D');
                return hs;
            }
        });

        OurHashSet hs1 = new HashSetUseOurHashMap();
        try {
            result1.get();
            hs1 = result2.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        th1.shutdown();
        th2.shutdown();

        return hs1.toString();
    }

    public static void main(String[] args) {
        System.out.println(Checker.check());
    }

}



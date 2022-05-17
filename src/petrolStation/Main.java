package petrolStation;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        PetrolStation petrolStation = new PetrolStation(500);
        CountDownLatch cdl = new CountDownLatch(3);
        ExecutorService es = Executors.newFixedThreadPool(5);

        while (petrolStation.getAmount() > 0) {
            es.submit(myRunnable(petrolStation, cdl));
            es.submit(myRunnable(petrolStation, cdl));
            es.submit(myRunnable(petrolStation, cdl));
            es.submit(myRunnable(petrolStation, cdl));
            es.submit(myRunnable(petrolStation, cdl));
            //По какой-то причине программа зациклилась, так и не понял, почему
        }
        es.shutdown();
    }

    static Runnable myRunnable(PetrolStation petrolStation, CountDownLatch cdl) {
        return new Runnable() {
            @Override
            public void run() {
                petrolStation.doRefuel(20);
                cdl.countDown();
                System.out.println(petrolStation.getAmount());
            }
        };
    }
}
package petrolStation;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PetrolStation {
    private double amount;
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Semaphore sem = new Semaphore(3);

    public PetrolStation(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void doRefuel(double refuelAmount) {
        try {
            sem.acquire();
            readWriteLock.writeLock().lock();
            Thread.sleep(new Random().nextInt(10 - 3 + 1) + 3);
            amount = amount - refuelAmount;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sem.release();
            readWriteLock.writeLock().unlock();
        }
    }
}
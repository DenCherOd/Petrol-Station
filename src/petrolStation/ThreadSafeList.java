package petrolStation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeList<E> {
    private List list = new ArrayList<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void add(E e) {
        readWriteLock.writeLock().lock();
        list.add(e);
        readWriteLock.writeLock().unlock();
    }

    public void remove(int index) {
        readWriteLock.writeLock().lock();
        list.remove(index);
        readWriteLock.writeLock().unlock();
    }

    public void get(int index) {
        readWriteLock.readLock().lock();
        list.get(index);
        readWriteLock.readLock().unlock();
    }
}
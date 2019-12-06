import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Queue<T> {

    private LinkedList<T> queue;
    private int size;

    private Lock lock;
    private Condition addCondition;
    private Condition takeCondition;



    Queue (int queueSize) {
        queue = new LinkedList<>();
        lock = new ReentrantLock(true);
        addCondition = lock.newCondition();
        takeCondition = lock.newCondition();
        size = queueSize;

    }

    boolean addElement (T obj) throws InterruptedException {
        lock.lock();
        boolean isSucsessfull = false;
        try {
            while (queue.size() == size) {
                addCondition.await();
            }
            isSucsessfull = queue.add(obj);
            takeCondition.signal();
        } finally {
            lock.unlock();
        }
        return isSucsessfull;
    }

    T getFirst () throws InterruptedException {
        lock.lock();
        T obj = null;
        try {
            while (queue.size() == 0) {
                takeCondition.await();
            }
            obj = queue.removeFirst();
            addCondition.signal();

        } finally {
            lock.unlock();
        }
        return obj;
    }

    int size() {
        lock.lock();
        try {
            return size = queue.size();
        } finally {
            lock.unlock();
        }
    }








}

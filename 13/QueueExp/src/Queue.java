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
        lock = new ReentrantLock();
        addCondition = lock.newCondition();
        takeCondition = lock.newCondition();
        size = queueSize;

    }

    boolean addElement (T obj) {
        lock.lock();
        boolean isSucsessfull = false;
        try {
            while (queue.size() == size) {
                addCondition.await();
            }
            isSucsessfull = queue.add(obj);
            takeCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return isSucsessfull;
    }

    T getFirst () {
        lock.lock();
        T obj = null;
        try {
            while (queue.size() == 0) {
                takeCondition.await();
            }
            obj = queue.removeFirst();
            addCondition.signal();

        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            lock.unlock();
        }
        return obj;
    }









}

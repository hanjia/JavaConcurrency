package concurrency.intrinsiclock;

/**
 * 
 * Synchronized methods:
 * 
 * First, it is not possible for two invocations of synchronized methods on the same object to interleave. 
 * When one thread is executing a synchronized method for an object, 
 * all other threads that invoke synchronized methods for the same object block (suspend execution) until the first thread is done with the object.
 * 
 * Second, when a synchronized method exits, 
 * it automatically establishes a happens-before relationship with any subsequent invocation of a synchronized method for the same object. 
 * This guarantees that changes to the state of the object are visible to all threads.
 * 
 * 
 * @author hajia
 *
 */
public class SynchronizedCounter {
    private int c = 0;

    public synchronized void increment() {
        c++;
    }

    public synchronized void decrement() {
        c--;
    }

    public synchronized int value() {
        return c;
    }
}
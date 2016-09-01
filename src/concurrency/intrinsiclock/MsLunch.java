package concurrency.intrinsiclock;

/**
 * 
 * Unlike synchronized methods, synchronized statements must specify the object that provides the intrinsic lock.
 * 
 * To avoid synchronizing invocations of other objects' methods.
 * Synchronized statements are also useful for improving concurrency with fine-grained synchronization. 
 * 
 * @author hanjia
 *
 */
public class MsLunch {
    private long c1 = 0;
    private long c2 = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void inc1() {
        synchronized(lock1) {
            c1++;
        }
    }

    public void inc2() {
        synchronized(lock2) {
            c2++;
        }
    }
}
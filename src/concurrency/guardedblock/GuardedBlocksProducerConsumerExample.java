package concurrency.guardedblock;

/**
 * 
 * Guarded block.
 * Such a block begins by polling a condition that must be true before the block can proceed. 
 * There are a number of steps to follow in order to do this correctly.
 * 
 * A more efficient guard invokes Object.wait to suspend the current thread. 
 * The invocation of wait does not return until another thread has issued a notification that some special event may have occurred 
 * — though not necessarily the event this thread is waiting for.
 * 
 * @author hanjia
 *
 */
public class GuardedBlocksProducerConsumerExample {
    public static void main(String[] args) {
        Drop drop = new Drop();
        Thread producerThread = new Thread(new Producer(drop));
        producerThread.start();
        Thread consumerThread =  new Thread(new Consumer(drop));
        consumerThread.start();
    }
}

package concurrency.collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConcurrencyBlockingQueueTest {
	/**
	 * https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingQueue.html
	 */
	public static void main(String[] args) {
		BlockingQueue<String> q = new LinkedBlockingQueue<String>(3);
		Producer p = new Producer(q);
		Consumer c1 = new Consumer(q);
		Consumer c2 = new Consumer(q);
		new Thread(p).start();
		new Thread(c1).start();
		new Thread(c2).start();
	}

}

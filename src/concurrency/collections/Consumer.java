package concurrency.collections;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
	private final BlockingQueue<String> queue;

	Consumer(BlockingQueue<String> q) {
		queue = q;
	}

	public void run() {
	     try {
	       while (true) { 
	    	   consume(queue.take()); 
	       }
	     } catch (InterruptedException ex) {
			System.out.println("Consumer is interrupted!");
	     }
	   }

	public void consume(String x) {
		System.out.println("Consumer in thread" + Thread.currentThread().getName() + " has fetched a message: " + x);
	}
}

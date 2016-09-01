package concurrency.collections;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueueImplementation<T> {
	private Queue<T> queue = new LinkedList<T>();
  	private int  limit = 10;

	public BlockingQueueImplementation(int limit) {
		this.limit = limit;
	}

	public synchronized void enqueue(T item) throws InterruptedException {
		while (this.queue.size() == this.limit) {
			wait();
		}
		if (this.queue.size() != 0) {
			notifyAll();
		}
		this.queue.add(item);
	}

	public synchronized T dequeue() throws InterruptedException {
		while (this.queue.size() == 0) {
			wait();
		}
		if (this.queue.size() != this.limit) {
			notifyAll();
		}

		return this.queue.poll();
	}
}

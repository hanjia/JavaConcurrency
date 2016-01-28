package concurrency.collections;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
	private final BlockingQueue<String> queue;
	final String[] messageList = {
			"Hello A!",
			"Hello B!",
			"Hello C!",
			"Hello D!"
	};
	private int count;
	
	public Producer(BlockingQueue<String> q) {
		queue = q;
	}

	public void run() {
		try {
			while (true) {
				if(count < messageList.length)
					queue.put(produce());
			}
		} catch (InterruptedException ex) {
			System.out.println("Producer is interrupted!");
		}
	}

	public String produce() {
		return messageList[count++];
	}
}


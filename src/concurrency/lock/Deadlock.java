package concurrency.lock;

/**
 * 
 * Deadlock: describes a situation where two or more threads are blocked forever, waiting for each other.
 * 
 * @author hanjia
 *
 */
public class Deadlock {	
	public static void main(String[] args) {
		final Friend alphonse = new Friend("Alphonse");
		final Friend gaston = new Friend("Gaston");
		
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				alphonse.bow(gaston);
			}
		});
		thread1.start();
		
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				gaston.bow(alphonse);
			}
		});
		thread2.start();
	}
}

class Friend {
	private final String name;

	public Friend(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public synchronized void bow(Friend bower) {
		System.out.format("%s: %s" + " has bowed to me!%n", this.name, bower.getName());
		bower.bowBack(this);
	}

	public synchronized void bowBack(Friend bower) {
		System.out.format("%s: %s" + " has bowed back to me!%n", this.name, bower.getName());
	}
}
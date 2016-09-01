package concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

/**
 * 
 * Use Lock objects to solve deadlock problems.
 * 
 * Lock objects work very much like the implicit locks used by synchronized code. As with implicit locks, only one thread can own a Lock object at a time. 
 * Lock objects also support a wait/notify mechanism, through their associated Condition objects.
 * The biggest advantage of Lock objects over implicit locks is their ability to back out of an attempt to acquire a lock.
 * 
 * 
 * @author hanjia
 *
 */
public class Safelock {  
    public static void main(String[] args) {
        final NewFriend alphonse = new NewFriend("Alphonse");
        final NewFriend gaston = new NewFriend("Gaston");
        Thread thread1 = new Thread(new BowLoop(alphonse, gaston));
        thread1.start();
        Thread thread2 = new Thread(new BowLoop(gaston, alphonse));
        thread2.start();
    }
}

class NewFriend {
    private final String name;
    private final Lock lock = new ReentrantLock();

    public NewFriend(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean impendingBow(NewFriend bower) {
        Boolean myLock = false;
        Boolean yourLock = false;
        try {
            myLock = lock.tryLock();
            yourLock = bower.lock.tryLock();
        } finally {
            if (! (myLock && yourLock)) {
                if (myLock) {
                    lock.unlock();
                }
                if (yourLock) {
                    bower.lock.unlock();
                }
            }
        }
        return myLock && yourLock;
    }
        
    public void bow(NewFriend bower) {
        if (impendingBow(bower)) {
            try {
                System.out.format("%s: %s has" + " bowed to me!%n", this.name, bower.getName());
                bower.bowBack(this);
            } finally {
                lock.unlock();
                bower.lock.unlock();
            }
        } else {
            System.out.format("%s: %s started to bow to me, but saw that I was already bowing to him.%n", this.name, bower.getName());
        }
    }

    public void bowBack(NewFriend bower) {
        System.out.format("%s: %s has" + " bowed back to me!%n", this.name, bower.getName());
    }
}

class BowLoop implements Runnable {
    private NewFriend bower;
    private NewFriend bowee;

    public BowLoop(NewFriend bower, NewFriend bowee) {
        this.bower = bower;
        this.bowee = bowee;
    }

	public void run() {
		Random random = new Random();
		while (true) {
			try {
				Thread.sleep(random.nextInt(10));
			} catch (InterruptedException e) {
				
			}
			bowee.bow(bower);
		}
	}
}
        


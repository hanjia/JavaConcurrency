package concurrency;

public class GuardedBlocks {
	boolean joy;
	
	public synchronized void guardedJoy() {
	    // This guard only loops once for each special event, which may not
	    // be the event we're waiting for.
	    while(!joy) {
	        try {
	            threadMessage("Still waiting...");
	            wait();
	        } catch (InterruptedException e) {}
	    }
	    threadMessage("Joy and efficiency have been achieved!");
	}
	
	public synchronized void notifyJoy() {
	    joy = true;
	    notifyAll();
	}
	
    // Display a message, preceded by the name of the current thread
    public static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s%n", threadName, message);
    }
	
	 private static class MessageLoop implements Runnable {
		 private GuardedBlocks g;
		 public MessageLoop(GuardedBlocks gb){
			 g = gb;
		 }
	     public void run() {
	         String[] importantInfo = {
	             "Mares eat oats",
	             "Does eat oats",
	             "Little lambs eat ivy",
	             "A kid will eat ivy too"
	         };
	         try {
	             for (int i = 0; i < importantInfo.length; i++) {
	                 // Pause for 1 seconds
	                 Thread.sleep(1000);
	                 // Print a message
	                 threadMessage(importantInfo[i]);
	             }
	             g.notifyJoy();
	         } catch (InterruptedException e) {
	             threadMessage("I wasn't done!");
	         }
	     }
	 }
	
	public static void main(String[] args){
		GuardedBlocks d = new GuardedBlocks();		
        threadMessage("Starting MessageLoop thread");
		Thread t = new Thread(new MessageLoop(d));
        t.start();
		d.guardedJoy();
	}
}

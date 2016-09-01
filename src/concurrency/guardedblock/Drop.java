package concurrency.guardedblock;

/**
 * 
 * The Drop class was written in order to demonstrate guarded blocks.
 * 
 * The two threads communicate using a shared object. 
 * Coordination is essential: the consumer thread must not attempt to retrieve the data before the producer thread has delivered it, 
 * and the producer thread must not attempt to deliver new data if the consumer hasn't retrieved the old data.
 * 
 *  
 * @author hanjia
 *
 */
public class Drop {
    // Message sent from producer to consumer.
    private String message;
    
    // True if consumer should wait for producer to send message,
    // false if producer should wait for consumer to retrieve message.
    private boolean empty = true;

    public synchronized String take() {
        // Wait until message is available.
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
            	
            }
        }
        // Toggle status.
        empty = true;
        // Notify producer that status has changed.
        notifyAll();
        return message;
    }

    public synchronized void put(String message) {
        // Wait until message has been retrieved.
        while (!empty) {
            try { 
                wait();
            } catch (InterruptedException e) {
            	
            }
        }
        // Toggle status.
        empty = false;
        // Store message.
        this.message = message;
        // Notify consumer that status has changed.
        notifyAll();
    }
}
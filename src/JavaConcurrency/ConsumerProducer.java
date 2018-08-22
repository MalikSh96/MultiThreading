package JavaConcurrency;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author malik
 */
/*
Page 14

The main() method starts five consumer and one producer thread and then waits for them to finish. The producer thread then
inserts a new value into the queue and afterwards notifies all waiting threads that something has happened. The consumer threads
acquire the queue lock and then fall asleep in order to be woken up later on when the queue is filled again. When the producer
thread has finished its work, it notifies all consumer threads to wake up. If we won’t do the last step, the consumer threads would
wait forever for the next notification, as we haven’t specified any timeout for the waiting. Instead we could have used the method
wait(long timeout) to be woken up at least after some amount of time has passed by.
*/
public class ConsumerProducer 
{
    private static final Queue queue = new ConcurrentLinkedQueue();
    private static final long startMillis = System.currentTimeMillis();
    
    public static void main(String[] args) throws InterruptedException 
    {
        //Thread array of length 5
        Thread[] consumerThreads = new Thread[5];
        
        for(int i = 0; i < consumerThreads.length; i++) 
        {
            //Create new instance of consumre
            consumerThreads[i] = new Thread(new Consumer(), "consumer-" + i);
            consumerThreads[i].start();
        }
        
        Thread producerThread = new Thread(new Producer(), "producer");
        producerThread.start();
        for(int i = 0; i < consumerThreads.length; i++) 
        {
            consumerThreads[i].join();
        }
        producerThread.join();
    }
    
    //Inner classes
    static class Consumer implements Runnable 
    {
        @Override
        public void run() 
        {
            while(System.currentTimeMillis() < (startMillis + 10000)) 
            {
                synchronized(queue) 
                {
                    try 
                    {
                        /*
                        Causes the current thread to wait until another thread invokes the Object.notify() 
                        method or the Object.notifyAll() method for this object
                        */
                        queue.wait(); 
                    } catch (InterruptedException e) 
                    {
                        e.printStackTrace();
                    }
                }
            
                if(!queue.isEmpty()) 
                {
                    /*
                    Retrieves and removes the head of this queue, or returns null if this queue is empty
                    */
                    Integer integer = (Integer) queue.poll(); 
                    System.out.println("[ " + Thread.currentThread().getName() + " ]: " + integer);
                }
            }
        }
    }
    
    public static class Producer implements Runnable 
    {
        @Override
        public void run() 
        {
            int i = 0;
            while(System.currentTimeMillis() < (startMillis + 10000)) 
            {
                queue.add(i++);
                synchronized (queue) 
                {
                    queue.notify(); //<-- Wakes up a single thread that is waiting on this object's monitor.
                } 
                try 
                {
                    Thread.sleep(100);
                } catch (InterruptedException e) 
                {
                    e.printStackTrace();
                }
            }
            synchronized (queue) 
            {
                queue.notifyAll(); //<-- Wakes up all threads that are waiting on this object's monitor
            }
        }
    }
}
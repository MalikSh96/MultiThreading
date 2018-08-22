package multithreadingOnline;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author malik
 */
/*
http://tutorials.jenkov.com/java-util-concurrent/blockingqueue.html

^Link above gives the info you need
*/
public class BlockingQueueExample 
{
    public static void main(String[] args) throws InterruptedException 
    {
         BlockingQueue queue = new ArrayBlockingQueue(1024);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

        Thread.sleep(4000);
    }
    
    static class Producer implements Runnable
    {
        protected BlockingQueue queue = null;

        public Producer(BlockingQueue queue) 
        {
            this.queue = queue;
        }
        
        @Override
        public void run() 
        {
            try 
            {
                queue.put("1");
                Thread.sleep(1000);
                queue.put("2");
                Thread.sleep(1000);
                queue.put("3");
            } catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }
    }
    
    static class Consumer implements Runnable 
    {
        protected BlockingQueue queue = null;

        public Consumer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() 
        {
            try 
            {
                /*
                If the .take takes indefinite amount of time, use .poll
                Using .poll lets you specify when to continue according to the time YOU give
                So if you set it to 10 seconds, then after 10 seconds the program continues
                */
                System.out.println(queue.take()); 
                System.out.println(queue.take());
                System.out.println(queue.take());
            } catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }
    }
}

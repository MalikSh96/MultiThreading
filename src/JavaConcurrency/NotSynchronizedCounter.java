package JavaConcurrency;

/**
 *
 * @author malik
 */
/*
PDF Java Concurrency: 1.5
The exact sequence in which all running threads are executed depends next to the thread
configuration like priority also on the available CPU resources and the way the scheduler chooses the next thread to execute.
Although the behavior of the scheduler is completely deterministic, it is hard to predict which threads execute in which moment
at a given point in time. This makes access to shared resources critical as it is hard to predict which thread will be the first thread
that tries to access it. And often access to shared resources is exclusive, which means only one thread at a given point in time
should access this resource without any other thread interfering this access.
A simple example for concurrent access of an exclusive resource would be a static variable that is incremented by more than one
thread:
The solution for problems like this is the synchronized key word in Java. With synchronized you can create blocks of statements
which can only be accessed by a thread, which gets the lock on the synchronized resource. Let’s change the run() method from
the last example and introduce a synchronized block for the whole class:

OBS, SYNC NOT WORKING!
*/
public class NotSynchronizedCounter implements Runnable 
{
    private static int counter = 0;
    
    @Override
    public void run() 
    {
        while(counter < 10) 
        {
            System.out.println("[ " + Thread.currentThread().getName() + " ] before: " + counter);
            counter++;
            System.out.println("[ " + Thread.currentThread().getName() + " ] after: " + counter);
        }
    }
    
    public static void main(String[] args) throws InterruptedException 
    {
        Thread[] threads = new Thread[5];
        for(int i = 0; i < threads.length; i++) 
        {
            threads[i] = new Thread(new NotSynchronizedCounter(), "thread - " + i);
            threads[i].start();
        }
        for(int i = 0; i < threads.length; i++) 
        {
            threads[i].join();
        }
    }
}

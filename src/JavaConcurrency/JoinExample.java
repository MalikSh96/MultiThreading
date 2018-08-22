package JavaConcurrency;

import java.util.Random;

/**
 *
 * @author malik
 */
/*
PDF Java Concurrency: 1.4
Another important feature of threads that you will have to use from time to time is the ability 
of a thread to wait for the termination of another thread.
Let’s assume you have to implement some kind of number crunching operation that can be divided into several parallel running
threads. The main thread that starts the so called worker threads has to wait until all its child threads have terminated. The
following code shows how this can be achieved:
Within our main method we create an array of five Threads, which are all started one after the other. Once we have started
them, we wait in the main Thread for their termination. The threads itself simulate some number crunching by computing
one random number after the other. Once they are finished, they print out "finished". Finally the main thread acknowledges the
termination of all of its child threads:
You will observe that the sequence of "finished" messages varies from execution to execution. If you execute the program more
than once, you may see that the thread which finishes first is not always the same. But the last statement is always the main thread
that waits for its children.

OBS, THE LAST STATEMENT IS NOT THE MAIN THREAD, IT DIFFERS, CHECK IT!!!
*/
public class JoinExample implements Runnable
{
    private Random rand = new Random(System.currentTimeMillis());
    
    @Override
    public void run() 
    {    
        for(int i = 0; i < 100000000; i++) 
        {
            rand.nextInt();
        }
        System.out.println("[ " + Thread.currentThread().getName() + " ] finished.");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[5];
        for(int i = 0; i < threads.length; i++) 
        {
            threads[i] = new Thread(new JoinExample(), "joinThread - " + i);
            threads[i].start();
        }
        for(int i = 0; i < threads.length; i++) 
        {
            threads[i].join();
        }
        System.out.println("[ " +Thread.currentThread().getName() + " ] All threads have finished.");
    }
}

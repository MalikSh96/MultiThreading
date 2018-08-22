package JavaConcurrency;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author malik
 */
/*
PDF Java Concurrency: 1.3
In the code example below you may have noticed the InterruptedException that sleep() may throw. Interrupts are a
very basic feature for thread interaction that can be understood as a simple interrupt message that one thread sends to another
thread. The receiving thread can explicitly ask if it has been interrupted by calling the method Thread.interrupted() or it
is implicitly interrupted while spending his time within a method like sleep() that throws an exception in case of an interrupt.
Within the main method we start a new thread first, which would sleep for a very long time (about 290.000 years) if it would not
be interrupted. To get the program finished before this time has passed by, myThread is interrupted by calling interrupt()
on its instance variable in the main method. This causes an InterruptedException within the call of sleep() and is
printed on the console as "Interrupted by exception!". Having logged the exception the thread does some busy waiting until the
interrupted flag on the thread is set. This again is set from the main thread by calling interrupt() on the thread’s instance
variable.
*/
public class InterruptExample implements Runnable
{
    @Override
    public void run() 
    {
        try 
        {
            Thread.sleep(Long.MAX_VALUE);
        } 
        catch (InterruptedException ex) 
        {
            System.out.println("[ " + Thread.currentThread().getName() + " ]" 
                    + " interrupted by exception!");
        }
        while(!Thread.interrupted())
        {
            //do nothing here 
        }
        System.out.println("[ " + Thread.currentThread().getName() + " ]" 
                    + " interrupted for the second time.");
    }
    
    public static void main(String[] args) throws InterruptedException 
    {
        Thread myThread = new Thread(new InterruptExample(), "myThread");
        myThread.start();
        
        System.out.println("[ " + Thread.currentThread().getName()+ " ] Sleeping in main thread for 5s...");
        Thread.sleep(5000);
        
        System.out.println("[ " + Thread.currentThread().getName()+" ] Interrupting myThread");
        myThread.interrupt();
        
        System.out.println("[ " + Thread.currentThread().getName()+ "] Sleeping in main thread for 5s...");
        Thread.sleep(5000);
        
        System.out.println("[ " + Thread.currentThread().getName()+" ] Interrupting myThread");
        myThread.interrupt();
    }
}

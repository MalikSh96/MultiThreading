package JavaConcurrency;

import java.util.Random;

/**
 *
 * @author malik
 */
/*
From page 11

As can be seen from the code below, two threads are started and try to lock the two static resources
But for a deadlock we need a
different sequence for both threads, hence we utilize the Random instance to choose what resource the thread wants to lock first.
If the boolean variable b is true, the resource1 is locked first and the threads then tries to get the lock for resource 2. If b is false,
the thread locks resource2 first and then tries to lock resource1. This program does not have to run long until we reach the first
deadlock, i.e. the program hangs forever if we would not terminate it:
In this execution thread-1 holds the lock for resource2 and waits for the lock on resource1, whereas thread-2 holds the lock for
resource1 and waits for resource2.
If we would set the boolean variable b in the example code above to true, we would not experience any deadlock because the
sequence in which thread-1 and thread-2 are requesting the locks is always the same. Hence one of both threads gets the lock
first and then requests the second lock, which is still available because the other threads wait for the first lock.
*/
public class Deadlock implements Runnable 
{   
    private static final Object resource1 = new Object();
    private static final Object resource2 = new Object();
    private final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) 
    {
        Thread myThread1 = new Thread(new Deadlock(), "thread-1");
        Thread myThread2 = new Thread(new Deadlock(), "thread-2");
        myThread1.start();
        myThread2.start();
    }
    
    @Override
    public void run() 
    {
        for(int i = 0; i < 10; i++)
        {
            boolean b = random.nextBoolean();
            if(b)
            {
                System.out.println("[ " + Thread.currentThread().getName() + 
                        " ] Trying to lock resource 1.");
                synchronized(resource1) 
                {
                    System.out.println("[ " + Thread.currentThread().getName() 
                            + " ] Locked resource 1.");
                    System.out.println("[ " + Thread.currentThread().getName() 
                            + " ] Trying to lock resource 2.");
                    
                    synchronized(resource2)
                    {
                        System.out.println("[ " + Thread.currentThread().getName() 
                                + " ] Locked resource 2.");
                    }
                }
            }
            else
            {
                System.out.println("[ " + Thread.currentThread().getName() 
                        + " ] Trying to lock resource 2.");
                synchronized(resource2) 
                {
                    System.out.println("[ " + Thread.currentThread().getName() 
                            + " ] Locked resource 2.");
                    System.out.println("[ " + Thread.currentThread().getName() 
                            + "] Trying to lock resource 1.");
                    
                    synchronized(resource1) 
                    {
                        System.out.println("[ " + Thread.currentThread().getName() + " ] Locked resource 1.");
                    }
                }
            }
        }
    }
}

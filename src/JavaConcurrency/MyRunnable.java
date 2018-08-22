package JavaConcurrency;

/**
 *
 * @author malik
 */
/*
PDF Java Concurrency: 1.2
*/
public class MyRunnable implements Runnable 
{
    @Override
    public void run() 
    {
        System.out.println("Executing thread " + Thread.currentThread().getName());
    }
    
    public static void main(String[] args) 
    {
        Thread myThread = new Thread(new MyRunnable(), "myRunnable");
        myThread.start();
    }
}

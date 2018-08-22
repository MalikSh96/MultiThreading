/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadingOnline;

/**
 *
 * @author malik
 */
/*
AS A FIRRST STEP IN MULTITHREADING, YOU HAVE TO IMPLEMENT THE INTERFACE RUNNABLE

ANOTHER WAY FOR MULTITHREADING IS BY EXTENDING THE THREAD CLASS, AND FROM THERE THE STEPS
ARE THE SAME AS THE ONE WITH THE IMPLEMENTING

LINKS:
https://www.tutorialspoint.com/java/java_multithreading.htm
*/
public class Multithreading implements Runnable 
{    
    private Thread t;
    private String threadName;
    
    /*
    STEP 1: 
    You need to implement a run() method provided by a Runnable interface.
    This method provides an entry point for the thread and you will put your complete business logic inside this method. 
    Following is a simple syntax of the run() method
    
    STEP 2:
    As a second step, you will instantiate a Thread object using the following constructor:
    Thread(Runnable threadObj, String threadName);
    Where, threadObj is an instance of a class that implements the Runnable interface and 
    threadName is the name given to the new thread.
    
    STEP 3:
    Once a Thread object is created, you can start it by calling start() method, 
    which executes a call to run( ) method. Following is a simple syntax of start() method
    */
    
    Multithreading(String name)
    {
        threadName = name;
        System.out.println("Creating " + threadName);
    }
    
    @Override
    public void run() {
        System.out.println("Running " + threadName);
        
        try{
            for(int i = 4; i > 0; i--)
            {
                System.out.println("Thread " + threadName + ", " + i);
                //Let the thread sleep for a while
                Thread.sleep(30);
            }
        }
        catch(InterruptedException e)
        {
            System.out.println("Thread " + threadName + " interrupted");
        }
        System.out.println("Thread " + threadName + " exiting");
    }
    
    public void start()
    {
        System.out.println("Starting " + threadName);
        if(t == null)
        {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}

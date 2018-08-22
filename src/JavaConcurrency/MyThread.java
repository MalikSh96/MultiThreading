package JavaConcurrency;

/**
 *
 * @author malik
 */
/*
PDF Java Concurrency: 1.2
As can be seen below, the class MyThread extends the Thread class and overrides the method run(). The method run()
gets executed once the virtual machine starts our Thread. As the virtual machine has to do some work in order to setup the
execution environment for the thread, we cannot call this method directly to start the thread. Instead we call the method start()
on an instance of the class MyThread. As this class inherits the method stop() from its superclass, the code behind this
method tells the JVM to allocate all necessary resources for the thread and to start it. When we run the code above, we see the
output "Executing thread myThread". In contrast to our introduction example, the code within the method run() is not executed
within the "main" thread but rather in our own thread called "myThread".
*/
public class MyThread extends Thread
{
    public MyThread(String name) 
    {
        super(name);
    }
    
    @Override
    public void run()
    {
        System.out.println("Executing thread " + Thread.currentThread().getName());
    }
    
    public static void main(String[] args) 
    {
        MyThread myThread = new MyThread("myThread");
        myThread.start();
    }
}

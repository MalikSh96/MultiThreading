package JavaConcurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author malik
 */
/*
Page 22

The ThreadPoolExecutor starts with 5 core threads and allows the pool to grow up to 10 threads at the maximum.
For demonstration purposes we
allow an unused thread only to idle for about 1 second. The queue implementation here is a LinkedBlockingQueue with a
capacity of 10 Runnable instances. We also implement a simple ThreadFactory in order to track the thread creation. The
same is true for the RejectedExecutionHandler.
The loop in the main() method now issues 100 Runnable instance to the pool within a short amount of time.
But it also shows that all tasks with taskId greater than 19 are forwarded to the RejectedExecutionHandler. This is due
to the fact that our Runnable implementation sleeps for 5 seconds. After the first 10 threads have been started the queue can
only hold another 10 Runnable instances. All further instances then have to be rejected.
Finally the shutdown() method lets the ThreadPoolExecutor reject all further tasks and waits until the already issued
tasks have been executed. You can replace the call of shutdown() with a call of shutdownNow(). The latter tries to
interrupt all running threads and shuts down the thread pool without waiting for all threads to finish.
*/
public class ThreadPoolExecutorExample implements Runnable 
{
    private static AtomicInteger counter = new AtomicInteger();
    private int taskId;
    
    public ThreadPoolExecutorExample(int taskId) {
        this.taskId = taskId;
    }
    
    @Override
    public void run() 
    {
        try 
        {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public int getTaskId() {
        return taskId;
    }
    
    public static void main(String[] args) 
    {
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(10);
        //An object that creates new threads on demand. --- see javadoc
        ThreadFactory threadFactory = new ThreadFactory()
        {
            @Override
            public Thread newThread(Runnable r) 
            {
                int currentCount = counter.getAndIncrement();
                System.out.println("Creating new thread: " + currentCount);
                return new Thread(r, "mythread" + currentCount);
            }
        };
        
        //A handler for tasks that cannot be executed by a ThreadPoolExecutor
        RejectedExecutionHandler rejectedHandler = new RejectedExecutionHandler() 
        {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) 
            {
                if (r instanceof ThreadPoolExecutorExample) 
                {
                    ThreadPoolExecutorExample example = (ThreadPoolExecutorExample) r;
                    System.out.println("Rejecting task with id " +  -
                    example.getTaskId());
                }
            }
        };
        
        /*
        An ExecutorService that executes each submitted task using 
        one of possibly several pooled threads, normally configured using Executors factory methods.
        */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 1, TimeUnit.SECONDS, queue, threadFactory, rejectedHandler);
        for(int i = 0; i < 100; i++) 
        {
            //Executes the given task sometime in the future. The task may execute in a new thread or in an existing pooled thread.
            executor.execute(new ThreadPoolExecutorExample(i));
        }
        
        //Initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted.
        executor.shutdown();
    }
}
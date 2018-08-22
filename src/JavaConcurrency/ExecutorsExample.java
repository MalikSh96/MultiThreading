package JavaConcurrency;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author malik
 */
/*
Page 24

The creation of the ExecutorService is a one-liner. To execute some tasks we just need a for-loop that creates a few new
instances of ExecutorsExample and stores the returned Future in an array. After we have submitted the tasks to the service, we
just wait for the result. The method get() of Future is blocking, i.e. the current threads sleeps until the result is available.
An overridden version of this method takes a timeout specification in order to let the waiting thread proceed if the task does not
finish within the defined time period.
*/
public class ExecutorsExample implements Callable<Integer>
{
    private static Random random = new Random(System.currentTimeMillis());
    
    @Override
    public Integer call() throws Exception 
    {
        Thread.sleep(1000);
        return random.nextInt(100);
    }
    
    public static void main(String[] args) throws InterruptedException, ExecutionException 
    {
        //Creates a thread pool of the size 5
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        /*
        A Future represents the result of an asynchronous computation. Methods are provided to 
        check if the computation is complete, to wait for its completion, and to retrieve the 
        result of the computation. The result can only be retrieved using method get when the computation has completed,
        */
        Future<Integer>[] futures = new Future[5];
        for(int i = 0; i < futures.length; i++) 
        {
            //Submits a value-returning task for execution and returns a Future representing the pending results of the task.
            futures[i] = executorService.submit(new ExecutorsExample());
        }
        for(int i = 0; i < futures.length; i++) 
        {
            Integer retVal = futures[i].get();
            System.out.println("Retrieved value: " + retVal);
        }
        executorService.shutdown();
    }
}

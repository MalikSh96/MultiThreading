package ExercisesDayOne;

/**
 *
 * @author malik
 */
/*
Write a function that takes an int n as parameter, and creates n threads, running at the same time. Use a for loop to create the threads.
Each thread should print the numbers from 1 to 100.
Have the threads print an identifier with each number, so you can see 
which thread prints what (use the iteration variable from the outer loop where the thread is created).
a) describe the output. Is it what you expected?
b) If the threads do not interleave, can you make them, by making the threads sleep for a short period of time? (think milliseconds).
*/
public class ExTwo implements Runnable
{
    private int amount;
    public ExTwo(int n) 
    {
        this.amount = n;
    }

    @Override
    public void run() 
    {
        int number = 100;
        for(int i = 0; i < number; i++)
        {
            System.out.println("Thread name: " + Thread.currentThread().getName() + " --- number: " + i);
        }
    }
    
    public static void main(String[] args) 
    {
        int n = 3;
        Thread[] threads = new Thread[n];
        for(int i = 0; i < threads.length; i++)
        {
            threads[i] = new Thread(new ExTwo(n));
            threads[i].start();
        }
    }
}

package ExercisesDayOne;

/**
 *
 * @author malik
 */
/*
The method next() in the class Even should always return an even number (see code snippet below). 
You will implement a program that demonstrates that this is not always true in a multithreaded program.
Create at least two threads, which both should call the next() method on the same 
Even object and test if the return value is even. Experiment with the number of calls each thread makes to next().

a) Are you able to provoke the expected error on your machine?
b) How often does the problem occur?
c) Use the synchronization techniques you’ve learned today, to make next() method atomic with respect to itself.
d) Argue that your solution is correct (argue, don’t prove)
 */
public class ExThree implements Runnable
{
    private int n = 0;
    
    public int next() throws InterruptedException
    {
        n++;
        Thread.sleep(5); //<-- provokes the error 
        n++;
        return n;
    }
    

    @Override
    public void run() 
    {
        for(int i = 0; i < n; i++)
        {
            System.out.println("Run method: " + Thread.currentThread().getName() 
                    + " --- " + i);
        }
    }
    
    public static void main(String[] args) throws InterruptedException 
    {
        ExThree eThree = new ExThree();
        Thread t1 = new Thread(eThree);
        Thread t2 = new Thread(eThree);
        
        t1.start();
        eThree.next();
        t2.start();
        eThree.next();
    }
}

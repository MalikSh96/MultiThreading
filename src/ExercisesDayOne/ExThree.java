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
public class ExThree
{   
    public static void main(String[] args) throws InterruptedException 
    {
        Even even = new Even();
        Runnable r = new Runnable() 
        {
            @Override
            public void run()
            {
                synchronized (even)
                {
                    for(int i = 0; i < 10000; i++)
                    {
                        if(even.next() % 2 != 0)
                            System.out.println("Error");
                        else
                            System.out.println("No errors");
                    }
                }
            }
        };
        
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        
        t1.start();
        t2.start();
    }
    
    static class Even 
    {
        private int n = 0;
        public int next()
        {
            n++;
            n++;    
            return n;
        }
    }
}

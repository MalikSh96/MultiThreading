package ExercisesDayOne;

/**
 *
 * @author malik
 */
public class ExOneThreadOne implements Runnable
{
    private long sumOfNum = 1000000000;
    
    @Override
    public void run() 
    {
        long i = 0;
        while(i <= sumOfNum)
        {
            sumOfNum += i;
        }
        System.out.println("Total " + sumOfNum + " --- " + Thread.currentThread().getName());
    }

}

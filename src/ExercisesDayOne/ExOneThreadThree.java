/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExercisesDayOne;

/**
 *
 * @author malik
 */
public class ExOneThreadThree implements Runnable 
{
    private boolean shouldRun = true;
    @Override
    public void run() 
    {
        int num = 10;
        while(shouldRun)
        {
            System.out.println("Counting from 10 " + num + " --- " + Thread.currentThread().getName());
            num++;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                System.out.println("Interrupted thread 3 " + Thread.currentThread().getName());
            }
        }
    }
    
    public void stop()
    {
        shouldRun = false;
    }
}

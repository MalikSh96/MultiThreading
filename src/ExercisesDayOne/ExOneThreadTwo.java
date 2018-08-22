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
public class ExOneThreadTwo implements Runnable
{

    @Override
    public void run() 
    {
        for(int i = 0; i <= 5; i++)
        {
            System.out.println("Counting " + i + " --- " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                System.out.println("Interrupted thread 2 " + Thread.currentThread().getName());
            }
        }
    } 
}

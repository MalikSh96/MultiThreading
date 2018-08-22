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
public class Main 
{
    public static void main(String[] args) {
        Multithreading M1 = new Multithreading("Thread-1");
        M1.start();
        
        Multithreading M2 = new Multithreading("Thread-2");
        M2.start();
    }
    
}

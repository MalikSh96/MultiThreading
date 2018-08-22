package ExercisesDayOne;

/**
 *
 * @author malik
 */
/*
Exercise 1 (create, start and end threads)
Create a program that starts 3 different parallel threads.

thread1: Compute and print the sum of all numbers from 1 to 1 billion
thread2: Print the numbers from 1 to 5. Pause for 2 seconds between each print.
thread3: Print all numbers from 10 and up. Pause for 3 seconds between each print.

The program should stop thread3 after 10 seconds.

Hint: 	For the sum in thread1, use the a long data type, the result is about 5e+17
Hint2: 	Let the main thread sleep for 10 seconds after starting thread3. The Thread class has a    static method “sleep(n)”, which takes an integer n, and makes the calling thread sleep for n milliseconds.
Hint3: 	Use a Boolean value in the loop in thread3 to terminate task3 (let the main thread 
change the value of the boolean value).
*/

public class ExOneMain 
{
    public static void main(String[] args) throws InterruptedException 
    {
        Thread t1 = new Thread(new ExOneThreadOne()); //Compute and print the sum of all numbers from 1 to 1 billion
        Thread t2 = new Thread(new ExOneThreadTwo()); //Print the numbers from 1 to 5. Pause for 2 seconds between each print.
        ExOneThreadThree eoThree = new ExOneThreadThree();
        Thread t3 = new Thread(eoThree); //Print all numbers from 10 and up. Pause for 3 seconds between each print
        
        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(10000);
        eoThree.stop();
    }
}

package FlowOneClass;

/**
 *
 * @author malik
 */
/*
This is a "small" example of thread, created together in class, has different "methods" and purposes, 
altså kan lave forskellige ting, derfor udkommentering
*/
public class dayOne 
{
    public static void main(String[] args) throws InterruptedException 
    {
        //If counter shared multiple threads
        int counter = 0;
        
        Runnable r1 = new Runnable() 
        {
            @Override
            public void run() 
            {
                System.out.println("From inside the thread");
            }
        };
        myTask mt = new myTask(counter);
        Thread t1 = new Thread(mt, "Thread1"); 
//        Thread t1 = new Thread(r1); //throwing the runnable into the thread
//        Thread t2 = new Thread(()->{System.out.println("From inside the SECOND thread");}); // <-- lambda version
//        Thread t2 = new Thread(mt, "Thread2");
        
        t1.start();
//        t2.start();
        
        Thread.sleep(1000);
        mt.stop();
        
        t1.join(); //It blocks the main thread for going forward before the other threads are done
//        t2.join();
        
        /*
        It does not differ which way you join, altså rækkefølgen er ikke vigti
        If it is t1.join then t2.join doesn't matter
        */
        
        System.out.println("ENDING the program...");
    }
    
    //Inner class
    static class myTask implements Runnable
    {
        private boolean shouldRun = true;
        private int counter;
        
        public myTask(int counter)
        {
            this.counter = counter;
        }
        
        public void stop()
        {
            shouldRun = false;
        }
        
        @Override
        public void run() 
        {
//            for(int i = 0; i < 100; i++)
//            {
//                counter++;
//                System.out.println("From myTask, counter: " + Thread.currentThread().getName() 
//                        + " --- " + counter);
//            }
            while(shouldRun)
            {
                System.out.println("working...");
            }
        } 
    }
}

package JavaConcurrency;

/**
 *
 * @author malik
 */
/*
Page 22
*/
public class MyExecutor implements Executor {

    @Override
    public void execute(Runnable r) 
    {
        (new Thread(r)).start();
    }   
}

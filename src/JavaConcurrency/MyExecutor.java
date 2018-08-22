package JavaConcurrency;

/**
 *
 * @author malik
 */
/*
Page 22

The java.util.concurrent package defines a set of interfaces whose implementations execute tasks. The simplest one of
these is the Executor interface:

Hence an Executor implementation takes the given Runnable instance and executes it. The interface makes no assumptions
about the way of the execution, the javadoc only states "Executes the given command at some time in the future.". A simple
implementation could therefore be:

Executor.java is also a part of this
*/
public class MyExecutor implements Executor {

    @Override
    public void execute(Runnable r) 
    {
        (new Thread(r)).start();
    }   
}

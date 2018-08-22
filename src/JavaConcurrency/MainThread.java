package JavaConcurrency;

import java.lang.Thread.State;

/**
 *
 * @author malik
 */
/*
PDF Java Concurrency: 1.1
In Java, processes correspond to a running Java Virtual Machine (JVM), whereas threads live within the same JVM and can be
created and stopped by the Java application dynamically during runtime. Each program has at least one thread: the main thread.
This main thread is created during the start of each Java application and it is the one that calls the main() method of your
program. From this point on, the Java application can create new Threads and work with them.
This is demonstrated in the following source code. Access to the current Thread is provided by the static method currentT
hread() of the JDK class java.lang.Thread:

As you can see from the source code of this simple application, we access the current Thread directly in the main() method
and print out some of the information provided about it:
*/
public class MainThread 
{
    public static void main(String[] args) 
    {
        long id = Thread.currentThread().getId();
        String name = Thread.currentThread().getName();
        int priority = Thread.currentThread().getPriority();
        State state = Thread.currentThread().getState();
        String ThreadGroupName = Thread.currentThread().getThreadGroup().getName();
        
        System.out.println("Id " + id + ", " + "name " + name + ", "
        + "priority " + priority + ", " + "state " + state + ", " 
                + "group name" + ThreadGroupName);    
    }
}
/*
• NEW: A thread that has not yet started is in this state.
• RUNNABLE: A thread executing in the Java virtual machine is in this state.
• BLOCKED: A thread that is blocked waiting for a monitor lock is in this state.
• WAITING: A thread that is waiting indefinitely for another thread to perform a particular action is in this state.
• TIMED_WAITING: A thread that is waiting for another thread to perform an action for up to a specified waiting time is in
this state.
• TERMINATED: A thread that has exited is in this state.
*/

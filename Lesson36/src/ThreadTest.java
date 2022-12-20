import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadTest {
    public static int counter = 0;
    public static Object locker = new Object();

    public static void main(String[] args) {
        /*int limit = 100000;
        IncThread t6 = new IncThread(limit);
        DecThread t7 = new DecThread(limit);
        t6.start();
        t7.start();
        try {
            t6.join();
            t7.join();
        }catch (InterruptedException e){
            e.printStackTrace();
            Logger.getLogger(ThreadTest.class.getName()).log(Level.SEVERE, null, e);
        }
        System.out.println("counter="+counter);*/

        MyResourse myResourse = new MyResourse();
        Thread t1 = new MyThread(myResourse);
        Thread t2 = new MyThread(myResourse);
        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(myResourse.getCounter());
    }
}
class IncThread extends Thread{
    int limit;
    public IncThread(int limit){
        this.limit = limit;
    }

    @Override
    public void run() {
        for (int i = 0; i < limit; i++){
            synchronized (ThreadTest.locker) {
                ThreadTest.counter++;
            }
        }
    }
}
class DecThread extends Thread{
    int limit;
    public DecThread(int limit){
        this.limit = limit;
    }

    @Override
    public void run() {
        for (int i = 0; i < limit; i++){
            synchronized (ThreadTest.locker) {
                ThreadTest.counter--;
            }
        }
    }
}

class MyResourse {
    long counter = 0;
    public synchronized void add(long value){
        this.counter += value;
    }
    long getCounter(){
        return this.counter;
    }
}

class MyThread extends Thread{
    protected MyResourse obj_counter = null;
    public MyThread (MyResourse counter){
        this.obj_counter = counter;
    }
    @Override
    public void run(){
        for (int i = 0; i <= 10000000; i++){
            obj_counter.add(i);
        }
    }
}
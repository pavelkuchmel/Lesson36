import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int v = (new Random()).nextInt(10);
        MyThread3 r4 = new MyThread3(v);
        Thread t4 = new Thread(r4);
        t4.setDaemon(true);
        v = (new Random()).nextInt(10);
        MyThread3 r5 = new MyThread3(v);
        Thread t5 = new Thread(r5);
        t5.setDaemon(true);

        t4.start();
        t5.start();

        t5.interrupt();
        try {
            t4.join();
            t5.join();
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Testsynchyro {
    public static String line = "";

    public static void main(String[] args) {

        Random r = new Random();
        MySemaphore ms = new MySemaphore(r.nextInt(2000)+1000);
        for(int i=0;i<20; i++){
            Thread t=new Thread(ms);
            t.start();
        }

        /*File file = new File("lines.txt");
        File file1 = new File("lines_out.txt");
        try {
            if (file.createNewFile()) {
                System.out.println("файл lines создан");
            }
            if (file1.createNewFile()) {
                System.out.println("файл linesout создан");
            }
        }catch (IOException e){
            Logger.getLogger(MyReader.class.getName()).log(Level.SEVERE,null, e);
        }*/

        /*Object locker = new Object();
        MyReader myReader = new MyReader("lines.txt", locker);
        MyWriter myWriter = new MyWriter("lines_out.txt", locker);

        Thread t1 = new Thread(myReader);
        Thread t2 = new Thread(myWriter);

        t1.setDaemon(true);
        t2.setDaemon(true);

        t2.start();

        try{
            Thread.sleep(500);
        }catch (InterruptedException e){
            Logger.getLogger(MyReader.class.getName()).log(Level.SEVERE,null, e);
        }

        t1.start();

        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            Logger.getLogger(MyReader.class.getName()).log(Level.SEVERE,null, e);
        }*/
    }
}

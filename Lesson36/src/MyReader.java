import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyReader implements Runnable{
    FileReader fr;
    Object locker;

    public MyReader(String filePath, Object locker){
        this.locker = locker;
        try{
            fr = new FileReader(filePath);
        }catch (FileNotFoundException e){
            Logger.getLogger(MyReader.class.getName()).log(Level.SEVERE,null, e);
        }
    }

    @Override
    public void run() {
        int lineCounter = 1;
        String str;
        BufferedReader br = new BufferedReader(fr);
        try {
            while ((str = br.readLine()) != null){
                System.out.println("Reader: " + str);
                if (lineCounter++ % 2 != 0){
                    synchronized (locker){
                        Testsynchyro.line = str;
                        locker.notify();
                        locker.wait();
                    }
                }
            }
            synchronized (locker){
                Testsynchyro.line = "exit";
                locker.notify();
                locker.wait();
            }
        }catch (IOException e){
            Logger.getLogger(MyReader.class.getName()).log(Level.SEVERE,null, e);
        }catch (InterruptedException e){
            Logger.getLogger(MyReader.class.getName()).log(Level.SEVERE,null, e);
        }
        finally {
            try {
                fr.close();
            } catch (IOException e) {
                Logger.getLogger(MyReader.class.getName()).log(Level.SEVERE,null, e);
            }
        }
    }
}

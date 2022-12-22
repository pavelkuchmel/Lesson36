import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyWriter implements Runnable{

    FileWriter fw;
    Object locker;

    public MyWriter(String filePath, Object locker){
        this.locker = locker;
        try{
            fw = new FileWriter(filePath, true);
        }
        catch (IOException e){
            Logger.getLogger(MyWriter.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void run() {
        synchronized (locker){
            while (!Testsynchyro.line.equals("exit")) {
                try{
                    locker.wait();
                    if (!Testsynchyro.line.equals("exit")){
                        fw.write(Testsynchyro.line + System.getProperty("line.separator"));
                    }
                    System.out.println("*** Written line: " + Testsynchyro.line);
                    locker.notify();
                }catch (IOException e){
                    Logger.getLogger(MyWriter.class.getName()).log(Level.SEVERE, null, e);
                }catch (InterruptedException e){
                    Logger.getLogger(MyWriter.class.getName()).log(Level.SEVERE, null, e);
                }

            }
        }
        try{
            fw.close();
        }catch (IOException e){
            Logger.getLogger(MyWriter.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

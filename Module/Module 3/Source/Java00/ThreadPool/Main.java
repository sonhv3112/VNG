import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.lang.Thread;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
         
        for (int i = 1; i <= 10; i++) {
            MyTask task = new MyTask(i, 2000);
            executorService.execute(task);
        }
            
        executorService.shutdown();
    }
}

class MyTask implements Runnable {
    private int id; 
    private int timeSleep; 

    MyTask(int id, int timeSleep) { 
        this.id = id; 
        this.timeSleep = timeSleep; 
    }

    @Override
    public void run() {
        
        System.out.println("Task " + id + " thực thi...");
 
        try {
            Thread.sleep(timeSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         
        System.out.println("Task " + id + " kết thúc");
    }
}
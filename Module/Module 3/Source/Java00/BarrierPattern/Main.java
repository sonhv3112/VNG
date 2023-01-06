import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {
 
    public static void main(String[] args) {
        int numThreads = 5;
        CyclicBarrier barrier = new CyclicBarrier(numThreads);
 
        for (int i = 0; i < numThreads; i++) {
            new Thread(new Task(barrier)).start();
        }
    }
 
    static class Task implements Runnable {
        private CyclicBarrier barrier;
 
        public Task(CyclicBarrier barrier) {
            this.barrier = barrier;
        }
 
        @Override
        public void run() {
            try {
                System.out.println("Thread " + Thread.currentThread().getId() + " is waiting on barrier");
                barrier.await();
                System.out.println("Thread " + Thread.currentThread().getId() + " has crossed the barrier");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class Main {
    private static final int THRESHOLD = 10;
 
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SimpleRecursiveAction action = new SimpleRecursiveAction(120);
        forkJoinPool.invoke(action);
    }
 
    private static class SimpleRecursiveAction extends RecursiveAction {
        private int simulatedWork;
 
        public SimpleRecursiveAction(int simulatedWork) {
            this.simulatedWork = simulatedWork;
        }
 
        @Override
        protected void compute() {
            if (simulatedWork > THRESHOLD) {
                System.out.println("Fork work " + simulatedWork);
                ForkJoinTask.invokeAll(createSubtasks());
            } else {
                System.out.println("Doing work directly, simulatedWork = " + simulatedWork);
            }
        }

        private List<SimpleRecursiveAction> createSubtasks() {
            List<SimpleRecursiveAction> subtasks = new ArrayList<>();
    
            subtasks.add(new SimpleRecursiveAction(this.simulatedWork / 2));
            subtasks.add(new SimpleRecursiveAction(this.simulatedWork - this.simulatedWork / 2));
    
            return subtasks;
        }
    
        private void processing(String work) {
            String result = work.toUpperCase();
            System.out.println("This result - (" + result + ") - was processed by " + Thread.currentThread().getName());
        }
    }
}

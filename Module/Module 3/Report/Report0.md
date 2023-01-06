# Module 3.0

Họ và tên: Hồ Văn Sơn \
Domain: sonhv2 

---------------------------------------

## 2.1 Basic 

- Log level. Có nhiều khái niệm liên quan tới log level, tuy nhiên trong đó có những khái niệm cơ bản như: 
    - DEBUG: Dùng để ghi các thông tin chi tiết và thử nghiệm.
    - INFO: Dùng để ghi các thông tin về hoạt động cơ bản của hệ thống.
    - WARN: Dùng để ghi các thông tin về các sự cố tiềm năng hoặc các vấn đề không mong muốn.
    - ERROR: Dùng để ghi các thông tin về các sự cố nghiêm trọng hoặc lỗi xảy ra trong hệ thống.
    - FATAL: Dùng để ghi các thông tin về các sự cố nghiêm trọng nhất, có thể dẫn đến việc hệ thống không hoạt động đúng nữa.

- Cấu hình log: 
```Java
<configuration>
  <appender name="FILE" class="ch.qos.logback.core.FileAppender">
    <file>${LOG_FILE}</file>
    <encoder>
      <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
    </encoder>
  </appender>

  <appender name="STDOUT" class="ch.qos.logback.core.Console.ConsoleAppender">
    <encoder>
      <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
    </encoder>
  </appender>

  <root level="debug">
    <appender-ref ref="FILE" />
    <appender-ref ref="STDOUT" />
  </root>

  <logger name="com.example.app" level="info" additivity="false">
    <appender-ref ref="FILE" />
  </logger>
</configuration>
```

- Trong đó: 
    - `<appender name="FILE" class="ch.qos.logback.core.FileAppender">` là thiết lập cho appender ghi log ra tập tin. Trong đó, name là tên của appender, class là class để ghi log ra tập tin, file là đường dẫn tới tập tin ghi log, `<encoder>` là thiết lập cho cách mà log sẽ được ghi ra tập tin (ví dụ như định dạng thời gian, log level, tên logger, v.v.).
    - `<appender name="STDOUT" class="ch.qos.logback.core.console.consoleAppender">` là thiết lập cho appender ghi log ra console. Các thiết lập tương tự như appender ghi log ra tập tin.
    - `<root level="debug">` là thiết lập cho log level mặc định. Trong đó, level là log level mặc định, các log có log level là debug trở lên sẽ được ghi ra các appender được thiết lập trong thẻ `<root>`.
    - `<logger name="com.example.app" level="info" additivity="false">` là thiết lập cho logger cụ thể. Trong đó, name là tên của logger, level là log level cho logger này, additivity là thiết lập cho việc logger này có ghi log ra các appender khác ngoài những appender được thiết lập trong thẻ `<logger>` hay không. Nếu đặt là false, thì logger này chỉ ghi log ra các appender được thiết lập trong thẻ `<logger>`, không ghi log ra các appender khác.

## 2.2 Threading 

- Thread-safe là khái niệm để chỉ việc một đoạn mã hoặc một đối tượng có thể được sử dụng bởi nhiều luồng (thread) cùng một lúc mà không gây ra sự nhầm lẫn hoặc xung đột giữa các luồng đó. Trong Java, một đoạn mã hoặc đối tượng được coi là thread-safe khi nó có thể được sử dụng bởi nhiều luồng mà không gây ra sự nhầm lẫn hoặc xung đột trong dữ liệu hoặc trạng thái của đối tượng đó.

- Threads and Executors
    - Trong Java, luồng (threads) là các đối tượng mà ta có thể tạo ra và chạy để thực hiện các tác vụ song song. Ta có thể tạo ra một luồng bằng cách kế thừa lớp Thread hoặc thực hiện lớp Runnable.
    - Executors là một lớp trong Java cung cấp các phương thức tiện ích để tạo ra và quản lý các luồng (threads). Nó cung cấp các phương thức để tạo ra các thread pool, scheduler và các task queue. Executors có thể giúp ta tạo ra và quản lý các luồng một cách dễ dàng hơn so với việc sử dụng lớp Thread trực tiếp.

- Trong Java, ta có thể sử dụng lớp Executor và ExecutorService để tạo ra và quản lý một thread pool. Lớp Executor cung cấp một cách để thực hiện các task bất đồng bộ (asynchronously) mà không cần phải quản lý luồng (threads) một cách trực tiếp. Lớp ExecutorService cung cấp các phương thức tiện ích để quản lý luồng, như tạm dừng hoặc hủy bỏ các luồng. Ví dụ về việc tạo ra một thread pool với số lượng luồng tĩnh bằng 5 và chạy 10 tác vụ.

    ```Java
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
    ```

- ForkJoin framework được cài đặt dựa trên ý tưởng thuật toán chia để trị, trong đó một ForkJoinPool sẽ chịu trách nhiệm quản lý và thực thi các nhánh ForkJoinTasks. Nguyên tắc thực hiện của framework này tương tự như tên của nó là Fork/Join, bao gồm 2 bước được thực hiện đệ quy là: Bước chia (Fork/Split) và bước gộp (Join/Merge). Cụ thể nó sẽ thực hiện việc chia nhỏ các task bằng cách đệ quy cho đến khi các task nhỏ đến mức đủ để thực hiện xử lý không đồng bộ. Sau đó, phần kết quả sẽ được gộp ở bước gộp. 

<p align="center">
    <image src="./assets/java-fork-and-join.png" 
        width=80%/> 
    </br>
    Mô hình mô tả cách thức ForkJoin hoạt động
</p>

- Demo ForkJoin thực hiện task giả lập có tải trọng là 120, khi task được chia nhỏ hơn hoặc bằng THRESHOLD là 10 thì sẽ được thực thi trực tiếp. 
    ```Java
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
    ```


- Các loại lock: 
    - ReetrantLock
        - ReentrantLock là một loại lock đồng bộ hóa mà cho phép một thread có thể khóa một đối tượng và sau đó khóa nó lại mà không bị chặn bởi các thread khác. ReentrantLock có rất nhiều tính năng khác nhau như chế độ khóa đồng bộ, khóa không đồng bộ và khóa không đồng bộ với thời gian chờ.
        - Use case: Khi cần đồng bộ hóa truy cập đến một tài nguyên chung nhưng không muốn sử dụng synchronized, hay khi cần các tính năng mở rộng của ReentrantLock như chế độ khóa không đồng bộ và khóa không đồng bộ với thời gian chờ.
    - ReadWriteLock
        - ReadWriteLock là một loại lock đồng bộ hóa cho phép nhiều thread đọc cùng một tài nguyên nhưng chỉ cho phép một thread viết vào tài nguyên đó tại một thời điểm. 
        - Use case: Khi ta có một tài nguyên chung mà nhiều thread cần truy cập để đọc nhưng chỉ có một số ít thread cần viết vào nó.
    - StampedLock
        - StampedLock là một loại lock đồng bộ hóa mới trong Java 8. Nó cho phép khóa một đối tượng bằng cách sử dụng một "stamp" (dấu vết). Nó có một số tính năng khác nhau như khóa đọc, khóa viết và khóa không đồng bộ.
        - Use case: Khi ta cần một lock đồng bộ hóa có hiệu suất tốt hơn so với ReentrantLock.
    - Semaphores 
        - Semaphores là một loại lock đồng bộ hóa cho phép đặt hạn chế số lượng thread có thể truy cập một tài nguyên chung. Một Semaphore có một số lượng tối đa các "permits" và mỗi lần một thread muốn truy cập vào tài nguyên, nó sẽ yêu cầu một "permit" và sẽ chờ đợi nếu không có sẵn.
        - Use case: Khi cần hạn chế số lượng thread có thể truy cập một tài nguyên chung cùng một lúc.
    - Spin lock với atomic variables:
        - Spin lock là một loại lock đồng bộ hóa cho phép một thread khóa một đối tượng và sau đó khóa nó lại mà không bị chặn bởi các thread khác. Nó được sử dụng khi một thread khóa một đối tượng trong một vòng lặp và không muốn chặn các thread khác trong khi đợi khóa được giải quyết. Spin lock sử dụng các biến atomic để đảm bảo rằng chỉ có một thread có thể khóa đối tượng đó tại một thời điểm.
        - Use case: Khi ta cần một lock đồng bộ hóa nhanh hơn so với các loại lock khác, hay muốn tránh việc chặn các thread khác khi đợi khóa được giải quyết.
        - So với các hình thức lock khác, spin lock có ưu điểm là nó rất nhanh vì nó không cần chặn các thread khác khi đợi khóa được giải quyết. Tuy nhiên, spin lock cũng có nhược điểm là nó có thể gây tải cao cho hệ thống vì nó luôn luôn lặp lại việc khóa và giải quyết khóa trong khi đợi.

- Distributed locks là các loại lock đồng bộ hóa mà được sử dụng trong các hệ thống phân tán để đồng bộ hóa truy cập đến các tài nguyên chung. Nó cho phép nhiều máy chủ khác nhau có thể sử dụng cùng một lock để đồng bộ hóa truy cập đến tài nguyên.

- Barrier pattern là một mẫu thiết kế phần tử khác trong lập trình đồng bộ hóa. Nó cho phép nhiều thread chạy song song nhưng không cho phép bất kỳ thread nào tiếp tục cho đến khi tất cả các thread đều hoàn thành một phần công việc. Barrier pattern được sử dụng khi bạn cần đảm bảo rằng một số thread phải hoàn thành một phần công việc của nó trước khi tiếp tục.

- Demo 
    ```Java
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
    ```

## 2.3 Networking 

- Non-blocking IO là một loại IO (input/output) không khóa, trong đó các lời gọi hàm IO không đợi cho đến khi IO hoàn thành, mà nó sẽ trả về ngay lập tức và cho phép chương trình tiếp tục thực thi các công việc khác. Điều này có thể giúp cho chương trình hoạt động hiệu quả hơn bằng cách tránh việc phải chờ IO hoàn thành mỗi lần gọi hàm.

- Zero-Copy
    - Zero-Copy là một kỹ thuật để giảm thiểu số lần sao chép dữ liệu giữa các tầng trong hệ thống máy tính. Khi bạn gửi một tập tin từ máy tính A sang máy tính B, dữ liệu của tập tin sẽ được sao chép từ bộ nhớ của máy tính A sang bộ nhớ trung gian, sau đó từ bộ nhớ trung gian sang bộ nhớ của máy tính B.
    - Kỹ thuật Zero-Copy được sử dụng để giảm thiểu số lần sao chép dữ liệu này bằng cách cho phép dữ liệu được truyền trực tiếp từ bộ nhớ của máy tính A sang bộ nhớ của máy tính B mà không cần sao chép qua bộ nhớ trung gian. Điều này có thể giúp tăng hiệu suất và giảm tải trên hệ thống bằng cách giảm thiểu số lần sao chép dữ liệu.

- Connection pooling là một kỹ thuật để quản lý các kết nối tới một hệ thống đích, thường là một cơ sở dữ liệu. Mục đích của connection pooling là để giảm thiểu số lượng công việc khởi tạo và hủy các kết nối mỗi khi cần truy cập hệ thống đích, bằng cách lưu trữ một tập hợp các kết nối đã được khởi tạo sẵn trong bộ đệm (pool). Khi cần truy cập hệ thống đích, chương trình sẽ lấy một kết nối từ bộ đệm để sử dụng, sau khi hoàn thành công việc, kết nối đó sẽ được trả lại vào bộ đệm để có thể sử dụng lại. Connection pooling có thể giúp tăng hiệu suất của chương trình bằng cách giảm thiểu số lượng công việc khởi tạo và hủy các kết nối mỗi khi cần truy cập hệ thống đích. Điều này có thể giúp giảm tải trên hệ thống và tăng hiệu suất của chương trình.
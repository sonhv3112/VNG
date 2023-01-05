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


## 2.2 Threading 

- Thread-safe là khái niệm để chỉ việc một đoạn mã hoặc một đối tượng có thể được sử dụng bởi nhiều luồng (thread) cùng một lúc mà không gây ra sự nhầm lẫn hoặc xung đột giữa các luồng đó. Trong Java, một đoạn mã hoặc đối tượng được coi là thread-safe khi nó có thể được sử dụng bởi nhiều luồng mà không gây ra sự nhầm lẫn hoặc xung đột trong dữ liệu hoặc trạng thái của đối tượng đó.

- Threads and Executors
    - Trong Java, luồng (threads) là các đối tượng mà ta có thể tạo ra và chạy để thực hiện các tác vụ song song. Ta có thể tạo ra một luồng bằng cách kế thừa lớp Thread hoặc thực hiện lớp Runnable.
    - Executors là một lớp trong Java cung cấp các phương thức tiện ích để tạo ra và quản lý các luồng (threads). Nó cung cấp các phương thức để tạo ra các thread pool, scheduler và các task queue. Executors có thể giúp ta tạo ra và quản lý các luồng một cách dễ dàng hơn so với việc sử dụng lớp Thread trực tiếp.

- Trong Java, ta có thể sử dụng lớp Executor và ExecutorService để tạo ra và quản lý một thread pool. Lớp Executor cung cấp một cách để thực hiện các task bất đồng bộ (asynchronously) mà không cần phải quản lý luồng (threads) một cách trực tiếp. Lớp ExecutorService cung cấp các phương thức tiện ích để quản lý luồng, như tạm dừng hoặc hủy bỏ các luồng. Ví dụ về việc tạo ra một thread pool với số lượng luồng tĩnh bằng 5:

```Java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyClass {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        executor.execute(new MyTask());
        executor.execute(new MyTask());
        executor.execute(new MyTask());

        executor.shutdown();
    }
}

class MyTask implements Runnable {
    @Override
    public void run() {
        Thread
    }
}
```

## 2.3 Networking 


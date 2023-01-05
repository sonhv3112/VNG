Java System Programming
=======================
*Software is a great combination between artistry and engineering (Bill Gates)*

## 1. Target

- Be familiar with various concepts in Linux system programming (using Java)
- Logging, unit tests ...

## 2. Content

### 2.1 Basic 

- [Unit tests with jUnit](http://www.vogella.com/tutorials/JUnit/article.html)

- [Logging with logback](https://stackify.com/logging-logback/). 
	- Phân biệt các khái niệm liên quan tới log level & viết chương trình Java đơn giản sử dụng logging. 
	- Đề xuất cấu hình ghi log (console, file) sử dụng logback để high performance nhất (có thể tham khảo [ở đây](https://blog.takipi.com/how-to-instantly-improve-your-java-logging-with-7-logback-tweaks/)), lý giải những config của em.

### 2.2 Threading

- Khái niệm thread-safe là gì?

- [Threads and Executors](http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/)

- [Thread pool](http://www.baeldung.com/thread-pool-java-and-guava)

- Mô hình hoạt động của [ForkJoinPool](https://www.javaworld.com/article/2078440/enterprise-java/java-tip-when-to-use-forkjoinpool-vs-executorservice.html), viết ứng dụng demo đơn giản.

- [Synchronization and Locks](http://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/). Làm rõ cách hoạt động và usecase của mỗi loại lock sau:
	- ReentrantLock
	- ReadWriteLock
	- [StampedLock](https://dzone.com/articles/a-look-at-stampedlock)
	- Semaphores
	- Spin lock với `atomic variables`. So với các hình thức lock khác, `spin lock` có gì hay/dở? [Tham khảo](https://chrisadkin.io/2015/03/31/spinlocks-when-to-worry-about-them-and-solutions-to-common-problems/)

- [Distributed Locks](https://martin.kleppmann.com/2016/02/08/how-to-do-distributed-locking.html). Demo với [Redisson](https://github.com/redisson/redisson/wiki/8.-Distributed-locks-and-synchronizers)

- [Barrier pattern](https://en.wikipedia.org/wiki/Barrier_(computer_science)) với Java `CountdownLatch` (demo code)


### 2.3 Networking

- [The Various Kinds of IO - Blocking, Non-blocking, Multiplexed and Async.](https://www.rubberducking.com/2018/05/the-various-kinds-of-io-blocking-non.html)

- [Non-blocking IO](https://medium.com/@copyconstruct/nonblocking-i-o-99948ad7c957) là gì?

- Tìm hiểu chung về [Java NIO](http://tutorials.jenkov.com/java-nio/index.html) và [Netty](https://www.slideshare.net/kslisenko/networking-in-java-with-nio-and-netty-76583794). Tham khảo thêm: [Benefits of Netty over basic ServerSocket server?](https://stackoverflow.com/questions/8406914/benefits-of-netty-over-basic-serversocket-server), 



- Kỹ thuật [Zero-Copy](https://www.ibm.com/developerworks/library/j-zerocopy/) giải quyết vấn đề gì?

- [Connection pooling](https://en.wikipedia.org/wiki/Connection_pool) giải quyết vấn đề gì?

## 3. Exercises

### 3.1 Let's Chat

Thiết kế chương trình chat bao gồm client và server (na ná giống Slack). User dùng client kết nối vào server để:

- Đăng ký account chat hoặc Login một account đã có sẵn 

- Tạo/join/liệt kê kênh chat (ví dụ #general, #family...)

- Chat tới một người hoặc tới một kênh 

- User có thể sign out

**Cách thực hiện**
- Chia thành cặp
- Cùng nhau thiết kế hệ thống, phân công phần việc theo các mô tả bên dưới
- Demo & trình diễn về kết quả làm việc 

#### 3.1.1 Protocol 

- Thiết kế protocol request/response dựa trên TCP, với [Protobuf](https://github.com/google/protobuf)

- Protocol cần đáp ứng các action ở trên

- Protocol này được dùng chung cho tất cả mọi người trong `nhóm`

- Chú ý: khi người dùng chưa thực hiện đăng nhập -> reject mọi request về sau. Cái này có thể thực hiện bằng việc dùng ý tưởng của [JWT](http://jwt.io/) để sinh ra một token gửi về cho authenticated client. Các request về sau phải gửi kèm token thì mới hợp lệ.

- Tips: cấu hình Maven để compile `.proto` (Phần [5.2. Using Maven Plugin](http://www.baeldung.com/grpc-introduction))

#### 3.1.2 Server 

- Dựa trên TCP, implement socket server viết dựa trên [Java NIO](http://tutorials.jenkov.com/java-nio/non-blocking-server.html) và [Netty](http://www.baeldung.com/netty). Tức là có 2 server giải quyết cùng 1 vấn đề, bằng 2 cách khác nhau: 1 dùng Java NIO, 1 dùng Netty (Chú ý nên thiết kế để tái sử dụng code tốt, dễ chuyển sang dùng một network library khác)

- Dùng Rocksdb cho persistent data chat. Cần thiết kế interface để có thể sử dụng storage engine khác về sau.

#### 3.1.3 Client

- Chương trình đơn giản để chat :D 

- Client mở persistent connection tới Server 

### 4. Tham khảo 

[Netty Best Practices](http://normanmaurer.me/presentations/2014-facebook-eng-netty/slides.html)

[Netty in Action](http://pdf.th7.cn/down/files/1603/Netty%20in%20Action.pdf)
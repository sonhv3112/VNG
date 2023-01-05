System Programming
=======================
*Without requirements and design, programming is the art of adding bugs to an empty text file. (Louis Srygley)*

## 1. Target

- Be familiar with various concepts in Linux system programming (using Java)

- Logging, unit tests ...


## 2. Content

Những nội dung ghi là cần trình diễn sẽ được tiến hành bằng cách: chọn người ngẫu nhiên và thuyết trình

### 2.4 Reactive 

- [Reactive manifesto](https://www.reactivemanifesto.org/) nói về những vấn đề gì?

- Tìm hiểu về project [reactor](http://projectreactor.io/learn) và [RxJava](http://www.vogella.com/tutorials/RxJava/article.html). 

```
Reactive programming provides a simple way of asynchronous programming. This allows to simplify the asynchronously processing of potential long running operations. It also provides a defined way of handling multiple events, errors and termination of the event stream. Reactive programming provides also a simplified way of running different tasks in different threads. For example, widgets in SWT and Android have to be updated from the UI thread and reactive programming provides ways to run observables and subscribers in different threads.

It is also possible to convert the stream before its received by the observers. And you can chain operations, e.g., if a API call depends on the call of another API Last but not least, reactive programming reduces the need for state variables, which can be the source of errors.
```

- Viết lại chat server sử dụng [reactor](http://projectreactor.io/learn)

- Tìm hiểu chung về [Vertx](http://vertx.io/) nói về những feature mà vertx hỗ trợ. Viết một RESTful API (method Post) đơn giản thực hiện phép tính cộng trên `vertx`. Dùng Rest client [insomnia](https://insomnia.rest/download/) để validate lại API

- Trình diễn nội dung tìm hiểu được

### 2.5 Benchmarking

- Làm rõ khái niệm `throughput` và `latency` trong benchmark network service.

- Kịch bản tiến hành thế nào để đo `throughput` và `latency` ?

- Tại sao cần chạy [warm up](http://www.baeldung.com/java-jvm-warmup) trước khi benchmark?

- [HdrHistogram](http://hdrhistogram.github.io/HdrHistogram/plotFiles.html) cho ta thấy điều gì khi đo đếm latency?

- Thực hiện benchmark cho service REST vertx ở trên.

### 2.6 Memory management

- Tại sao network server cần phải quan tâm tới chuyện Memory Allocation?

- Java quản lý memory như thế nào? Làm rõ các loại reference.

- Viết module hàm để concat ByteBuffer lại thành 1 ByteBuffer lớn hơn theo template sau:

```java
public ByteBuffer join(List<ByteBuffer> buffers){
	// Implement it
}
public ByteBuffer join(ByteBuffer... buffers){
	// Implement it
}
```

- Những điều gì cần chú ý để tối ưu Java app.

**Tham khảo**
- [Java Memory Management](https://dzone.com/articles/java-memory-management)

- [JavaOne 2013: Memory Efficient Java](https://www.slideshare.net/cnbailey/memory-efficient-java)

http://www.kdgregory.com/index.php?page=java.byteBuffer

https://www.javacodegeeks.com/2012/12/the-java-bytebuffer-a-crash-course.html

http://tutorials.jenkov.com/java-performance/index.html
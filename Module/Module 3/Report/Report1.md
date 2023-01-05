# Module 3.1

Họ và tên: Hồ Văn Sơn \
Domain: sonhv2 

---------------------------------------

## 2.4. Reactive 

- `Reactive manifesto` là một tiêu chuẩn để thiết kế hệ thống, sao cho đảm bảo 4 tiêu chí sau: Responsive, Resilient, Elastic và Message Driven. Hệ thống được xây dựng tuân theo Reactive system đem lại nhiều lợi ích như linh hoạt, dễ dàng nâng cấp, mở rộng, mang lại người dùng một trải nghiệm tốt với khả năng phản hồi nhanh, tương tác hiệu quả. 
    - Responsive (tính phản hồi): Hệ thống có khả năng phản hồi nhanh và đáp ứng các yêu cầu từ phía người dùng một cách nhanh chóng. 
    - Resilient (tính bền bỉ): Hệ thống có khả năng chịu được những sự cố trong lúc vận hành mà vẫn tiếp tục hoạt động bình thường sau đó. 
    - Elastic (tính linh động): Hệ thống có khả năng mở rộng hoặc thu nhỏ lại để phù hợp với các nhu cầu khác nhau của các tập người dùng khác nhau. 
    - Message Driven: Hệ thống sử dụng cơ chế bất đồng bộ, tin nhắn để giao tiếp giữa các thành phần khác nhau trong hệ thống, nhằm giảm thiểu sự phụ thuộc giữa các thành phần, điều này góp phần giúp tăng khả năng mở rộng và linh động trong việc sử dụng cấu hình hệ thống. 

- `Vertx` là một nền tảng xây dựng ứng dựng đảm bảo tính nhẹ, linh hoạt, mà hiệu quả cao và reactive. Nó cho phép tạo ra các ứng dụng có tính hướng sự kiện và non-blocking (bất đồng bộ). Một số tính năng chính của vertx như: 
    - Đa ngôn ngữ, đa nền tảng: Cho phép viết ứng dụng bằng một số ngôn ngữ phổ biến hiện nay như Java, JS, Ruby, Groovy, Python, và Ceylon. Ứng dụng có thể được deploy trên nhiều nền tảng như server, máy tính, di động. 
    - Reactive: Tuân theo Reactive manifesto, có tính hướng sự kiện và non-blocking. 
    - Event-driven: Được xây dựng dựa trên framework Netty, cho phép xử lý hiệu quả nhiều yêu cầu cùng một lúc. 
    - Non-blocking: Điều này cho phép mở rộng phần mềm để có thể quản lý nhiều connection cùng một lúc mà không cần số lượng lớn thread như cơ chế blocking. 
    - Clustering: Cho phép server có thể được chia theo chiều ngang để mở rộng.
    - Modularity: Được thiết kế theo dạng module hóa nên có thể tích hợp nhiều module trong một ứng dụng. 
    - Nhẹ, linh hoạt: Vùng phủ bộ nhớ (memory footprint) sử dụng ít, có thể mở rộng, thay đổi phù hợp đối với những môi trường bị hạn chế về mặt tài nguyên. 

- Cài đặt restful api đơn giản thực hiện phép cộng: 

    ```Java
    public void start() {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.post("/add").handler(this::addHandler);

        vertx.createHttpServer()
            .requestHandler(router)
            .listen(8080)
            .onSuccess(s -> System.out.println("HTTP server started on port " + s.actualPort()));
    }

    private void addHandler(RoutingContext routingContext) {
            
        JsonObject requestBody = routingContext.getBodyAsJson();

        int a = Integer.parseInt(requestBody.getString("a"));
        int b = Integer.parseInt(requestBody.getString("b"));
        int c = this.add(a, b);

        routingContext.response()
            .putHeader("content-type", "application/json")
            .setStatusCode(200)
            .end(Json.encodePrettily(new AddResult(a, b, c)));
    }
    ```
    - Server sẽ nhận POST request tại `/add` với body request là JSON, 2 số sẽ ở 2 trường "a", và "b". Kết quả trả về là JSON với 3 trường "a", "b" là 2 số được input vào và "c" là tổng của hai số a, b. 


<p align="center">
    <image src="./assets/rest_demo_0.png" 
        width=90%/> 
    </br>
    Dùng insomnia để test API (1)
</p>

<p align="center">
    <image src="./assets/rest_demo_1.png" 
        width=90%/> 
    </br>
    Dùng insomnia để test API (2)
</p>


## 2.5 Benchmarking

### 2.5.1 Throughput & Latency

- Khái niệm: 
    - `Throughput` biểu diễn lưu lượng data mà một network service có thể nhận, xử lý trong một đơn vị thời gian, thông thường throughput được đo theo đơn vị `bits per second` hay `packets per second`. Throughput cao chỉ ra rằng network service đấy vẫn có thể xử lý ổn định trong khi traffic cao và do đó dễ dàng để mở rộng, nâng cấp sau này. 
    - `Latency` biểu diễn thời gian mà một request được network service tiến hành xử lý và trả về. Latency (độ trễ) thấp là rất quan trọng trong các ứng dụng thực tế ngày nay, bởi nó đồng nghĩa với việc rằng tốc độ phản hồi yêu cầu của service là nhanh, từ đó cải thiện trải nghiệm ứng dụng cho người dùng. 
    - Khi thực hiện benchmark một network service, việc benchmark throughput và latency là rất quan trọng, bởi ta cần biết được network service đấy liệu có tốt, ổn định hay không. Một network service có throughput cao cho phép tiếp nhận một lượng lớn request, tuy nhiên nếu latency cao thì rõ ràng có thể rằng những request đấy không thể được xử lý kịp thời trong thời gian ngắn để trả về cho phía người dùng. Điều ngược lại cũng diễn ra tương tự. 

- Tiến hành đo: 
    1. Setup network service cần tiến hành benchmark. 
    2. Viết script hoặc sử dụng các tool để gửi request đến service. Script hoặc tool sử dụng cần đảm bảo tuân theo các bước cơ bản: 
        - Khởi tạo sẵn các tài nguyên cần cho việc benchmark như connection, cấu trúc dữ liệu lưu trữ kết quả cho việc benchmark. 
        - Thực hiện bước warm up bằng việc gửi một số lượng request cố định đến server hoặc service cần benchmark. Điều này giúp cho server có thể thực hiện một số bước như cache hoặc một số công việc lúc khởi tạo khác. 
        - Tiếp theo gửi một số lượng request đến server, các request liên tiếp nên cách nhau một khoảng thời gian cố định. 
        - Ghi lại thời gian xử lý và phản hồi từng request của server vào kết quả. 
        - Đợi request cuối cùng kết thúc thì tính toán throughput và latency tương ứng. 

- Việc chạy warm up trước khi benchmark là cần thiết bởi một số lý do sau: 
    - Caching: Nhiều server, service hiện nay thường sử dụng cơ chế cache nên việc chạy warm up sẽ giúp một số dữ liệu được cache tương tự như trong trường hợp chạy trên thực tế. 
    - JIT compilation: Một số ngôn ngữ, ví dụ như Java, sử dụng Just-In-Time (JIT) compilation để tối ưu mã nguồn trong quá trình chạy. Trong quá trình warm up, JIT compiler có thêm cơ hội để tối ưu code tương tự như thực tế trước khi chạy benchmark. 
    - Resource allocation: Một số hệ thống, server thường chiếm tài nguyên một cách động (memory, CPU, ...) dựa theo cấu hình, quá trình chạy của chúng. Bước warm up giúp quá trình này có thể phân bổ tài nguyên trước một cách chính xác.
    
- HdrHistogram phân tích latency trong lúc đo. Nó thực hiện bằng cách lưu lại phân phối của các latency trong một khoảng thời gian, sử dụng histogram. Thông qua hdrhistogram ta có thể thấy được được nhiều thông số thống kế về phân phối của latency như minimum, maximum, trung bình và phần trăm giá trị tương ứng. 

- Để tiến hành benchmark cho service trên, ta viết một script bằng Java để benchmark thông qua Http request: 
    ```Java
    private static void sendRequest(String json) throws Exception {
        URL url = new URL("http://localhost:8080/add");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        byte[] jsonBytes = json.getBytes(StandardCharsets.UTF_8);

        OutputStream os = con.getOutputStream();
        os.write(jsonBytes, 0, jsonBytes.length);

        Thread.sleep(TIME_SLEEP);

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String responseLine = null;
        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }

        System.out.println(response.toString());
        System.out.println("Status code: " + con.getResponseCode());

        br.close();
        os.close();
        con.disconnect();
    }
    ```
    - Ở đây bước warmup sẽ gửi 500 request và bước benchmark sẽ gồm 5000 request. Sau đó latency được ghi lại vào HdrHistogram, Throughput sẽ được đo mỗi 5s một lần và lưu vào một list. 
    ```Java
    
        for (int i = 1; i <= NUM_REQUESTS_WARMUP; ++i) {
            System.out.println("Warm up step " + i + ": ");
            sendRequest("{\"a\": " + i + ", \"b\": " + (i + 1234) + "}");
        }

        long startTimeThroughput = System.nanoTime();
        int numSent = 0;
        List<Double> throughputList = new ArrayList<Double>();

        Histogram histogram = new Histogram(3);

        for (int i = 0; i < NUM_REQUESTS; i++) {
            System.out.println("Benchmark step " + i + ": ");
            long startTime = System.nanoTime();

            sendRequest("{\"a\": " + i + ", \"b\": " + (i + 1234) + "}");
            ++numSent;

            long endTime = System.nanoTime();
            long latency = endTime - startTime;
            histogram.recordValue(latency);

            long elapsedTimeThroughput = (long)((System.nanoTime() - startTimeThroughput) / 1e9);
            if (elapsedTimeThroughput >= INTERVAL_CAL_THROUGHPUT) {
                double throughput = numSent / elapsedTimeThroughput;
                throughputList.add(throughput);
                startTimeThroughput = System.nanoTime();
                numSent = 0;
            }
        }

        System.out.println("Throughput measured list (requests/sec):");
        for (Double x : throughputList)
            System.out.println(x + " requests/sec");

        System.out.println("Latency (ms)");
        System.out.println("Min: " + histogram.getMinValue() / 1e6);
        System.out.println("Max: " + histogram.getMaxValue() / 1e6);
        System.out.println("Mean: " + histogram.getMean() / 1e6);
        System.out.println("Std Dev: " + histogram.getStdDeviation() / 1e6);
        System.out.println("50th percentile: " + histogram.getValueAtPercentile(50) / 1e6);
        System.out.println("99th percentile: " + histogram.getValueAtPercentile(99) / 1e6);
        System.out.println("99.9th percentile: " + histogram.getValueAtPercentile(99.9) / 1e6);
    ``` 
    - Kết quả đo được như sau:
    <p align="center">
        <image src="./assets/benchmark.png" 
            width=50%/> 
    </p>

## 2.6 Memory management

- Memory allocation cần được đặc biệt quan tâm trong network server bởi nó có thể ảnh hưởng đến hiệu năng và khả năng mở rộng, nâng cấp của server sau này. Lấy ví dụ, nếu một server hiện tại đang phân bổ quá nhiều tài nguyên nó có thể đánh đổi với hiệu năng bởi nó làm gia tăng việc dọn dẹp rác (garbage colletion) sau khi các tài nguyên đó không còn được sử dụng. Do đó điều này có thể dẫn đến việc không thể xử lý một số request trong một giây. Trong trường hợp ngược lại, nếu server không phân bổ đủ lượng tài nguyên thì nó cũng sẽ làm giảm hiệu năng bởi phải thường xuyên phân bổ lại tài nguyên để tương thích với số tài nguyên đang sử dụng nhằm đáp ứng số lượng request đến tương ứng. 

- Trong Java, memory được quản lý một cách tự đổng bởi JVM (Java Virtual Machine). Trong đó, JVM sử dụng một garbage collector để quét vùng nhớ heap một cách định kì và phát hiện những đối tượng không được sử dụng trong thời gian dài để loại bỏ chúng khỏi vùng nhớ và giải phóng vùng nhớ. 

- Có ba loại references trong Java: strong, weak và soft. 
    - Strong reference: Là loại reference phổ biến nhất trong Java. Chúng được tạo ra mặc định mỗi khi một đối tượng được gán vào mọt biến. Một đối tượng được trỏ đến bởi strong reference sẽ đảm bảo rằng đối tượng đó sẽ không bị garbage collector loại bỏ. 
    - Weak reference: Yếu hơn strong reference bởi chúng không ngăn chặn được việc garbage collector bộ nhớ của đối tượng. Weak reference thường được sử dụng để cài đặt cho các cấu trúc dữ liệu tương tự cache, có đặc điểm chung là thường bị xóa đi khi bộ nhớ rỗng không còn đủ. 
    - Soft reference: Yếu hơn weak reference, sẽ bị loại bỏ khi bộ nhớ JVM còn thấp. Soft reference thường được sử dụng để cài đặt các cấu trúc dữ liệu tương tự như cache sensitive. 

- Concat ByteBuffer
    ```Java
    public ByteBuffer join(List<ByteBuffer> buffers){
        int totalSize = buffers.stream().mapToInt(ByteBuffer::limit).sum();
        ByteBuffer result = ByteBuffer.allocate(totalSize);

        for (ByteBuffer buffer : buffers) {
            result.put(buffer.array());
        }

        result.flip();
        return result;
    }

    public ByteBuffer join(ByteBuffer... buffers){
        int totalSize = 0;
        for (ByteBuffer buffer : buffers) {
            totalSize += buffer.limit();
        }

        ByteBuffer result = ByteBuffer.allocate(totalSize);
        for (ByteBuffer buffer : buffers) {
            result.put(buffer.array());
        }

        result.flip();
        return result;
    }
    ```

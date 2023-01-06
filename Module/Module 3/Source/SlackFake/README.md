## Todo List
- Create entities
- Implement database (InMemory)
- Create request/response models
- Register (K)
- Login (S)
- Logout (K)
- Create Channel (K)
- Join Channel (S)
- Listing Channel (K)
- Send message to channel (S)
- Implement NIO
- Implement Netty
- Implement Rocksdb
- Create Client
	- Persistent connection

## For Developers
- `mvn clean package` or `mvn antrun:run` to generate protobuf classes

## Requirements
### 1. Let's Chat

Thiết kế chương trình chat bao gồm client và server (na ná giống Slack). User dùng client kết nối vào server để:

- Đăng ký account chat hoặc Login một account đã có sẵn 

- Tạo/join/liệt kê kênh chat (ví dụ #general, #family...)

- Chat tới một người hoặc tới một kênh 

- User có thể sign out

**Cách thực hiện**
- Chia thành cặp
- Cùng nhau thiết kế hệ thống, phân công phần việc theo các mô tả bên dưới
- Demo & trình diễn về kết quả làm việc

#### 2. Protocol 

- Thiết kế protocol request/response dựa trên TCP, với [Protobuf](https://github.com/google/protobuf)

- Protocol cần đáp ứng các action ở trên

- Protocol này được dùng chung cho tất cả mọi người trong `nhóm`

- Chú ý: khi người dùng chưa thực hiện đăng nhập -> reject mọi request về sau. Cái này có thể thực hiện bằng việc dùng ý tưởng của [JWT](http://jwt.io/) để sinh ra một token gửi về cho authenticated client. Các request về sau phải gửi kèm token thì mới hợp lệ.

- Tips: cấu hình Maven để compile `.proto` (Phần [5.2. Using Maven Plugin](http://www.baeldung.com/grpc-introduction))

#### 3. Server 

- Dựa trên TCP, implement socket server viết dựa trên [Java NIO](http://tutorials.jenkov.com/java-nio/non-blocking-server.html) và [Netty](http://www.baeldung.com/netty). Tức là có 2 server giải quyết cùng 1 vấn đề, bằng 2 cách khác nhau: 1 dùng Java NIO, 1 dùng Netty (Chú ý nên thiết kế để tái sử dụng code tốt, dễ chuyển sang dùng một network library khác)

- Dùng Rocksdb cho persistent data chat. Cần thiết kế interface để có thể sử dụng storage engine khác về sau.

#### 4. Client

- Chương trình đơn giản để chat :D 

- Client mở persistent connection tới Server 

### 5. Tham khảo 

[Netty Best Practices](http://normanmaurer.me/presentations/2014-facebook-eng-netty/slides.html)

[Netty in Action](http://pdf.th7.cn/down/files/1603/Netty%20in%20Action.pdf)

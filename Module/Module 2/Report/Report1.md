# Module 2.1

Họ và tên: Hồ Văn Sơn \
Domain: sonhv2 

---------------------------------------

## 2.1. Design pattern & clean code

### 2.1.a. 5 Design pattern thường sử dụng 

**Singleton** 

- Đảm bảo lớp chỉ có duy nhất một instance được tạo ra. Code minh họa phía dưới được cài đặt bằng Java.

```java 
class Singleton { 
    private volatile static Singleton _instance = null; 
    private Singleton() {}
    public static Singleton getInstance() { 
        Singleton result = _instance; 
        if (result == null) { 
            synchronized (this) { 
                result = _instance; 
                if (result == null) {  
                    result = _instance = new Singleton(); 
                }
            }
        }   
        return result; 
    }
}
```

**Factory Method**

- Cung cấp một interface mô tả các đối tượng có khả năng sinh ra các đối tượng product cụ thể, hay nói cách khác nó giúp tạo ra một đối tượng mà không cần thiết chỉ ra rằng chính xác lớp nào được tạo ra từ lớp nào. Code minh họa bên dưới được cài đặt bằng Java. 

- Factory.java
```java
package FactoryMethod;

interface Factory { 
    public Product getProduct();
}
```

- FactoryA.java
```java
package FactoryMethod;

public class FactoryA implements Factory {
    public Product getProduct() { 
        return new ProductA();
    }
}
```

- FactoryB.java
```java
package FactoryMethod;

public class FactoryB implements Factory {
    public Product getProduct() { 
        return new ProductB();
    }
}
```

- Product.java
```java
package FactoryMethod;

public interface Product { 
    public void print(); 
}
```

- ProductA.java
```java
package FactoryMethod;

public class ProductA implements Product {
    public void print() { 
        System.out.println("This is product A");
    }
}
```

- ProductB.java
```java
package FactoryMethod;

public class ProductB implements Product {
    public void print() { 
        System.out.println("This is product B");
    }
}
```

**Abstract Factory**

- `Abstract Factory` cung cấp giải pháp để đóng gói một nhóm các "nhà máy" cụ thể có điểm chung mà không cần chỉ ra `Product` cụ thể mà nó tạo ra. Code minh họa cài đặt các "nhà máy" để  tạo ra các loại checkbox hoặc nút nhấn (giả định bằng cách chỉ in ra màn hình thông báo) trên Window và MacOS, phần mã nguồn được đặt tại `Source/DesignPattern/AbstractFactory`. 

**Builder**

- `Builder` là một design pattern cho phép ta tạo ra object từng bước bằng cách xây dựng từng phần của object đấy. Pattern này cho phép ta tạo ra các kiểu, hình khác nhau cùng một loại object.

<p align="center">
    <image src="./assets/structure.png" 
        width=80%/> 
    </br>
    Cấu trúc của Builder (Nguồn: 
    <a href="https://refactoring.guru/design-patterns/builder">
        Refactoring Guru
    </a>
    )
</p>

- Phần code minh họa dưới đây 

**Dependency Injection**

### 2.1.b. Thế nào là clean code? Nêu các ví dụ để clean code.

- Clean code là viết mã nguồn một cách có tổ chức, dễ đọc, dễ hiểu và dễ để duy trì, nâng cấp cho sau này. Đây là một điều quan trọng để việc làm việc nhóm trở nên dễ dàng hơn. Clean code có thể theo một số chuẩn hay quy ước nào đó, giúp cho người khác có thể dễ hiểu, dễ đoán được ý nghĩa của đoạn mã nguồn mà mình viết. 

- Cụ thể có một số phần như sau: 
    - Naming convention: Ta cần đặt tên biến, hàm, lớp, ... một cách dễ hiểu, tường minh, tương ứng với ý nghĩa, ngữ nghĩa của chúng. Trong nhiều trường hợp, biến thường là một danh từ và hàm thường bắt đầu bằng một động từ. Ví dụ để tính toán giá trị trung vị của một dãy thay vì đặt `'med()'` thì ta nên đặt `'calculateMedian()'`. 
    - Formatting: Các mã nguồn nên tuân theo một định dạng cụ thể, định dạng đấy có thể bao gồm yêu cầu thực hiện việc sử dụng khoảng cách, thụt đầu dòng, cách dòng một cách thống nhất, ... Ta có thể xem ví dụ như sau: 
        ```java
        // BAD
        for (int i=0;i<10;++i) System.out.println(i); 

        // GOOD
        for (int i = 0; i < 10; ++i)
            System.out.println(i);
        ```
    - Comments and documentation: Cần chú thích và dẫn các nguồn (nếu tham khảo mã nguồn, hoặc sử dụng thuật toán nào đấy) để giải thích rõ cách mà hàm, mã nguồn chạy, hoặc giá trị đầu vào, giá trị đầu ra. 
    - Modularity: Chia nhỏ những module lớn, phức tạp thành nhiều module nhỏ, dễ hiểu hơn để cài đặt một cách tường minh hơn. Sử dụng các hàm để xử lý các công việc nhỏ, một hàm chỉ nên xử lý một công việc cụ thể. 

## 2.2. Cấu trúc dữ liệu 

### Probabilistic algorithm 

- Là các thuật toán liên quan, ứng dụng xác suất thống kê trong việc lấy input, quá trình xử lý hay cả quá trình trả output. Các thuật toán này thường được sử dụng trong các trường hợp mà việc tìm kết quả chính xác là tốn quá nhiều chi phí hay không thể tính toán một cách chính xác được giá trị đấy, và lúc này người ta thường có xu hướng sử dụng `Probabilistic algorithm` và chấp nhận kết quả sẽ đúng theo một tỷ lệ nào đấy. 

- Một số tính chất của `Probabilistic algorithm` có thể kể đến như: 
    - Có thể trả ra nhiều kết quả khác nhau với các lần chạy khác nhau, mặc dù cùng một input. 
    - Kết quả có một độ tin cậy nhất định tuân theo một phân phối nào đó. 
    - Có thể sử dụng trên tập dữ liệu bị thiếu hoặc dữ liệu nhiễu, không chắc chắn.
    - Tìm được kết quả xấp xỉ (có thể sai) cho các bài toán không thể tìm ra kết quả chính xác. 

- **Bloom Filters**
    - Cấu trúc này là một cấu trúc dữ liệu xác suất với độ phức tạp không gian nhỏ để lưu trữ tập các phần tử và kiểm tra xem một phần tử bất kỳ có nằm trong tập hay không. Tuy nhiên việc kiểm tra và đưa ra kết quả có thể chính xác hoặc không chính xác, bởi đây là một cấu trúc dữ liệu dựa vào xác suất.
    - Thuật toán này gồm hai giá trị đặc trưng là `k` và `m`. Trong đó `m` là số bit được sử dụng cho mảng lưu trữ, `k` là số hàm băm được sử dụng, mà mỗi hàm băm sẽ trả về các giá trị từ 1 đến m (hoặc 0 đến m - 1 tùy định nghĩa). 
    - Để chèn một chuỗi vào cấu trúc dữ liệu này, ta thực hiện việc cho chuỗi qua lần lượt `k` hàm băm đã nói trên và thu về `k` giá trị (có thể trùng nhau) trong khoảng từ 1 đến m. Các bit ở mảng lưu trữ tại các vị trí mà nằm trong `k` giá trị thu được sẽ được bật lên 1. 
    - Để kiểm tra một chuỗi "có thể" nằm trong bloom filter hay không, ta thực hiện cho chuỗi qua lần lượt `k` hàm băm và thu về `k` giá trị, nếu các bit ở mảng lưu trữ tại `k` vị trí này đều là 1 thì ta kết luận chuỗi có thể nằm trong tập, ngược lại ta kết luận chuỗi chắc chắn không nằm trong tập, 

- **Cuckoo Filters**
    - Với mục đích, tính chất tương tự với bloom filter, tuy nhiên hướng tiếp cận của cấu trúc dữ liệu này có đôi phần khác đi. 
    - 


- **Count Min Sketch**

- **HyperLogLog**

### Trie 

- Trie (hay cây tiền tố) là một cấu trúc dữ liệu lưu trữ các chuỗi để thao tác trên tập chuỗi, có một số tính chất sau: 
    - Có dạng cây với một gốc. 
    - Trên mỗi cạnh sẽ chứa thông tin là một ký tự. 
    - Đường đi từ gốc đến một đỉnh bất kỳ sẽ thể hiện một xâu. 
    - Thường để lưu trữ tất cả tiền tố của các chuỗi trong tập. 
- Các thao tác cơ bản trên Trie: 
    - Thêm một chuỗi vào trie, độ phức tạp $O(L)$ với $L$ là độ dài của chuỗi cần thêm. 
    - Kiểm tra xem một chuỗi có trong trie không, độ phức tạp $O(L)$ với $L$ là độ dài của chuỗi cần tìm. 
    - Xóa một chuỗi khỏi tập, độ phức tạp $O(L)$ với $L$ là độ dài của chuỗi cần xóa. 
    - Ngoài ra, ta cũng có một số thao tác phức tạp hơn như tìm những chuỗi có thứ tự từ điển nhỏ nhất mà lớn hơn một chuỗi hoặc ngược lại là thứ tự từ điển lớn nhất mà nhỏ hơn chuỗi cho trước, hay nếu mỗi node trên trie lưu tổng số chuỗi có trong cây con gốc tại node đấy thì ta có thể tìm chuỗi có thứ tự từ điển lớn thứ $K$ trong $O(L \times AL)$ với $L$ là độ dài chuỗi cần tìm và $AL$ là số con tối đa của mỗi node trên trie, ...
- Bên cạnh đó, cũng có một số thuật toán cực kỳ mạnh mẽ trên Trie như Aho-corasick để tìm kiếm nhiều chuỗi trên Trie hay cũng tương tự như Commentz-Walter. 

### Hashing 

- Điểm giống nhau: Crypto & Non-crypto đều là các hàm băm ánh xạ từ một dữ liệu với chiều dài bất kì sang chiều dài cố định. 
- Điểm khác nhau: 
    - Vấn đề bảo mật: 
        - Crypto hash function được thiết kế nhằm đảm bảo không tồn tại hai dữ liệu đầu vào nào cho ra cùng một giá trị đầu ra, và ta cũng không thể khôi phục lại dữ liệu đầu vào từ dữ liệu đầu ra. 
        - Non-crypto hash function không đảm bảo rằng hai dữ liệu đầu vào cho ra hai giá trị đầu ra khác nhau và ta cũng có thể khôi phục lại dữ liệu đầu vào từ đầu ra. 
    - Tốc độ: Thông thường crypto hash function có tốc độ chạy chậm hơn non-crypto bởi nó được thiết kế dành cho vấn đề bảo mật nên yêu cầu việc tính toán phức tạp, nhiều bước hơn. 
    - Ứng dụng: Crypto hash function thường được ứng dụng vào các việc yêu cầu tính bảo mật cao như mật khẩu, đảm bảo tính bảo mật cơ sở dữ liệu, chữ ký điện tử, ... Còn Non-crypto hash function có tốc độ tính toán nhanh hơn nhưng có thể xảy ra các trường hợp đụng độ do đó thường được sử dụng trong các cấu trúc dữ liệu tìm kiếm, nén dữ liệu. 

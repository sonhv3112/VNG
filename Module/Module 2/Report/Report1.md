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
    - Modularity: 
    - Error Handling: 
    - Testability:  
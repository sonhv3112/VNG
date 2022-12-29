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

- Phần code minh họa dưới đây cài đặt giả lập việc tạo một "thông tin" của người dùng. `User` sẽ có các thông tin như `FirstName, LastName, Phone, Address, Age`. `UserBuilder` sẽ được cài đặt như sau: 

```java
public class UserBuilder {
    private User result; 

    UserBuilder() {
		this.result = new User();
    }

	public UserBuilder setFirstName(String firstName) {
		this.result.setFirstName(firstName);
		return this; 
	}

	public UserBuilder setLastName(String lastName) {
		this.result.setLastName(lastName);
		return this; 
	}

	public UserBuilder setAge(int age) {
		this.result.setAge(age);
		return this; 
	}

	public UserBuilder setPhone(String phone) {
		this.result.setPhone(phone);
		return this; 
	}

	public UserBuilder setAddress(String address) {
		this.result.setAddress(address);
		return this; 
	}

    public User build() { 
        User user = this.result; 
        this.result = new User(); 
        return user; 
    }
}
```

**Dependency Injection**

- Dependency Injection là một design pattern mà trong đó một đối tượng hoặc một hàm nhận các hàm hoặc đối tượng khác mà nó phụ thuộc vào hay nói cách khác ta cung cấp đối tượng hoặc hàm với các phụ thuộc của nó.
- Mẫu thiết kế này cung cấp một số lợi ích như cho phép chúng ta dễ dàng thay đổi các sự phụ thuộc của một hàm, hoặc đối tượng, điều này là rất hữu ích trong việc testing. Và bên cạnh đó ta cũng có thể dễ dàng tái sử dụng lớp bởi vì các phụ thuộc đều có thể dễ dàng thay đổi mà không cần phải hard code. 
- Ví dụ ta có một bài toán kết nối với database, thông thường ta sẽ fix cứng các phụ thuộc với database như `host`, `username`, `password` khi cài đặt. Tuy nhiên chính việc này sẽ làm cản trợ việc mở rộng hệ thống hay giả sử ta muốn đổi host cũng rất khó khăn khi phải đi config lại tất cả. Để giải quyết bài toán này ta cung cấp một lớp `DatabaseConnection` mà có các phụ thuộc như `host`, `username`, `password` để việc config sau này trở nên dễ dàng hơn. 

```java
public class DatabaseConnection { 
    private String host; 
    private String username; 
    private String password; 

    public DatabaseConnection(String host, String username, String password) { 
        this.host = host; 
        this.username = username; 
        this.password = password; 
    }

    public void connect() { 
        // Connect to database 
    }
}
```

- Dependency Injection cũng có các loại thường gặp như: 
    - Constructor injection: Các phụ thuộc được truyền vào thông qua constructor. 
    - Setter injection: Các phụ thuộc sẽ được truyền vào thông qua setter.
    - Interface injection: Dependency sẽ cung cấp một Interface, trong đó có chứa hàm có tên là Inject. Các client phải triển khai một Interface mà có một setter method dành cho việc nhận dependency và truyền nó vào class thông qua việc gọi hàm Inject của Interface đó.

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

### **Probabilistic algorithm**

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
    - Với mục đích, tính chất tương tự với bloom filter, tuy nhiên hướng tiếp cận của cấu trúc dữ liệu này có đôi phần khác đi và nó cũng có một số điểm lợi hơn bloom filter trong đó nó có thể cho phép khả năng xóa phần tử khỏi tập và tỷ lệ trả lời sai là thấp hơn. 
    - Cuckoo filter được cài đặt dựa trên cuckoo hash table, đây là cấu trúc dữ liệu hash table sử dụng hai hàm hash để ánh xạ phần tử thành index trong một dãy mà nó dùng để lưu trữ. Trong một Cuckoo filter, dãy sẽ được chia thành nhiều buckets, và mỗi bucket sẽ chứa một số "fingerprints" biểu diễn các phần tử nằm trong tập bucket đó. Khi một phần tử được thêm vào tập, "fingerprint" của phần tử đấy sẽ được tính và thêm vào một bucket. Nếu bucket đó đầy thì phần tử đó sẽ được thêm vào bucket khác sử dụng một trong những hàm hash để tính toán lại. Quá trình này sẽ được lặp lại cho đến khi tìm thấy bucket phù hợp để thêm phần tử hoặc cho đến khi kết luận rằng tất cả bucket đều đã đầy.
    - Để kiểm tra một phần tử có thuộc tập không, ta tính "fingerprint" của phần tử tương ứng đấy rồi kiểm tra ở các buckets trong cuckoo filter. Nếu nó được tìm thấy ở bất kì bucket ta kết luận có thể phần tử nằm trong tập. Ngược lại thì kết luận không có. 

- **Count Min Sketch**
    - Count Min Sketch là một cấu trúc dữ liệu theo xác suất được sử dụng để ước tính tần số xuất hiện của các phần tử trong dữ liệu. 
    - Cấu trúc dữ liệu này được cài đặt bằng việc duy trì một mảng hai chiều để đếm, trong đó một chiều sẽ tương ứng với các phần tử nằm trong dữ liệu và chiều còn lại sẽ tương ứng với tập các hàm hash. Khi bắt gặp một phần tử, phần tử tương ứng trong ma trận sẽ được tăng lên bằng cách sử dụng hàm hash. Kết quả mà bảng trả về là ước tính xấp xỉ số tần suất xuất hiện của các phần tử trong dữ liệu, nhưng bởi đây là một cấu trúc dữ liệu dựa trên xác suất thống kê do đó việc tính toán không thể luôn luôn đưa ra con số chính xác được.

- **HyperLogLog**
    - HyperLogLog là một cấu trúc dữ liệu xác suất dùng để ước tính số lượng phần tử khác nhau có trong tập. 
    - HyperLogLog sử dụng $m$ hàm hash và $m$ bucket riêng biệt. Và với mỗi bucket nó sẽ ước tính số lượng số số không dẫn đầu nhiều nhất khi mã hóa phần tử thành bit tương ứng với bucket đấy. 
    - Đầu tiên các phần tử sẽ được cho qua các hàm hash thành một dãy bit gồm $k$ bit trong đó sẽ lấy $p$ bit cuối ($2^p=m$) để xét xem sẽ cập nhật phần tử này vào bucket nào và $k - p$ bit đầu sẽ được dùng để tìm số lượng bit 0 dẫn nhiều dài nhất để cập nhật vào bucket tương ứng. 
    - Cuối cùng nếu kết quả ước tính được số lượng số 0 dẫn đầu nhiều nhất đối với $m$ bucket là $\{R_1, R_2, ..., R_m\}$ thì kết quả số phần tử khác nhau thu được sẽ là: $constant \times m \times 2^{\frac{1}{m}(R_1 + R_2 + ... + R_m)}$. Trong đó ta có thể tra constant phù hợp [ở đây](https://en.wikipedia.org/wiki/HyperLogLog).

### **Trie** 

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

### **Hashing** 

- Điểm giống nhau: Crypto & Non-crypto đều là các hàm băm ánh xạ từ một dữ liệu với chiều dài bất kì sang chiều dài cố định. 
- Điểm khác nhau: 
    - Vấn đề bảo mật: 
        - Crypto hash function được thiết kế nhằm đảm bảo không tồn tại hai dữ liệu đầu vào nào cho ra cùng một giá trị đầu ra, và ta cũng không thể khôi phục lại dữ liệu đầu vào từ dữ liệu đầu ra. 
        - Non-crypto hash function không đảm bảo rằng hai dữ liệu đầu vào cho ra hai giá trị đầu ra khác nhau và ta cũng có thể khôi phục lại dữ liệu đầu vào từ đầu ra. 
    - Tốc độ: Thông thường crypto hash function có tốc độ chạy chậm hơn non-crypto bởi nó được thiết kế dành cho vấn đề bảo mật nên yêu cầu việc tính toán phức tạp, nhiều bước hơn. 
    - Ứng dụng: Crypto hash function thường được ứng dụng vào các việc yêu cầu tính bảo mật cao như mật khẩu, đảm bảo tính bảo mật cơ sở dữ liệu, chữ ký điện tử, ... Còn Non-crypto hash function có tốc độ tính toán nhanh hơn nhưng có thể xảy ra các trường hợp đụng độ do đó thường được sử dụng trong các cấu trúc dữ liệu tìm kiếm, nén dữ liệu. 
- Benchmark MD5 và xxhash trên java: 

<p align="center">
    <image src="./assets/md5.png" 
        width=80%/> 
    </br>
    Hash với MD5
</p>

<p align="center">
    <image src="./assets/xxhash.png" 
        width=80%/> 
    </br>
    Hash với XXHash
</p>

- Ta có thể thấy rõ rằng xxhash có tốc độ hash nhanh hơn md5 bởi đây là một hàm hash non-crypto còn md5 là một hàm hash crypto. 

## 3.1 Từ điển

- Ở bài tập này, từ điển được cài với các loại sau:
    - `SimpleDictionarySorted(UnSorted)`: Từ điển đơn giản với các từ được lưu thành list (không sắp xếp hoặc đã sắp xếp để tăng tốc tìm kiếm)
    - `HashDictionary`: Từ điển lưu các từ bằng hash table. 
    - `TrieDictionary`: Từ điển lưu các từ bằng cây Trie. 
    - `BloomFilterDictionary`: Từ điển lưu các từ bằng Bloom filter. 
- Để biên dịch chương trình dùng lệnh `make build`, chạy chương trình ta sử dụng lệnh `make run DICT=<dict> TYPE_DICT=<type_dict>` trong đó `<dict>` là đường dẫn đến file từ điển, `<type_dict>` là loại từ điển tương ứng như trên. 

## 3.1 Predictive text 

- Đối với bài tập này, trong phần cài đặt này chỉ đơn giản cài đặt cây Trie để lưu tập từ điển và sau đó sử dụng `dfs` trên cây Trie để tìm `k` từ có thứ tự từ điển nhỏ nhất mà khớp prefix với từ được nhập vào. 

## 3.3 Hash tables

- Hash table được cài đặt thủ công theo phương án xử lý collision là `Open Addressing`, nếu bảng băm đầy ta tiến hành tăng kích thước bảng và tái băm hoặc nếu số lượng phần tử trong bảng băm quá nhỏ so với kích thước thật ta cũng giảm kích thước và tái băm. Hàm hash ở đây cũng được sử dụng đơn giản là hàm hash tràn số. 

- Bảng băm cung cấp một số thao tác cơ bảng như `insert`, `delete` hay `search`. 

## 3.4 Hackerrank

<p align="center">
    <image src="./assets/hkr.png" 
        width=80%/> 
</p>
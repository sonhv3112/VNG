# Warm up

Họ và tên: Hồ Văn Sơn \
Domain: sonhv2 

# 0. Basic knowledge 

## 0.1. Source control
 
- SVN 

- Git 

## 0.2. Linux shell/bash script: 

- **ssh**
    - Mô tả: Viết tắt của "Secure Shell" hay "Secure Socket Shell", cho phép người dùng/client truy cập, điều khiển từ xa máy tính/server khác cho mục đích thực hiện các lệnh một cách an toàn. 
    - Cú pháp: `ssh [OPTION]... [user@]host[:port]`
    - Các `OPTION` phổ biến: 
        - a
    - ssh dựa trên kiến trúc client-server, và nó đảm bảo tính an toàn bảo mật khi trao đổi thông tin giữa client và host bằng việc authentication đầu tiên (nếu cần) và các thông tin trao đổi sẽ được mã hóa thông qua một số phương pháp mã hóa như:   
        - Symmetrical encryption: ...
        - Asymmetrical encrption: ...
        - Hashing: ...
- **scp** 
    - Mô tả: Sao chép file giữa các host thông qua internet. scp sử dụng ssh để trao đổi các file do đó nó bước authentication và các phương thức bảo mật đều tương tự với ssh.
    - Cú pháp: `scp [OPTION]... source target`. Trong đó: `source` và `target` đều phải chỉ ra một file cụ thể của host tương ứng, có dạng `[user@]source_host[:port][/path]`.
    - Các `OPTION` phổ biến: 
        - a
- **mv** 
    - Mô tả: Di chuyển hoặc đổi tên file tại máy local. 
    - Cú pháp: 
        - `mv [OPTION]... [-T] SOURCE DEST`: di chuyển file `SOURCE` sang `DEST`, nếu `SOURCE` và `DEST` nằm cùng một thư mục thì tương ứng thao tác đổi tên. 
        - `mv [OPTION]... SOURCE... DIRECTORY` hoặc \
        `mv [OPTION]... [-t] DIRECTORY SOURCE`: di chuyển các file `SOURCE` vào thư mục `DIRECTORY`, nếu các file `SOURCE` nằm cùng một thư mục `DIRECTORY` thì tương ứng thao tác đổi tên các file đấy. 
    - Các `OPTION` phổ biến: 
        - a
- **rm** 
    - Mô tả: Xóa file hoặc thư mục.  
    - Cú pháp: `rm [OPTION]... [FILE]...`. 
    - Các `OPTION` phổ biến: 
        - `-f, --force`: Bỏ qua các đối số khác, không bao giờ thất bại. 
        - `-r, -R, --recursive`: Thực hiện xóa tất cả file, thư mục con nằm trong thư mục và cuối cùng là xóa thư mục theo phương pháp đệ quy. 
- **cp** 
    - Mô tả: Sao chép file, thư mục.
    - Cú pháp: 
        - `cp [OPTION]... [-T] SOURCE DEST`: sao chép file `SOURCE` sang `DEST`. 
        - `cp [OPTION]... SOURCE... DIRECTORY` hoặc \
        `cp [OPTION]... [-t] DIRECTORY SOURCE`: sao chép các file `SOURCE` vào thư mục `DIRECTORY`.
    - Các `OPTION` phổ biến: 
        - a
- **telnet**
    - Mô tả: 
    - Cú pháp: ``
    - Các `OPTION` phổ biến: 
        - a 
- **netstat**
    - Mô tả: Liệt kê danh sách các kết nối TCP/IP tĩnh hoặc hiện tại đang chạy.
    - Cú pháp: `netstat` 
    - Các `OPTION` phổ biến: 
        - `-a`: Liệt kê tất cả các kết nối hiện tại và các port đang lắng nghe. 
- **kill process**
    - Mô tả: Gửi tín hiệu đến một process. 
    - Cú pháp: `kill` 
    - Các `OPTION` phổ biến: 
        - a

## 0.3. OOP 

- **Encapsulation** (tính đóng gói)
    - Các thuộc tính của đối tượng mang tầm vực private hoặc protected để hạn chế truy xuất, các phương thức cung cấp tính năng cho bên ngoài thì mang tầm vực public. 
    - Tell don't ask principle: Thông tin của đối tượng hạn chế được truy xuất, chỉnh sửa từ bên ngoài (trừ trường hợp bất khả kháng thì có thể truy xuất qua getter, setter). Đối tượng nắm giữ thông tin nào thì đối tượng đó có trách nhiệm phải xử lý các tác vụ, chức năng tương ứng đối với các dữ liệu mà chúng nắm giữ. 
- **Inheritance** (tính kế thừa): 
    - Lớp kế thừa có thể kế thừa tất cả thuộc tính (attributes), phương thức (methods), ..., trừ những phương thức mà lớp kế thừa tự định nghĩa lại nếu trong tầm vực từ lớp cơ sở để tái sử dụng. 
- **Polymorphism** (tính đa hình): 
    - Tính đa hình là khả năng tùy biến của phương thức, đối tượng  được thể hiện ở việc cung cấp một interface cho nhiều đối tượng có các kiểu khác nhau hoặc trong một lớp có nhiều phương thức cùng một tên nhưng nhiều tham số.
    - Các kiểu đa hình: 
        - Compile time polymorphism bao gồm Function overloading (quá tải hàm) và Operator overloading (quá tải toán tử). Trong đó quá tải hàm cho phép ta cài đặt nhiều phương thức với tên gọi giống nhau nhưng với các tham số truyền vào là khác nhau, còn quá tải toán tử cho phép ta cài đặt lại các toán tử như +, -, *, / (tuy nhiên trong Java thì không hỗ trợ điều này). 
        - Runtime polymorphism (Subtype): Nó sử dụng kiểu lớp cơ sở hoặc interface để tham chiếu đến các đối tượng có kiểu là lớp kế thừa mà kế thừa từ lớp cơ sở hoặc cài đặt interface. Tính đã hình của kiểu này sẽ diễn ra trong quá trình chạy của chương trình. Tức là trong quá trình thực thi chương trình sẽ xác định xem kiểu dữ liệu cụ thể của biến có kiểu dữ liệu là lớp cơ sở hoặc interface đó đang tham chiếu đến vùng có kiểu dữ liệu là gì. 
- **Abstraction** (tính trừu tượng): 
    - Tính trừu tượng được thể hiện qua việc ẩn đi một số thông tin không cần thiết của đối tượng (thuộc tính, cách mà các phương thức hoạt động) để bên ngoài có thể dễ dàng sử dụng các tính năng mà đối tượng cung cấp. 
    - Được cài đặt thông qua lớp trừu tượng hoặc interface (trong Java), bên cạnh đó việc cài đặt tường minh các phương thức có sẵn có thể được thực hiện tại lớp trừu tượng hoặc bỏ ngõ để lớp kế thừa cài đặt sau. 

## 0.4. Algorithm 

- **Linked List** 
    - Mỗi phần tử trong list là một node chứa thông tin mà nó nắm giữ và liên kết đến node tiếp theo 
    - Độ phức tạp các thao tác (với $n$ là chiều dài danh sách):
        - Các thao tác (chèn, sửa, xóa, lấy giá trị) với phần tử đầu, cuối danh sách: $O(1)$
        - Các thao tác (chèn, sửa, xóa, lấy giá trị) với phần tử không phải là đầu, cuối: $O(n)$
        - Tìm kiếm phần tử: $O(n)$ 
- **Binary Tree** (Cây nhị phân)
    - Là một cây mà mỗi node sẽ có tối đa hai node con là node con trái và node con phải. 
    - Ví dụ ta có cách cài đặt một node đơn giản của cây nhị phân tìm kiếm trên Java như sau: 
    ```java
    class Node { 
        int value; 
        Node left, right; 

        Node() { 
            left = right = null; 
        }
    }
    ```
- **Binary Search Tree** (Cây nhị phân tìm kiếm) 
    - Tương tự cây nhị phân, tuy nhiên giá trị tại một node sẽ lớn hơn hoặc bằng tất cả giá trị ở cây con có gốc là node con trái và bé hơn hoặc bằng tất cả giá trị ở cây con có gốc là node con phải. 
    - Các thao tác thường gặp trên cây nhị phân tìm kiếm: Chèn, sửa, xóa: $O(n)$ cho trường hợp xấu nhất, với $n$ là số node của cây nhị phân tìm kiếm. 
    - Để cải tiến tốc độ các thao tác trên cây nhị phân tìm kiếm thì có những cây cải tiến như AVL Tree, Red Black Tree, ... để giảm độ phức tạp thời gian của các thao tác xuống còn $O(log_2(n))$, tuy nhiên đều này cũng tương đương với việc cài đặt của các cây cải tiến này có phần phức tạp hơn Binary Search Tree. 
- **Quick Sort** 
    - Ý tưởng thuật toán: Thuật toán Quick Sort có ý tưởng dựa trên giải thuật chia để trị, được cài đệ quy như sau: 
        - Nếu dãy có ít hơn hai phần tử thì không làm gì. 
        - Ngược lại, chọn một giá trị pivot thuộc dãy, sau đó phân dãy 
    - Ta có đoạn psuedo code phía dưới mô tả lại một cách cài đặt thuật toán Quick Sort để sắp xếp tăng dần dãy con liên tiếp từ l đến r của dãy A. 
        ```psuedo
        // brief:   Sort subarray A[l..r] (ascending)
        // input:   Array A 
        //          l, r: index of first and end element subarray 
        QuickSort(A[], l, r) { 
            if (l == r) 
                return; 

            i = l, j = r; 
            pivot = A[random(l..r)]; // random pivot 

            // Move all element of A that less or equal to 
            // pivot to the left of A, another to the right 
            while (i <= j) { 
                while (A[i] < pivot) 
                    ++i;
                while (A[j] > pivot) 
                    --j;
                if (i <= j) { 
                    swap(A[i], A[j]); 
                    ++i; --j; 
                }
            }

            // Sort the left of array 
            if (i < r)
                QuickSort(A[], i, r); 

            // Sort the right of array 
            if (l < j)
                QuickSort(A[], l, j); 
        }
        ``` 
    - Độ phức tạp thời gian trung bình: $O(nlog_2(n))$. Ta thấy tại mỗi bước, giá trị $pivot$ sẽ được chọn nhưng đều được chọn một cách ngẫu nhiên, do đó nếu $pivot$ luôn giá trị bé nhất hoặc lớn nhất của dãy con mà hàm đệ quy gọi thì, trường hợp xấu nhất xảy ra với độ phức tạp thời gian xấu nhất là $O(n^2)$. Tuy nhiên trên thực tế, ta khó có thể bắt gặp trường hợp xấu nhất này, bên cạnh đó Quick Sort thường là thuật toán chạy tốt hơn so với các thuật toán sắp xếp còn lại và việc cài đặt cũng đơn giản, nên được cài đặt là thuật toán sắp xếp tại nhiều thư viện của nhiều ngôn ngữ khác nhau. 
- **Merge Sort** 
    - Ý tưởng thuật toán: Thuật toán Merge Sort có ý tưởng dựa trên giải thuật chia để trị, thông thường thuật toán sẽ được cài đặt theo hướng đệ quy như sau: 
        - Nếu dãy có ít hơn hai phần tử thì không làm gì. 
        - Ngược lại, chia dãy thành hai dãy con liên tiếp trong đó một dãy con là tiền tố và một dãy là hậu tố sao cho độ dài của hai dãy này chênh lệch không quá 1. Ta gọi đệ quy Merge Sort cho hai dãy con này, sau đó trộn hai dãy con đã được sắp xếp này thành một dãy tăng dần. 
    - Ta có đoạn psuedo code phía dưới mô tả lại một cách cài đặt thuật toán Merge Sort để sắp xếp tăng dần dãy con liên tiếp từ l đến r của dãy A. 
    ```
    // brief:   Merge to array A, B 
    // input:   Sorted array A, B 
    //          n, m: length of array A, B
    // return:  Sorted array C contain all element of A and B 
    Merge(A[], n, B[], m) { 
        C = []; 
        i = 0, j = 0; 
        while (i < n && j < m) { 
            if (i == n || j == m) { 
                if (j == m) { 
                    C.add(A[i]); // add A[i] to the end of C  
                    ++i; 
                } else { 
                    C.add(B[j]); // add B[j] to the end of C  
                    ++j; 
                }
            } else { 
                if (A[i] <= A[j]) { 
                    C.add(A[i]); // add A[i] to the end of C  
                    ++i; 
                } else { 
                    C.add(B[j]); // add B[j] to the end of C  
                    ++j; 
                }
            }
        }
        return C;
    }

    // brief:   Sort subarray A[l..r] (ascending)
    // input:   Array A 
    //          l, r: index of first and end element subarray 
    MergeSort(A[], l, r) { 
        if (l == r) 
            return; 

        mid = (l + r) / 2;
        MergeSort(A[], l, mid);
        MergeSort(A[], mid + 1, r); 
        A[l..r] = Merge(A[l..mid], mid - l + 1, 
                        A[mid + 1..r], r - mid); 
    }
    ```
    - Độ phức tạp thời gian: $O(nlog_2(n))$. Merge Sort là thuật toán có tốc độ chạy ổn định nhất trong các thuật toán sắp xếp, trong trường hợp xấu nhất nó vẫn có độ phức tạp về thời gian vẫn là $O(nlog_2(n))$. Tuy nhiên ta thấy rằng thuật toán yêu cầu thêm bộ nhớ cho mảng $C$ trong mỗi lần merge do đó điều này cũng gây ra điểm bất lợi cho thuật toán Merge Sort. 

## 0.5. HTTP 

HTTP (Hypertext Transfer Protocol) là một giao thức tại tầng application dựa trên mô hình request/response, kiến trúc client/server. HTTP giao tiếp thông qua TCP/IP, do đó nó sẽ tạo một kết nối tin cậy tại mỗi session và một session sẽ là chuỗi các message được truyền giữa server và client.

- HTTP 1.0: Có nhiều bước cải tiến so với phiên bản tiền nhiệm trước đó là HTTP 0.9.
    - Header: Cung cấp thêm chi tiết các thông tin cần thiết như: Kiểu dữ liệu mà client chấp nhận, kiểu dữ liệu của content được truyền (content-type), thông tin loại server, user-agent, status code để xác định trạng thái của quá trình thực hiện. 
    - Cung cấp một số phương thức mới ngoài GET còn có HEAD (dùng để hỏi thông tin của một dữ liệu, document; nhanh hơn phương thức GET), POST (đẩy dữ liệu đến server). 

- HTTP 1.1: 
    - Cung cấp một số tính năng mới nhằm tối ưu hiệu năng, tốc độ xử lý như: Persitent, Piplined connection (cho phép thực hiện nhiều request/response trong một connection).
    - Cung cấp chunked transfer encoding, content negotiation, virtual hosting (một server với một địa chỉ IP có thể host nhiều domain khác nhau). 
    - Hỗ trợ cache, giúp tăng tốc độ phản hồi .
    - Một số phương thức mới: PUT, DELETE, TRACE, OPTIONS
- HTTP 2.0
    - Request multiplexing: Cho phép thực hiện nhiều request/response cùng một lúc tại một kết nối với cơ chế bất đồng bộ. 
    - Request prioritization: Cho phép đặt mức độ ưu tiên của các request để xác định được những request nào ưu tiên được response sớm hơn. 
    - Connection reset: Cơ chế giúp client hoặc server đóng kết nối giữa chúng và thực hiện việc kết nối trở lại ngay lập tức. 
    - Server push: Server sẽ dự đoán những tài nguyên nào sẽ được request trong thời gian sắp tới, từ đó có thể cache lại và response cho client nhanh hơn. Điều này làm giảm việc có nhiều request chưa được xử lý trong hàng đợi. 
- HTTP 3.0
    - Sử dụng QUIC (Quick UDP Internet Connection) là một giao thức tại tầng transport, nó cung cấp native multiplexing, do đó các gói tin bị mất sẽ chỉ ảnh hưởng đến luồng mà gói tin đó bị mất. 
    - Cung cấp cơ chế mã hóa thích hợp, kết nối tại HTTP 3.0 luôn là kết nối được mã hóa, tương ứng với HTTPS tại HTTP 2.0. 

## 0.6. Design pattern 

### 0.6.1. Singleton (Creature Patterns)

- `Singleton` đảm bảo rằng lớp chỉ tạo ra một instance duy nhất và cung cấp bên ngoài một điểm truy cập toàn cục để có thể truy cập đến instance này. 

<p align="center">
    <image src="./assets/singleton.png" 
        height = 250px/> 
    </br>
    Cấu trúc của Singleton Pattern (Nguồn: 
    <a href="https://refactoring.guru/design-patterns/singleton">
        Refactoring Guru
    </a>
    )
</p>

- Việc cài đặt lớp theo Singleton Pattern thường sẽ gồm 2 bước sau: 
    - Đặt tầm vực của phương thức khởi tạo thành `private` để tránh việc bên ngoài có thể sử dụng `new` để tạo đối tượng Singleton. 
    - Tạo một phương thức tĩnh để bên ngoài có thể lấy instance của đối tượng Singleton, tại phương thức này sẽ cài đặt gọi phương thức khởi tạo `private` để tạo instance (nếu nó chưa được khởi tạo) và lưu instance đấy là một thuộc tính tĩnh của lớp. 
- Dưới đây là một cách cài đặt lớp với kiểu Singleton Pattern bằng Java: 
    ```java
    class Singleton { 
        private static Singleton _instance = null; 
        private Singleton() {} 
        public static Singleton getInstance() { 
            if (_instance == null) 
                _instance = new Singleton(); 
            return _instance; 
        }
    }
    ```
- Tuy nhiên ta có thể thấy rằng cách cài đặt trên chỉ hoạt động đúng đắn tại single-thread và nó sẽ gặp vấn đề đối với multi-thread. Bởi với các cài đặt đơn giản như phía trên không thể đảm bảo rằng chỉ có một instance được tạo ra, giá trị của instance có thể thay đổi nhiều lần khi nhiều thread cùng gọi `getInstance` một lúc. 
- Do đó, ta có thể suy nghĩ đến một phương án giải quyết đó là tại mỗi thời điểm chỉ chó phép một thread có thể truy cập đến hàm `getInstance`. Điều này có thể được thực hiện bằng cách sử dụng từ khóa `synchronized` trong Java. Ta sẽ thay đổi cách cài đặt phương thức tĩnh `getInstance` như sau: 
    ```java 
        public static synchronized Singleton getInstance() { 
            if (_instance == null) 
                _instance = new Singleton(); 
            return _instance; 
        }
    ```
- Đoạn code trên đã giải quyết phần nào vấn đề, nhưng giờ đây ta có một vấn đề khác đó là thực tế chỉ cần lock các phương thức trong khi instance chưa được khởi tạo, còn những lần sau đó thì ta không cần lock `getInstance` nữa. Chính vì điều này, nếu ta sử dụng đoạn code trên thì hiệu năng có thể giảm đến 100 lần hoặc hơn thế . Ta còn có thể tiếp tục cải tiến lớp Singleton trên để cải thiện tốc độ như sau: 
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
    ```
- Lợi ích:
    - Mẫu Singleton đảm bảo được rằng chỉ có một instance được tạo ra trong quá trình chương trình thực thi. 
    - Chỉ khởi tạo instance một lần duy nhất trong lần đầu gọi nó, do đó giúp tăng tốc việc xử lý. 
- Hạn chế: 
    - Mẫu Singleton vi phạm tính Single Responsibility, khi nó vừa quản lý cách để tạo instance và cả vòng đời của chúng. 

### 0.6.2. Composite (Structural Pattern) 

- `Composite` cho phép bạn tổ chức các đối tượng thành một cấu trúc dạng cây và xử lý với các đối tượng trên cây này như là những đối tượng riêng biệt. 

<p align="center">
    <image src="./assets/composite.png" 
        height = 400/> 
    </br>
    Cấu trúc của Composite Pattern (Nguồn: 
    <a href="https://refactoring.guru/design-patterns/composite">
        Refactoring Guru
    </a>
    )
</p>

- Mô tả: 
    - Lớp `Component` (thường là interface hoặc abstract class) mô tả chung các đối tượng trên cây (cả đối tượng đơn giản và phức tạp). 
    - Lớp `Leaf` biểu thị cho các đối tượng đơn giản trên cây, không chứa các đối tượng con nào bên trong nó. 
    - Lớp `Composite` hay là một container, mô tả một lớp phức tạp trên cây. Lớp này có thể chứa nhiều đối tượng con bên trong nó (hoặc là lớp `Leaf` hoặc `Composite` khác), tuy nhiên nó không cần biết kiểu dữ liệu cụ thể của các đối tượng con thật sự là gì mà nó sẽ thực hiện các thao tác xử lý thông qua interface `Component`.  

- Lợi ích: 
    - Dễ dàng làm việc với các mô hình có cấu trúc dạng cây, tận dụng tính đa hình và đệ quy để xử lý các tác vụ. 
    - Tuân thủ Open/Closed Principle khi có thể mở rộng thêm các đối tượng trong mô hình, cấu trúc dạng cây mà không cần phải chỉnh sửa lại cấu trúc cũ. 
- Khó khăn trong việc khái quát hóa lớp `Component` bởi nó cần khái quát được tất cả các đối tượng của mô hình, bao gồm cả các lớp đơn giản đến phức tạp.

### 0.6.3. Factory Method 
### 0.6.4. Abstract Factory 
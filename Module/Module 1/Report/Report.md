# Warm up

Họ và tên: Hồ Văn Sơn \
Domain: sonhv2 

---------------------------------------

# 0. Basic knowledge 

## 0.1. Source control
 
- SVN 
    - Subversion là một phần mềm mã nguồn mở dùng để quản lý và kiểm tra các phiên bản mã nguồn khác nhau trong quá trình phát triển phần mềm. Subversion cũng còn được gọi là SVN. SVN là lệnh dùng để thực hiện các chức năng Subversion trong môi trường thi hành lệnh trên các máy vi tính.
    - Tính năng mà SVN cung cấp: 
        - Subversion là kiểu kho lưu trữ trung tâm (khác với kho lưu trữ phân tán). 
        - Cung cấp các tính năng cần thiết để nhiều người/nhiều thiết bị có thể cùng tham gia phát triển/chỉnh sửa  nội dung một cách an toàn, đơn giản và nhanh chóng. 
        - Quản lý, lưu trữ thư mục và tập tin với đầy đủ các lịch sử sửa đổi trên hệ thống (user tham gia, thêm, sửa đổi những tập tin, thư mục nào, như thế nào, ...). 
        - Lưu lại các version để có thể roll back version cũ bất cứ lúc nào cần.  
        - Cho phép khóa một số tệp để không bị merge.
        - ...
- Git 
    - Git là một hệ thống quản lý mã nguồn phân tán cho phép theo dõi sự thay đổi của bất kì tập file/folder nào. Git cung cấp cho người dùng một giải pháp đơn giản để theo dõi các version của project (sự thay đổi, cập nhật) để nhiều người dùng có thể dễ dàng làm việc với project hơn. 
    - Một số lệnh thường thấy khi sử dụng Git: 
        - `git init`: Tạo một Git repository trên thư mục hiện tại ở máy local. 
        - `git clone <repository url>`: Tải về version cuối cùng của một repository xuống máy local, có thể thêm option `-b` để xác định rõ branch muốn tải xuống. 
        - `git fetch`: Kiểm tra các cập nhật tại repository so với phiên bản tại local. 
        - `git checkout`: Chuyển đổi làm việc giữa các branch trên máy local, hoặc tạo branch mới với option `-b`. 
        - `git add <file path>`: Thêm, chỉnh sửa các file vào local repo nằm trong đường dẫn tương ứng.
        - `git commit -m "<message>"`: Ghi lại các thay đổi hiện tại so với các file trong repository trước đó. Mỗi commit sẽ có một mã hash SHA1 tương ứng để theo dõi sự thay đổi tương ứng với mỗi lần commit. 
        - `git push`: Cập nhật những thay đổi từ máy local đến remote branch. 
        - `git diff`: Kiểm tra những sự thay đổi mới trên nhánh hiện tại chưa được thực hiện. 
        - `git merge`: Kết hợp các nhánh lại với nhau
        - `git pull`: Hợp nhất remote repo với local, gần như tương đương với lệnh fetch và merge. 
        - ...

## 0.2. Linux shell/bash script: 

- **ssh**
    - Mô tả: Viết tắt của "Secure Shell" hay "Secure Socket Shell", cho phép người dùng/client truy cập, điều khiển từ xa máy tính/server khác cho mục đích thực hiện các lệnh một cách an toàn. 
    - Cú pháp: `ssh [OPTION]... [user@]host[:port]`
    <!-- - Các `OPTION` phổ biến: 
        - a -->
    - ssh dựa trên kiến trúc client-server, và nó đảm bảo tính an toàn bảo mật khi trao đổi thông tin giữa client và host bằng việc authentication đầu tiên (nếu cần) và các thông tin trao đổi sẽ được mã hóa thông qua một số phương pháp mã hóa như: Symmetrical encryption, Asymmetrical encrption, Hashing,...
- **scp** 
    - Mô tả: Sao chép file giữa các host thông qua internet. scp sử dụng ssh để trao đổi các file do đó nó bước authentication và các phương thức bảo mật đều tương tự với ssh.
    - Cú pháp: `scp [OPTION]... source target`. Trong đó: `source` và `target` đều phải chỉ ra một file cụ thể của host tương ứng, có dạng `[user@]source_host[:port][/path]`.
    <!-- - Các `OPTION` phổ biến: 
        - a -->
- **mv** 
    - Mô tả: Di chuyển hoặc đổi tên file tại máy local. 
    - Cú pháp: 
        - `mv [OPTION]... [-T] SOURCE DEST`: di chuyển file `SOURCE` sang `DEST`, nếu `SOURCE` và `DEST` nằm cùng một thư mục thì tương ứng thao tác đổi tên. 
        - `mv [OPTION]... SOURCE... DIRECTORY` hoặc \
        `mv [OPTION]... [-t] DIRECTORY SOURCE`: di chuyển các file `SOURCE` vào thư mục `DIRECTORY`, nếu các file `SOURCE` nằm cùng một thư mục `DIRECTORY` thì tương ứng thao tác đổi tên các file đấy. 
    <!-- - Các `OPTION` phổ biến: 
        - a -->
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
    <!-- - Các `OPTION` phổ biến: 
        - a -->
- **telnet**
    - Mô tả: Giúp giao tiếp với host khác thông qua giao thức TELNET.
    - Cú pháp: `telnet <host>`
    <!-- - Các `OPTION` phổ biến: 
        - a  -->
- **netstat**
    - Mô tả: Liệt kê danh sách các kết nối TCP/IP tĩnh hoặc hiện tại đang chạy.
    - Cú pháp: `netstat` 
    - Các `OPTION` phổ biến: 
        - `-a`: Liệt kê tất cả các kết nối hiện tại và các port đang lắng nghe. 
- **kill process**
    - Mô tả: Gửi tín hiệu đến một process. 
    - Cú pháp: `kill` 
    - Các `OPTION` phổ biến: 
        - `-l`: Liệt kê các tín hiệu có thể truyền đến một process có thể sử dụng. 
    - Thông thường để kill một process thông qua pid (port) thì ta sử dụng lệnh: `kill -9 pid` hoặc `killall -9 appname` đối với tên của process đấy. Ở đây option -9 tương ứng với tín hiệu SIGKILL.

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

- `Factory Method` cung cấp một interface mô tả các đối tượng có khả năng sinh ra các đối tượng product một cách cụ thể, hay nói cách khác nó giúp tạo ra một đối tượng mà không cần thiết chỉ ra rằng chính xác lớp nào được tạo ra từ lớp nào. 

<p align="center">
    <image src="./assets/factory_method.png" 
        width=80%/> 
    </br>
    Cấu trúc của Factory Method (Nguồn: 
    <a href="https://refactoring.guru/design-patterns/composite">
        Refactoring Guru
    </a>
    )
</p>

- Mô tả: 
    - `Product` cung cấp interface cho các đối tượng `ConcreteProduct` sẽ được `Factory` tạo ra.
    - `Creator` (thường là interface hoặc abstract class) cung cấp phương thức để tạo ra các đối tượng `Product`. 
    - Lớp `ConcreteCreator` là các lớp kế thừa `Creator` cung cấp các phương thức để tạo ra các đối tượng `Product` cụ thể.
- Lợi ích: 
    - Xóa bỏ được sự ràng buộc giữa một creator và các product. 
    - Đảm bảo tính Single Responsibility Principle, bởi một creator thường chỉ tạo ra một loại `Product` cụ thể. 
    - Đảm bảo tính Open/Closed Principle, với việc có thể mở rộng thêm các loại `Product` khác mà không cần phá vỡ cấu trúc cũ. 
- Khó khăn: Việc cài đặt trở nên phức tạp bởi chia nhiều lớp con tương ứng với từng loại `Product`. 

### 0.6.4. Abstract Factory 

- `Abstract Factory` cung cấp giải pháp để đóng gói một nhóm các "nhà máy" cụ thể có điểm chung mà không cần chỉ ra `Product` cụ thể mà nó tạo ra.

<p align="center">
    <image src="./assets/abstract_factory.png" 
        width=80%/> 
    </br>
    Cấu trúc của Factory Method (Nguồn: 
    <a href="https://refactoring.guru/design-patterns/composite">
        Refactoring Guru
    </a>
    )
</p>

- Mô tả: 
    - `Abstract Product`: Mô tả các interface cho tập các product khác nhau nhưng có điểm chung. 
    - `Concrete Product`: Các đối tượng product cụ thể kế thừa từ `Abstract Product`. 
    - `Abstract Factory`: Cung cấp interface các "nhà máy" chứa các phương thức để tạo ra các abstract product. 
    - `Concrete Factory`: Các "nhà máy" cụ thể kế thừa từ `Abstract Factory` cung cấp các phương thức tạo ra các product (`Concrete Product`) tương ứng. 
- Lợi ích: 
    - Xóa bỏ được sự ràng buộc giữa một creator và các product. 
    - Đảm bảo tính Single Responsibility Principle, Open/Closed Principle. 
- Khó khăn: Việc cài đặt trở nên phức tạp bởi chia nhiều lớp con. 

## Bài tập Directory tree

### Class diagram

<p align="center">
    <image src="./assets/DirectoryTreeClassDiagram.png" 
        width=100%/> 
    </br>
    Class diagram của bài làm
</p>

### Mô tả: 

- Được cài đặt dựa trên pattern `Composite` và bên cạnh đó cũng sử dụng `Simple Factory` (không phải pattern).
- Lớp `ElementSize` mô tả kích thước một file/folder, bên trong có chứa enum `ElementSize.Type` gồm các kích thước cụ thể như B (Byte), K (Kilobytes), M (Megabytes), ... Lớp `ElementSize` cung cấp một số phương thức như cộng hai size, chuyển đổi đơn vị của size, parse `ElementSize` từ string, ...
- Lớp `AbstractElement` là một `Component` mô tả một đối tượng có thể có trong pattern `Composite`, và các lớp `DirectoryElement` (folder), `FileElement` (file) kế thừa từ lớp này. Các đối tượng trên cây phải cung cấp các phương thức như tính tổng kích thước của một file/folder, tìm kiếm file/folder trong folder (cây con với gốc chính đối tượng đó), thêm file/folder vào thư mục hiện tại, ...
- Lớp `ElementFactory` là một Simple factory để tạo ra các đối tượng file/folder tương ứng với tham số truyền vào.
- Lớp `DirectoryTreeParser` cho mục đích parse directory tree từ string hoặc từ file và trả về gốc của cây. 

### Biên dịch và chạy

- File Makefile được cung cấp nhằm mục đích sử dụng make để biên dịch và chạy project. Makefile cung cấp một số lệnh sau: 
    - `make build`: Biên dịch tất cả file mã nguồn có trong project vào thư mục Target. 
    - `make run`: Chạy chương trình từ Target. 
    - `make clean`: Xóa các file trong Target. 
    - `make`: Biên dịch, sau đó chạy chương trình.

---------------------------------------

# 1. Load balancer

## Khái niệm 

- Load balancing được sử dụng trong trường hợp có nhiều server cùng đảm nhiệm vai trò nhận request, lúc này load balancing là một giải pháp được dùng trong việc phân phối các truy cập từ client đến nhiều server.
- Từ đây, load balancer giúp phần vào việc cải thiện hiệu quả của tính phản hồi, khả dụng của server, cũng như làm giảm trường hợp server bị quá tải. 
- Load balancer sẽ được đặt giữa client và server, từ đó nó có thể nhận những request từ phía client và điều phối về server thích hợp để xử lý. 
- Một số thuật toán được sử dụng để load balancing như: 
    - Dynamic load balancing: Least connection, Weighted least connection, Weighted response time, Resource-based 
    - Static load balancing: Round robin, Weighted round robin, IP hash. 

## Kiến trúc NGINX

- NGINX sử dụng mô hình để có thể dự đoán việc sử dụng các tiến trình tương ứng với tài nguyên phần cứng được cung cấp. Mô hình sẽ bao gồm: 
    - Tiến trình `master` thực hiện việc chuyên biệt như đọc cấu hình và liên kết đến các port sau đó tạo một số process con để làm việc (bao gồm 3 loại dưới). 
    - Tiến trình `cache loader` được chạy lúc khởi động để tải bộ nhớ cache trên đĩa vào vùng nhớ. 
    - Tiến trình `cache manager` được lập lịch chạy theo định kỳ để loại bỏ một số mục trên cache nhằm đảm bảo đúng với kích thước đã định từ trước. 
    - Các tiến tình `woker` đảm nhiệm tất cả vai trò, như quản lý kết nối, đọc và ghi từ đĩa hay giao tiếp với upstream server. 
- Ở NGINX, các tiến trình đều là single-threaded và hoạt động một cách độc lập, nhận kết nối mới và xử lý với chúng. Do đó, các bước xử lý của một request từ phía client đều được thực hiện một cách tuần tự theo một quy trình có trước.

<p align="center">
    <image src="./assets/state_machine_nginx.png" 
        height=500/> 
    </br>
    State machine: Quy trình xử lý một request của NGINX (Nguồn: 
    <a href="https://www.nginx.com/blog/inside-nginx-how-we-designed-for-performance-scale/">
        NGINX
    </a>
    )
</p>

- NGINX sử dụng kiến trúc Non-blocking "Event-Driven" giúp cho mỗi `woker` có thể xử lý hàng trăm đến hàng nghìn kết nối cùng hoạt động tại một thời điểm, so với các kiến trúc blocking I/O thì mỗi `worker` chỉ có thê xử lý một kết nối hoạt động tại một thời điểm. Cụ thể, mỗi tiến trình sẽ có nhiều `worker connection`, các `worker connection` này đóng vai trò trong việc nhận kết nối, yêu cầu từ phía client và cung cấp đến cho `worker process` của nó để xử lý, các yêu cầu này đều được thực hiện theo một quy trình cho trước như đã mô tả ở trên, tuy nhiên việc thực hiện các yêu cầu sẽ được thực hiện một cách bất đồng bộ do đó tránh việc chặn các sự kiện khác. 

<p align="center">
    <image src="./assets/blocking.png" 
        width=400/> 
    <image src="./assets/non_blocking.png" 
        width=400/> 
    </br>
    Các hoạt động blocking I/O, non-blocking của NGINX  (Nguồn: 
    <a href="https://www.nginx.com/blog/inside-nginx-how-we-designed-for-performance-scale/">
        NGINX
    </a>
    )
</p>

- Bởi NGINX sử dụng kiến trúc non-blocking, event-drivent, cơ chế bất đồng bồ do đó việc sử dụng single-threaded là hợp lý, tránh lãng phí những thread còn lại nếu sử dụng multi-thread, và điều này cũng giúp cho việc tận dụng được hiệu năng của thread để xử lý request. Bên cạnh đó single-threaded khiến cho các request được xử lý theo một quy trình được định sẵn (state machine) như hình ở phần trên, từ đây giúp NGINX có thể dự đoán được thời gian response ổn định hơn và điều phối các request một cách tốt hơn. 

## Bài tập

### Cấu hình HTTP Server bằng Java 

- Sử dụng Maven repository `com.sun.net.httpserver` để cài đặt một HTTP Server đơn giản bằng Java. 
- Makefile cấu hình một số lệnh chạy đơn giản để build project và chạy server. Để sử dụng, lệnh `make` và `mvn` cần khả dụng trên môi trường muốn chạy:
    - `make build`: Sync maven, compile project. 
    - `make run PORT=<port>`: Chạy server với PORT tại `<port>`, mặc định là 9000. 
    - `make clean`: Xóa project.

### Cấu hình NGINX Load balancing

- Đầu tiên ta cần cấu hình lại file `nginx.conf` để NGINX có thể làm load balancing như sau: 

```
worker_processes auto;

events {

}

http {
    log_format upstreamlog '$server_name to: $upstream_addr {$request} '
    'upstream_response_time $upstream_response_time'
    ' request_time $request_time';

    upstream backend {
        server localhost:9000;
        server localhost:9001;
    }

    server {
        listen 80;
        server_name localhost;

        access_log /var/log/nginx/nginx-access.log upstreamlog;

        location / {
            proxy_pass "http://backend";
        }
    }
}
```

- Trong trường hợp này, hay server ở phía backend tại hai địa chỉ là `localhost:9000` và `localhost:9001` được chỉ định tương ứng ở phần `upstream backend`. Bên cạnh đó, cấu hình trên cũng cấu hình lại log lại phần truy cập vào server NGINX tại port 80 sẽ được phân phối vào server nào để tiện việc kiểm tra. Sau đó ta khởi động lại nginx bằng `sudo systemctl reload nginx` (UNIX). Theo cấu hình trên số worker process sẽ được cấu hình tự động và ở đây ta có là 8 worker processes. 


<p align="center">
    <image src="./assets/load_balancer_workprocesses.png" 
        width=100%/> 
</p>


- Tiếp theo ta start hai server phía backend tại port 9000 của localhost và port 9001 như sau: 

<p align="center">
    <image src="./assets/start_server_load_balancer.png" 
        width=100%/> 
</p>

- Cuối cùng ta thử truy cập `localhost` bằng web browser, và kiểm kết quả tại file đã cấu hình như phía trên `/var/log/nginx/nginx-access.log` như sau: 


<p align="center">
    <image src="./assets/log_load_balancer.png" 
        width=100%/> 
</p>

---------------------------------------

# 2. Caching

## Vai trò

- Caching được dựa trên ý tưởng dữ liệu phân cấp, nó là việc lưu trữ dữ liệu ở một vùng nhớ đặc biệt cho phép truy vấn dữ liệu ở đó mà không cần truy vấn tại nơi gốc chứa dữ liệu đó. Thông thường những dữ liệu thường xuyên được truy cập sẽ được cache lại, ví dụ như trên hệ điều hành thì caching sẽ lưu dữ liệu ở các tầng phân cấp (L1 Cache, L2 Cache, L3 Cache), tầng càng ở trên thì có tốc độ truy vấn càng nhanh nhưng kích thước lưu trữ càng bé. 
- Vai trò: Mục đích chính của việc caching là tăng tốc độ truy vấn các dữ liệu thường xuyên được truy cập, tùy theo lượng truy vấn đến dữ liệu mà dữ liệu đó sẽ được phân cấp một cách hợp lý để phù hợp với tốc độ truy vấn sau này. 

## Thuật toán LRU 

- Ý tưởng: Thuật toán LRU (Least Recently Used) Cache tổ chức các phần tử được cache theo thứ tự được truy xuất của chúng, điều này giúp xác định được phần tử nào đã được truy cập gần đây hoặc chưa được truy cập trong một thời gian dài trước đó. Từ đây, chiến thuật thay thế khi cache đầy của LRU là các phần tử được truy cập gần đây sẽ có khả năng truy cập trong tương lai gần nhiều hơn các phần tử không được truy cập trong một thời gian dài, do đó nó sẽ thay thế vào phần tử có thời gian chưa được tham chiếu đến lâu nhất trong cache. 
- Cài đặt: Thông thường thuật toán LRU Cache được cài đặt với hai cấu trúc dữ liệu để lưu trữ là: 
    - Doubly Linked List: Danh sách lưu trữ các phần tử trong cache. 
    - Hash map: Map ánh xạ giá trị các phần tử trong cache thành node tương ứng trong Doubly Linked List. 
    - Khi thực hiện thao tác truy vấn một phần tử: 
        - Nếu phần tử đó có sẵn trong Cache (kiểm tra bằng Hash map), ta sử dụng Hash map để tìm ra node tương ứng trên List và di chuyển nó đến cuối List (giá sử phần tử cuối cùng của danh sách là phần tử được truy cập gần đây đây, và giảm dần khi trở về ngược đầu danh sách).
        - Nếu phần tử chưa có sẵn trong Cache, ta xóa phần tử đầu tiên của List và giá trị trên Hash map tương ứng, sau đó thêm phần tử vào Cache ở cuối List và ánh xạ tương ứng trên Hash map. 
- Việc cài đặt bằng hai cấu trúc dữ liệu trên đảm bảo được tốc độ của các thao tác trên Cache như truy vấn phần tử trên Cache trong $O(1)$, loại bỏ phần tử không được sử dụng trong thời gian dài nhất trong $O(1)$ mà độ phức tạp về bộ nhớ vẫn là $O(n)$. Tuy nhiên ta có thể thấy được việc cài đặt bằng Hash map cũng đưa ra một số điểm bất lợi về độ phức tạp thời gian khi ta không thể đảm bảo rằng mọi truy vấn trên Hash map đạt được độ phức tạp thời gian trung bình là $O(1)$. 

## Thuật toán LFU 

- Ý tưởng: Thuật toán LFU (Least Frequently Used) Cache tổ chức các phần tử được cache và theo dõi theo số lần được truy xuất của chúng. Do đó chiến thuật thay thế khi cache đầy của LFU là thay thế ở phần tử có số lần truy xuất ít nhất trong Cache, nếu có nhiều phần tử có cùng số lần truy xuất thì sẽ thay thế phần tử chưa được truy xuất trong thời gian dài nhất (tương tự LRU). 
- Cài đặt: Thông thường thuật toán LFU được cài đặt với hai cấu trúc dữ liệu để lưu trữ là: 
    - Min-heap: Lưu trữ các phần tử trong cache theo số lần được truy xuất của chúng. 
    - Hash map: Map ánh xạ giá trị các phần tử trong cache thành node trên min-heap tương ứng. 
    - Khi thực hiện thao tác truy vấn một phần tử: 
        - Nếu phần tử đó có sẵn trong Cache (kiểm tra bằng Hash map), tăng số lần truy xuất của phần tử đó lên, sử dụng Hash map để tìm ra node tương ứng trên min-heap và thực hiện việc thao tác cập nhật lại subtree trên min-heap với gốc là node vừa được truy xuất đến. 
        - Nếu phần tử đó không có sẵn trong Cache, ta xóa phần tử đầu tiên của min-heap và thay thế bằng phần tử mới này, sau đó cập nhật lại min-heap.
- Việc cài đặt như trên đảm bảo các thao tác trên Cache tốn $O(log(n))$, tuy nhiên đối với thao tác khi phần tử không có sẵn trong Cache thì phần tử thêm mới vào sẽ có số lần truy cập bé nhất nên thao tác sẽ thường tốn $O(1)$.

## Cách hoạt động của caffein

- Caffein sử dụng `Window TinyLFU` để cài đặt cho chiến lược thay thế, nó cho được hiệu năng cao, tỷ lệ truy cập (HIT) cao và dung lượng bộ nhớ thấp. Cụ thể như sau: 
    - `W-TinyLFU` sử dụng một vùng nhớ nhỏ `LRU` để lưu các phần tử mới vừa được Cache (theo cơ chế tương tự LRU Cache). Khi các phần tử nằm trong vùng `Window Cache` này bị thay thế, nó sẽ được chuyển vào `TinyLFU` để kiểm tra đạt chuẩn bằng một thuật toán từ trước, nếu đạt chuẩn nó sẽ được chuyển sang `Main Cache`. Tại đây cache được chia thành hai vùng protected (thông thường là 80%) và probation, các phần tử mới được thêm vào vùng protected và nó sẽ đảm bảo rằng không bị thay thế ngay. Khi vùng nhớ protected đầy, một số phần tử sẽ được chuyển sang vùng nhớ probation và từ đây một số phần tử probation sẽ được loại ra và chuyển vào `TinyLFU` để tính toán. Việc làm trên sẽ lặp lại cho đến khi `Main Cache` trở lại với bộ nhớ tối đa mặc định.

    <p align="center">
        <image src="./assets/WTinyLFU.png" 
            width=90%/> 
        </br>
        Kiến trúc của Window TinyLFU 
        </br>
        (Nguồn: 
        <a href="https://www.researchgate.net/publication/351709350_Lightweight_Robust_Size_Aware_Cache_Management">
            Lightweight Robust Size Aware Cache Management
        </a>
        )
    </p>

    - `TinyLFU` sử dụng bảng phác thảo tần suất để có thể ước tính xác suất truy xuất là [CountMin Sketch](http://dimacs.rutgers.edu/~graham/pubs/papers/cmsoft.pdf). Phần cài đặt sử dụng [4-bit CountMinSketch](https://github.com/ben-manes/caffeine/blob/master/caffeine/src/main/java/com/github/benmanes/caffeine/cache/FrequencySketch.java).

## Bài tập 

- Ở bài này sử dụng lại file cấu hình NGINX ở bài tập trước, nhưng thêm phần static resources ở thư mục `/http/images`. Tại thư mục này, chứa file `image.png` để trang web có thể get mỗi khi được load lên từ trình duyệt web. Để static resources được cache lại, thêm cấu hình `expires <time>`, ví dụ như file cấu hình bên dưới là 30 ngày.

```            
worker_processes auto;

events {

}

http {
        log_format upstreamlog '$server_name to: $upstream_addr {$request} '
        'upstream_response_time $upstream_response_time'
        ' request_time $request_time';

        upstream backend {
                server localhost:9000;
                server localhost:9001;
        }

        server {
                listen 80;
                server_name localhost;
                access_log /var/log/nginx/nginx-access.log upstreamlog;

                location / {
                        proxy_pass "http://backend";
                }

                location /images/ {
                        root /http;
                        expires 30d;
                }
        }

}
```

- Dưới đây là hình minh họa cho trước và sau khi bật cache cho static resources.

<p align="center">
    <image src="./assets/caching_1.png" 
        width=100%/> 
    </br>
    Trước khi bật cache
</p>

<p align="center">
    <image src="./assets/caching_2.png" 
        width=100%/> 
    </br>
    Sau khi bật cache
</p>

- Ngoài ra cũng NGINX cũng cung cấp directive để cấu hình cache đơn giản là: `proxy_cache_path` và `proxy_cache`, trong đó: 
    - **proxy_cache_path**: Cấu hình đường dẫn cho folder cache và một số cấu hình khác của cache như: 
        - levels: Cấu trúc phân cấp, ví dụ như two-level thì sẽ là `levels=1:2`
        - key_zones: Tên và kích thước của `shared memory` cho phép để chứa các metadata về các dữ liệu đã được cache. Ví dụ `key_zones=my_cache:10m` thì tên của vùng shared memory sẽ là my_cache và kích thước của nó là 10MB. 
        - max_size: Kích thước tối đa của Cache. 
        - inactive: Thời lượng tối đa mà một phần tử có thể tồn tại ở trong Cache tính từ thời điểm truy cập lần cuối.
    - **proxy_cache**: Được thêm vào với `keys_zone` tương ứng với **proxy_cache_path** để kích hoạt cache.
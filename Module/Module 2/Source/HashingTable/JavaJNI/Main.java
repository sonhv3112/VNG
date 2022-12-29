// package JavaJNI; 

public class Main {
    public static void main(String[] args) { 
        HashTable ht = new HashTable(); 
        ht.insert("1234", "123"); 
        System.out.println(ht.search("124"));
    }
}

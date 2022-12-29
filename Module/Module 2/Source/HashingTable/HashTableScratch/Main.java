package HashTableScratch;

public class Main {
    public static void main(String[] args) { 
        HashTable ht = new HashTable(); 
        ht.insert("1234", "value 1");
        System.out.println(ht.search("1234"));
        ht.insert("123", "value 2"); 
        System.out.println(ht.search("123"));
        System.out.println(ht.search("111"));
    }
}

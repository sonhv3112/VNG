package HTScratch;

import HTScratch.HashTable;

public class Main {
    public static void main(String[] args) { 
        HashTable ht = new HashTable(); 
        ht.insert("1234", "ho van son");
        System.out.println(ht.search("1234"));
    }
}

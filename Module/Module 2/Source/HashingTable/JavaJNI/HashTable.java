// package JavaJNI; 

public class HashTable { 
    static { 
        System.loadLibrary("HashTable");;
    }

    private int size;
    private int count;
    private int base_size;
    private long items;

    HashTable() { 
        this.newHashTable();
    }

    protected void finalize() { 
        this.deleteHashTable(); 
        System.out.println("Destroy");
    }

    public native void insert(String key, String value); 
    public native String search(String key); 
    public native void delete(String key); 

    private native void newHashTable(); 
    private native void deleteHashTable(); 
}
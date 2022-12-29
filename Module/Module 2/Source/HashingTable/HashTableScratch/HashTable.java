package HashTableScratch;

public class HashTable {
    private static final int INITIAL_BASE = 617; 
    private static final int INITIAL_BASE_SIZE = 127; 
    private static final HashItem DELETED_ITEM = new HashItem(null, null); 

    int size; 
    int base; 
    int count; 
    HashItem[] items; 

    public HashTable() { 
        this.count = 0; 
        this.base = INITIAL_BASE; 
        this.size = INITIAL_BASE_SIZE; 
        this.items = new HashItem[this.size]; 
    }

    public HashTable(int size) { 
        this.count = 0; 
        this.base = INITIAL_BASE; 
        this.size = PrimeUtils.nextPrime(size); 
        this.items = new HashItem[this.size]; 
    }

    public HashTable(int size, int base) { 
        this.count = 0; 
        this.base = PrimeUtils.nextPrime(base); 
        this.size = PrimeUtils.nextPrime(size); 
        this.items = new HashItem[this.size]; 
    }

    private void resize(int size) { 
        size = PrimeUtils.nextPrime(size); 
        if (size < INITIAL_BASE_SIZE) 
            return; 

        HashItem[] olderItems = items; 
        this.items = new HashItem[size];  
        this.size = size; 
        for (HashItem hashItem : olderItems) { 
            if (hashItem == null || hashItem == DELETED_ITEM) 
                continue; 
            this.insert(hashItem.getKey(), hashItem.getValue()); 
        }
    }

    private void resizeUp() { 
        this.resize(this.size * 2); 
    }

    private void resizeDown() { 
        this.resize(this.size / 2);
    }

    public void insert(String key, String value) { 
        if (this.count == this.size) 
            this.resizeUp();

        int index = HashItem.getHash(key, this.base, this.size); 
        while (items[index] != null && items[index] != DELETED_ITEM) 
            index = (index + 1) % this.size; 
        items[index] = new HashItem(key, value); 
        ++this.count; 
    }

    public void delete(String key, String value) { 
        int index = HashItem.getHash(key, this.base, this.size); 
        while (items[index] != null) { 
            if (items[index] != DELETED_ITEM) { 
                if (items[index].getKey().equals(key)) { 
                    items[index] = DELETED_ITEM;
                    return; 
                }
            }
            index = (index + 1) % this.size; 
        }
        --this.count; 
        if (this.size / 2 >= this.count)
            this.resizeDown();
    }

    public String search(String key) { 
        int index = HashItem.getHash(key, this.base, this.size); 
        while (items[index] != null) { 
            if (items[index] != DELETED_ITEM) { 
                if (items[index].getKey().equals(key)) { 
                    return items[index].getValue(); 
                }
            }
            index = (index + 1) % this.size; 
        }
        return null; 
    }
}

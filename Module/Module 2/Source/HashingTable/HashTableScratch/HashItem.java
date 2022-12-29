package HashTableScratch; 

public class HashItem {
    private String key; 
    private String value; 

    public HashItem(String key, String value) { 
        this.key = key; 
        this.value = value; 
    }

    public String getKey() { 
        return this.key; 
    }

    public String getValue() { 
        return this.value; 
    }

    public static int getHash(String str, int a, int m) { 
        int hash = 0; 
        for (int i = 0; i < str.length(); ++i) { 
            hash += (int)(Math.pow(a, str.length() - 1 - i)) * (int)(str.charAt(i)); 
            hash %= m; 
        }
        return hash; 
    }
}

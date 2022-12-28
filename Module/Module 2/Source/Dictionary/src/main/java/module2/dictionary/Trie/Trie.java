package module2.dictionary.Trie;

public class Trie {
    TrieNode root; 

    public Trie() { 
        root = new TrieNode(); 
    }

    public void add(String str) { 
        TrieNode currentNode = root; 
        for (int i = 0; i < str.length(); ++i) { 
            char ch = str.charAt(i); 
            TrieNode nextNode = currentNode.getChild(ch); 
            if (nextNode == null) 
                currentNode = currentNode.addChild(ch); 
            else 
                currentNode = nextNode; 
        }
        currentNode.setEndWord(true);
    }

    public boolean contains(String str) { 
        TrieNode currentNode = root; 
        for (int i = 0; i < str.length(); ++i) { 
            char ch = str.charAt(i); 
            TrieNode nextNode = currentNode.getChild(ch); 
            if (nextNode == null) 
                return false; 
            currentNode = nextNode; 
        }
        return currentNode.isEndWord();  
    }
}

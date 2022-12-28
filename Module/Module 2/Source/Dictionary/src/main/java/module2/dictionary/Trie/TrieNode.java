package module2.dictionary.Trie;

import java.util.Map;
import java.util.HashMap;

public class TrieNode {
    private boolean endWord; 
    private Map<Character, TrieNode> children; 

    public TrieNode() { 
        this.children = new HashMap<Character, TrieNode>(); 
        this.endWord = false;
    }

    public TrieNode getChild(Character ch) { 
        if (children.containsKey(ch)) 
            return children.get(ch); 
        return null; 
    }

    public TrieNode addChild(Character ch) { 
        TrieNode newNode = new TrieNode(); 
        children.put(ch, newNode);  
        return newNode; 
    }

    public void setEndWord(boolean check) { 
        this.endWord = check; 
    }

    public boolean isEndWord() { 
        return this.endWord; 
    }
}

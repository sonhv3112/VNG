package PredictiveText.Trie;

import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TrieNode {
    private boolean endWord; 
    private Map<Character, TrieNode> children; 

    public TrieNode() { 
        this.children = new HashMap<>(); 
        this.endWord = false;
    }

    public TrieNode getChild(Character ch) { 
        if (children.containsKey(ch)) 
            return children.get(ch); 
        return null; 
    }

    /**
     * 
     * @param type 0 is sorted, 1 is unsorted
     * @return
     */
    public List<Character> getAllKeyChild(int type) { 
        List<Character> listKeys = new ArrayList<Character>(this.children.keySet()); 
        if (type == 1) 
            Collections.sort(listKeys); 
        return listKeys; 
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

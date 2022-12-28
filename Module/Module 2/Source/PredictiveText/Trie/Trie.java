package PredictiveText.Trie;

import java.util.ArrayList;
import java.util.List;

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

    private void searchKNeareastString(TrieNode node, int k, StringBuilder currentString, List<String> listString) { 
        if (node.isEndWord()) 
            listString.add(currentString.toString()); 
        if (listString.size() == k) 
            return; 
        List<Character> listChild = node.getAllKeyChild(0); 
        for (Character ch : listChild) { 
            currentString.append(ch); 
            searchKNeareastString(node.getChild(ch), k, currentString, listString);
            currentString.deleteCharAt(currentString.length() - 1); 
            if (listString.size() == k) 
                return; 
        }
    }

    public List<String> searchKNeareastString(String str, int k) { 
        List<String> kNeareastStrings = new ArrayList<>();
        TrieNode currentNode = root; 
        for (int i = 0; i < str.length(); ++i) { 
            char ch = str.charAt(i); 
            TrieNode nextNode = currentNode.getChild(ch); 
            if (nextNode == null) 
                return kNeareastStrings; 
            currentNode = nextNode; 
        }
        searchKNeareastString(currentNode, k, new StringBuilder(str), kNeareastStrings); 
        return kNeareastStrings; 
    }
}

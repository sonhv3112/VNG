package module2.dictionary.Dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import module2.dictionary.Utils.Tokenizer;

public class SimpleDictionary implements Dictionary {
    public enum Type { 
        Sorted, 
        UnSorted;
    }

    private List<String> tokens; 
    private Type dictionaryType; 

    public SimpleDictionary(String filename, Type dictionaryType) throws FileNotFoundException { 
        this.dictionaryType = dictionaryType; 
        this.tokens = new ArrayList<String>();

        File inputFile = new File(filename); 
        Scanner fileScanner = new Scanner(inputFile); 

        while (fileScanner.hasNextLine()) { 
            String line = fileScanner.nextLine(); 
            Tokenizer tokenizer = new Tokenizer(line); 
            List<String> tokensInCurrentLine = tokenizer.tokenizeOnlyAlphabetNNumber(); 
            tokens.addAll(tokensInCurrentLine); 
        }
        
        fileScanner.close();

        if (tokens.isEmpty()) 
            return; 

        if (dictionaryType == Type.Sorted) { 
            Collections.sort(tokens); 
            List<String> uniqueTokens = new ArrayList<String>(); 
            for (String token : tokens) { 
                if (uniqueTokens.isEmpty() || !uniqueTokens.get(uniqueTokens.size() - 1).equals(token)) 
                    uniqueTokens.add(token); 
            }
            tokens = uniqueTokens; 
        }

    }

    private boolean containsInSortedDictionary(String word) { 
        return Collections.binarySearch(tokens, word) >= 0; 
    }

    private boolean containsInUnSortedDictionary(String word) { 
        for (String token : tokens) 
            if (token.equals(word)) 
                return true; 
        return false; 
    }

    public boolean contains(String word) {
        if (this.dictionaryType == Type.Sorted) 
            return containsInSortedDictionary(word); 
        if (this.dictionaryType == Type.UnSorted) 
            return containsInUnSortedDictionary(word); 
        return false;
    }
    
}

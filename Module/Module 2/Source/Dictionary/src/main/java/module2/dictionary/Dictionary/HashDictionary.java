package module2.dictionary.Dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import module2.dictionary.Utils.Tokenizer;

public class HashDictionary implements Dictionary {
    private Set<String> dict; 

    public HashDictionary(String filename) throws FileNotFoundException { 
        this.dict = new HashSet<String>();

        File inputFile = new File(filename); 
        Scanner fileScanner = new Scanner(inputFile); 

        while (fileScanner.hasNextLine()) { 
            String line = fileScanner.nextLine(); 
            Tokenizer tokenizer = new Tokenizer(line); 
            List<String> tokensInCurrentLine = tokenizer.tokenizeOnlyAlphabetNNumber(); 
            for (String token : tokensInCurrentLine) 
                dict.add(token); 
        }

        fileScanner.close();
    }
    
    public boolean contains(String word) {
        return this.dict.contains(word); 
    }
    
}

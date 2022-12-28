package module2.dictionary.Dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import module2.dictionary.Utils.Tokenizer;

public class BloomFilterDictionary implements Dictionary {
    BloomFilter<String> dict; 

    public BloomFilterDictionary(String filename) throws FileNotFoundException { 
        this.dict = BloomFilter.create(
            Funnels.stringFunnel(Charset.forName("UTF-8")), 
            (long)(1e9));

        File inputFile = new File(filename); 
        Scanner fileScanner = new Scanner(inputFile); 

        while (fileScanner.hasNextLine()) { 
            String line = fileScanner.nextLine(); 
            Tokenizer tokenizer = new Tokenizer(line); 
            List<String> tokensInCurrentLine = tokenizer.tokenizeOnlyAlphabetNNumber(); 
            for (String token : tokensInCurrentLine) 
                dict.put(token); 
        }

        fileScanner.close();
    }

    public boolean contains(String word) {
        return dict.mightContain(word);
    }
    
}

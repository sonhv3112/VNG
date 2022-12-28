package module2.dictionary;

import java.io.FileNotFoundException;
import java.util.Scanner;

import module2.dictionary.Dictionary.*;

public class Main { 
    static Dictionary getDictionary(String filename, String type) { 
        Dictionary dict = null; 
        try { 
            if (type.toLowerCase().equals("simpledictionaryunsorted")) { 
                System.out.println("Building Simple Dictionary Unsorted...");
                dict = new SimpleDictionary(filename, SimpleDictionary.Type.UnSorted);
            } else if (type.toLowerCase().equals("simpledictionarysorted")) {
                System.out.println("Building Simple Dictionary Sorted...");
                dict = new SimpleDictionary(filename, SimpleDictionary.Type.Sorted);
            } else if (type.toLowerCase().equals("hashdictionary")) {
                System.out.println("Building Hash Dictionary...");
                dict = new HashDictionary(filename);
            } else if (type.toLowerCase().equals("bloomfilterdictionary")) { 
                System.out.println("Building Bloom Filter Dictionary...");
                dict = new BloomFilterDictionary(filename); 
            } else { 
                System.out.println("Building Trie Dictionary...");
                dict = new TrieDictionary(filename);
            }
        } catch (FileNotFoundException e) { 
            System.out.println("File not found!");
        }
        return dict; 
    }

    public static void main(String[] args) {
        if (args.length < 2) { 
            System.out.println("Loss arguments");
            return; 
        }

        // System.out.println(args[0] + args[1]);

        long startTimeAll = System.currentTimeMillis(); 

        Dictionary dict = getDictionary(args[0], args[1]); 

        if (dict != null) 
            System.out.println("[+] Build dictionary from file " + args[0] + " successful in " + (System.currentTimeMillis() - startTimeAll) + "ms"); 
        else { 
            System.out.println("[-] Build dictionary from file " + args[0] + " failed in " + (System.currentTimeMillis() - startTimeAll) + "ms"); 
            return; 
        }
        
        Scanner scanner = new Scanner(System.in); 

        while (true) { 
            System.out.println("[?] Enter your word, or \"-1\" to exit: ");
            String word = scanner.nextLine();
            if (word.equals("-1"))
                break; 
            long startTime = System.currentTimeMillis(); 

            System.out.print("[!] Find \"" + word + "\" in dictionary: ");
            System.out.println(dict.contains(word) ? "Yes" : "No");
            System.out.println("[!] Processing time: " + (System.currentTimeMillis() - startTime) + "ms\n");
        }

        scanner.close();

        System.out.println("[!] Total time excution: " + (System.currentTimeMillis() - startTimeAll) + "ms");
    }   
}
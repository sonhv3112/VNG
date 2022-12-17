package DirectoryTree;

import java.util.ArrayList;
import java.util.Scanner;

import DirectoryTree.Parser.DirectoryTreeParser;
import DirectoryTree.TypeComposite.AbstractElement;
import DirectoryTree.Unit.ElementSize;

public class Main { 
    public static void main(String[] args) { 
        Scanner inp = new Scanner(System.in); 
        System.out.println("Enter file containing directory tree:"); 
        String filename = inp.nextLine();  
        AbstractElement root; 

        try { 
            root = DirectoryTreeParser.parseDirectoryTreeFromFile(filename); 
        } catch (Exception e) { 
            e.printStackTrace();
            inp.close(); 
            return;
        }
        
        if (root == null) {
            System.out.println("Fail to parse");
            inp.close(); 
            return; 
        } 
        
        System.out.println("Success to parse directory tree from text file!");

        while (true) {
            System.out.print("Enter your option (0: search file/folder, 1: calculate total size of subtree, other: quit): "); 
            String option = inp.nextLine();
            switch (option) { 
                case "0": 
                    System.out.println("Enter name of file/folder:"); 
                    String name = inp.nextLine(); 
                    ArrayList<String> paths = root.search(name); 
                    if (paths.isEmpty()) { 
                        System.out.println("No item found!");
                    } else { 
                        System.out.println("Result:"); 
                        for (String path : paths) 
                            System.out.println(path);
                    }
                    break; 

                case "1": 
                    System.out.println("Enter path of file/folder:"); 
                    String path = inp.nextLine(); 
                    ElementSize totalSize = root.getTotalSizePath(path); 
                    if (totalSize.isEmpty()) { 
                        System.out.println("No item found!");
                    } else { 
                        System.out.println("Total size of \"" + path + "\": " + totalSize);
                    }
                    break;

                default: 
                    break; 
            }
            if (option.charAt(0) != '0' && option.charAt(0) != '1') 
                break; 
        }

        inp.close(); 
    }
}
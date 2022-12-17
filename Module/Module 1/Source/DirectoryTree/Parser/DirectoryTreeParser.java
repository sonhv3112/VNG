package DirectoryTree.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import DirectoryTree.Factory.ElementFactory;
import DirectoryTree.TypeComposite.AbstractElement;
import DirectoryTree.TypeComposite.ElementType;
import DirectoryTree.Unit.ElementSize;

public class DirectoryTreeParser {
    private static AbstractElement parseLine(String str) { 
        String[] tokens = str.strip().split(" ", 3);
        if (tokens.length != 3) 
            return null; 

        String type = tokens[0].substring(1, tokens[0].length() - 1); 
        ElementSize size = ElementSize.parseSize(tokens[1].substring(1, tokens[1].length() - 1)); 
        String name = tokens[2]; 

        // Create element from simple factory
        for (ElementType elementType : ElementType.values()) { 
            if (type.equals(elementType.toString())) { 
                return ElementFactory.createElement(elementType, name, size); 
            }
        }

        return null;  
    }

    private static int getLevelElement(String str) { 
        for (int i = 0; i < str.length(); ++i) { 
            if (str.charAt(i) != ' ') 
                return i / 4; 
        }
        return str.length() / 4; 
    }

    public static AbstractElement parseDirectoryTree(String str) throws Exception {  
        String[] lines = str.split("\n");
        if (lines.length == 0) {
            return null; 
        }

        AbstractElement root = null; 
        AbstractElement prevElement = null; 
        int prevLevel = 0; 

        for (String line : lines) { 
            if (line.strip().length() == 0) 
                continue; 

            if (root == null) { 
                if (getLevelElement(lines[0]) > 0)  
                    throw new Exception("Invalid root of directory tree format!"); 
                root = parseLine(lines[0]);
                if (root == null) 
                    throw new Exception("Invalid root of directory tree format!"); 
                prevElement = root; 
            } else { 
                int currentLevel = getLevelElement(line); 
                AbstractElement currentElement = parseLine(line); 

                if (currentLevel == 0 || currentElement == null) 
                    throw new Exception("Invalid directory tree format!"); 

                if (currentLevel == prevLevel) { 
                    // CASE: current file/folder is same level with previous file/folder 
                    AbstractElement parent = prevElement.getParent(); 

                    if (parent == null) // parent of previous file/folder is not a folder 
                        throw new Exception("Invalid directory tree format!"); 
                    
                    // Add current file/folder to parent folder 
                    parent.addChild(currentElement);
                    prevElement = currentElement; 
                } else if (currentLevel > prevLevel) { 
                    // CASE: previous folder contains current file/folder

                    if (currentLevel != prevLevel + 1) 
                    // Level of current file/folder must be greater than parent folder 1
                        throw new Exception("Invalid directory tree format!"); 
                        
                        
                    // Add current file/folder to previous folder 
                    prevElement.addChild(currentElement);
                    prevElement = currentElement; 
                    ++prevLevel; 
                } else {
                    /* CASE: Parent of current file/folder is ancestor of previous file/folder 
                     * Example: Folder 0 is parent of current element, and is the ancestor of previous element
                     *      Folder0/
                     *      |---Folder1/
                     *      |   |--- Previous element 
                     *      |---Current element
                    **/ 

                    // Find parent of current file/folder (level = currentLevel - 1) 
                    for (; currentLevel <= prevLevel; --prevLevel) { 
                        prevElement = prevElement.getParent(); 
                        if (prevElement == null) 
                            throw new Exception("Invalid directory tree format!"); 
                    }

                    // Add current file/folder to parent folder 
                    prevElement.addChild(currentElement);
                    prevElement = currentElement; 
                    ++prevLevel; 
                }
            }
        }

        return root; 
    }

    public static AbstractElement parseDirectoryTreeFromFile(String filename) throws Exception, FileNotFoundException { 
        String content = ""; 

        File inpFile = new File(filename); 
        Scanner inpScanner = new Scanner(inpFile); 
        while (inpScanner.hasNextLine()) { 
            content += inpScanner.nextLine() + '\n'; 
        }
        inpScanner.close();
        
        return parseDirectoryTree(content); 
    }
}

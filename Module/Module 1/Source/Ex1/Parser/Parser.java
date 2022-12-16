package Ex1.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

import Ex1.Factory.ElementFactory;
import Ex1.TypeComposite.AbstractType;
import Ex1.TypeComposite.DirectoryElement;
import Ex1.TypeComposite.ElementType;
import Ex1.TypeComposite.FileElement;
import Ex1.Unit.ElementSize;

public class Parser {
    private static AbstractType parseLine(String str, AbstractType parent) throws ParseException { 
        String[] tokens = str.strip().split(" ");
        if (tokens.length != 3) 
            throw new ParseException("Invalid element format!", 0); 
        
        String type = tokens[0].substring(1, tokens[0].length() - 2); 
        ElementSize size = ElementSize.parseSize(tokens[1].substring(1, tokens[1].length() - 2)); 
        String name = tokens[2]; 

        // create element from simple factory
        for (ElementType elementType : ElementType.values()) { 
            if (type.equals(elementType.toString())) { 
                return ElementFactory.createElement(elementType, parent, name, size); 
            }
        }

        return null;  
    }

    private static int countLevelElement(String str) { 
        for (int i = 0; i < str.length(); ++i) { 
            if (str.charAt(i) != ' ') 
                return i; 
        }
        return str.length(); 
    }

    public static AbstractType parseDirectoryTree(String str) {  
        String[] lines = str.split("\n");
        if (lines.length == 0) {
            return null; 
        }

        AbstractType root; 
        try { 
            root = parseLine(lines[0], null);
        } catch (Exception e) { 
            System.out.println(e);
            e.printStackTrace();
            return null; 
        }
        AbstractType pointer = null; 
        int levelPointer = 0; 
        for (String line : lines) { 

        }
        return new FileElement(null, null, "a"); 
    }

    public static AbstractType parseDirectoryTreeFromFile(String filename) { 
        String content = ""; 
        try { 
            File inpFile = new File(filename); 
            Scanner inpScanner = new Scanner(inpFile); 
            while (inpScanner.hasNextLine()) { 
                content += inpScanner.nextLine() + '\n'; 
            }
            String[] arrString = content.split("\n"); 
            for (String str : arrString) 
                System.out.println(str);
            inpScanner.close();
        } catch (FileNotFoundException e) { 
            System.out.println(e);
        }
        return parseDirectoryTree(content); 
    }
}

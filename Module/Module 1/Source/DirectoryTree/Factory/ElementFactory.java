package DirectoryTree.Factory;

import DirectoryTree.TypeComposite.AbstractElement;
import DirectoryTree.TypeComposite.DirectoryElement;
import DirectoryTree.TypeComposite.ElementType;
import DirectoryTree.TypeComposite.FileElement;
import DirectoryTree.Unit.ElementSize;

public class ElementFactory {
    public static AbstractElement createElement(ElementType type, String name, ElementSize size) {
        if (type == ElementType.File) 
            return new FileElement(name, size); 
        if (type == ElementType.Directory) 
            return new DirectoryElement(name, size); 
        return null; 
    }
}

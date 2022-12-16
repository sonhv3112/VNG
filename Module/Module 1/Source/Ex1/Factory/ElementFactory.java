package Ex1.Factory;

import Ex1.TypeComposite.AbstractType;
import Ex1.TypeComposite.DirectoryElement;
import Ex1.TypeComposite.ElementType;
import Ex1.TypeComposite.FileElement;
import Ex1.Unit.ElementSize;

public class ElementFactory {
    public static AbstractType createElement(ElementType type, AbstractType parent, String name, ElementSize size) {
        if (type == ElementType.File) 
            return new FileElement(parent, name, size); 
        if (type == ElementType.Directory) 
            return new DirectoryElement(parent, name, size); 
        return null; 
    }
}

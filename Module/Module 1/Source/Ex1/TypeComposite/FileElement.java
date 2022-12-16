package Ex1.TypeComposite;

import Ex1.Unit.ElementSize;

public class FileElement extends AbstractType {
    public FileElement(AbstractType parent, String name, ElementSize size) { 
        super(parent, name, size); 
    }
    public FileElement(AbstractType parent, String name, String size) { 
        super(parent, name, size); 
    }
}

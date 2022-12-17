package DirectoryTree.TypeComposite;

import DirectoryTree.Unit.ElementSize;

public class FileElement extends AbstractElement {
    public FileElement(String name, ElementSize size) { 
        super(name, size); 
    }
    public FileElement(String name, String size) { 
        super(name, size); 
    }
}

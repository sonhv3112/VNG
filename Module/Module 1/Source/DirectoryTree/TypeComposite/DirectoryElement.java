package DirectoryTree.TypeComposite;

import java.util.ArrayList;

import DirectoryTree.Unit.ElementSize;

public class DirectoryElement extends AbstractElement {
    private ArrayList<AbstractElement> childComponents; 

    public DirectoryElement(String name, ElementSize size) { 
        super(name, size);  
        this.childComponents = new ArrayList<>(); 
    }

    public DirectoryElement(String name, String size) { 
        super(name, size);  
        this.childComponents = new ArrayList<>(); 
    }

    public DirectoryElement(String name, ElementSize size, ArrayList<AbstractElement> childComponents) { 
        super(name, size); 
        this.childComponents = new ArrayList<>(childComponents); 
    }

    @Override
    protected void print(int padding) {  
        super.print(padding);
        for (AbstractElement child : childComponents) 
            child.print(padding + 4);
    }

    @Override
    protected void search(ArrayList<String> currentList, String name) {
        super.search(currentList, name);

        for (AbstractElement child : this.childComponents)
            child.search(currentList, name);
    }

    @Override
    public ElementSize getTotalSize() {
        ElementSize subtreeSize = super.getTotalSize(); 

        for (AbstractElement child : this.childComponents) 
            subtreeSize.add(child.getTotalSize()); 

        return subtreeSize; 
    }
    
    @Override
    public ElementSize getTotalSizePath(String path) { 
        if (this.path.equals(path)) 
            return this.getTotalSize(); 
        ElementSize totalSize = new ElementSize(); 
        for (AbstractElement child : childComponents) { 
            if (path.startsWith(child.path)) 
                totalSize.add(child.getTotalSizePath(path)); 
        }
        return totalSize; 
    }

    @Override
    public void addChild(AbstractElement child) { 
        child.setParent(this);
        childComponents.add(child); 
    } 

    @Override
    public void removeChild(int id) { 
        childComponents.remove(id); 
    }
    
    @Override
    public void removeChild(AbstractElement child) { 
        childComponents.remove(child); 
    }
}

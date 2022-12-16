package Ex1.TypeComposite;

import java.util.ArrayList;

import Ex1.Unit.ElementSize;

public class DirectoryElement extends AbstractType {
    private ArrayList<AbstractType> childComponents; 

    public DirectoryElement(AbstractType parent, String name, ElementSize size) { 
        super(parent, name, size);  
        this.childComponents = new ArrayList<>(); 
    }

    public DirectoryElement(AbstractType parent, String name, String size) { 
        super(parent, name, size);  
        this.childComponents = new ArrayList<>(); 
    }

    public DirectoryElement(AbstractType parent, String name, ElementSize size, ArrayList<AbstractType> childComponents) { 
        super(parent, name, size); 
        this.childComponents = new ArrayList<>(childComponents); 
    }

    @Override
    public void search(ArrayList<String> currentList, String name) {
        super.search(currentList, name);

        for (AbstractType child : this.childComponents)
            child.search(currentList, name);
    }

    @Override
    public ElementSize getTotalSize() {
        ElementSize subtreeSize = super.getTotalSize(); 

        for (AbstractType child : this.childComponents) 
            subtreeSize.add(child.getTotalSize()); 

        return subtreeSize; 
    }
    
    @Override
    public void addChild(AbstractType child) { 
        childComponents.add(child); 
    } 

    @Override
    public void removeChild(int id) { 
        childComponents.remove(id); 
    }
    
    @Override
    public void removeChild(AbstractType child) { 
        childComponents.remove(child); 
    }
}

package Ex1.TypeComposite;

import java.util.ArrayList;

import Ex1.Unit.ElementSize;

public abstract class AbstractType {

    protected AbstractType parent; 
    protected String path, name; 
    protected ElementSize size;

    AbstractType(AbstractType parent, String name, ElementSize size) { 
        this.parent = parent; 
        this.path = parent != null ? parent.path + name : name; 
        this.name = name; 
        this.size = size; 
        this.fixPath();
    }

    AbstractType(AbstractType parent, String name, String size) { 
        this.parent = parent; 
        this.path = parent != null ? parent.path + name : name; 
        this.name = name; 
        this.size = ElementSize.parseSize(size); 
        this.fixPath();
    }
    
    private void fixPath() { 
        // make sure that path is a directory 
        if (this.path.charAt(this.path.length() - 1) != '/') 
            this.path += '/';
    }

    public void search(ArrayList<String> currentList, String name) { 
        if (this.name.equals(name))
            currentList.add(path + name); 
    }

    public ElementSize getTotalSize() {
        return size;
    }

    public void addChild(AbstractType child) {}
    public void removeChild(int id) {}
    public void removeChild(AbstractType child) {}
}

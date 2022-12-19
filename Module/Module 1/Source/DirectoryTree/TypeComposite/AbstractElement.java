package DirectoryTree.TypeComposite;

import java.util.ArrayList;

import DirectoryTree.Unit.ElementSize;

public abstract class AbstractElement {

    protected AbstractElement parent; 
    protected String path, name; 
    protected ElementSize size;

    protected AbstractElement(String name, ElementSize size) { 
        this.parent = null; 
        this.name = name; 
        this.size = size; 
        this.setPath();
    }

    protected AbstractElement(String name, String size) { 
        this.parent = null; 
        this.name = name; 
        this.size = ElementSize.parseSize(size); 
        this.setPath();
    }

    private void setPath() { 
        this.path = this.parent == null ? this.name : this.parent.path + '/' + this.name; 
    }
    
    protected void print(int padding) { 
        for (int i = 0; i < padding; ++i) { 
            System.out.print(' ');
        }
        System.out.println(this.name);
    }
    
    protected void search(ArrayList<String> currentList, String name) { 
        if (this.name.equals(name))
            currentList.add(this.path); 
    }

    public AbstractElement getParent() { 
        return this.parent; 
    }

    public void setParent(AbstractElement parent) { 
        this.parent = parent; 
        this.setPath();
    }

    public ArrayList<String> search(String name) { 
        ArrayList<String> result = new ArrayList<>(); 
        this.search(result, name);
        return result; 
    }

    public ElementSize getTotalSize() {
        return size;
    }

    public ElementSize getTotalSizePath(String path) { 
        if (this.path.equals(path)) 
            return this.getTotalSize(); 
        return new ElementSize(0); 
    }

    public void print() { 
        this.print(0); 
    }

    public void addChild(AbstractElement child) {}
    public void removeChild(int id) {}
    public void removeChild(AbstractElement child) {}
}

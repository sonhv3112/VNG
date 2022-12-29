package AbstractFactory;

public class WindowFactory implements GUIFactory { 
    public Button createButton() { 
        return new WindowButton(); 
    }

    public CheckBox createCheckBox() { 
        return new WindowCheckBox(); 
    }
}
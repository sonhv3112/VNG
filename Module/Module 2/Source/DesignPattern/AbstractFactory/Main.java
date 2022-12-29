package AbstractFactory; 

public class Main {
    public static void main(String[] args) {
        GUIFactory factory = new MacOSFactory(); 
        Button button = factory.createButton(); 
        CheckBox checkBox = factory.createCheckBox(); 
        button.print(); 
        checkBox.print();

        factory = new WindowFactory();
        button = factory.createButton(); 
        checkBox = factory.createCheckBox(); 
        button.print(); 
        checkBox.print(); 
    }
}

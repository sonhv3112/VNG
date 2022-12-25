package FactoryMethod;

public class FactoryA implements Factory {
    public Product getProduct() { 
        return new ProductA();
    }
}

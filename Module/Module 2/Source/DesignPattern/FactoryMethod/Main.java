package FactoryMethod;

public class Main {
    public static void main(String[] args) { 
        Factory factory = new FactoryA(); 
        Product product = factory.getProduct(); 
        product.print(); 

        Factory factory2 = new FactoryB(); 
        Product product2 = factory2.getProduct(); 
        product2.print(); 
    }
}

public class Main {

    static {
        System.loadLibrary("native");
    }
    
    public static void main(String[] args) {
        new Main().sayHello();
    }

    // Declare a native method sayHello() that receives no arguments and returns void
    private native void sayHello();
}
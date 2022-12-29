package Builder;

public class Main {
    public static void main(String[] args) { 
        UserBuilder userBuilder = new UserBuilder(); 
        User user = userBuilder
            .setFirstName("Nguyen")
            .setLastName("Van A")
            .setAddress("123 B")
            .build();
        System.out.println(user);
    }
}

package Builder;

public class UserBuilder {
    private User result; 

    UserBuilder() {
		this.result = new User();
    }

	public UserBuilder setFirstName(String firstName) {
		this.result.setFirstName(firstName);
		return this; 
	}

	public UserBuilder setLastName(String lastName) {
		this.result.setLastName(lastName);
		return this; 
	}

	public UserBuilder setAge(int age) {
		this.result.setAge(age);
		return this; 
	}

	public UserBuilder setPhone(String phone) {
		this.result.setPhone(phone);
		return this; 
	}

	public UserBuilder setAddress(String address) {
		this.result.setAddress(address);
		return this; 
	}

    public User build() { 
        User user = this.result; 
        this.result = new User(); 
        return user; 
    }
}

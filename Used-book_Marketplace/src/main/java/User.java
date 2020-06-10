public class User {

    // private variables of the information of a user
    private String id;
    private String name;
    private String password;
    private String phoneNumber;
    private String emailAddress;

    // make setters, getters of private variables of the information of a user
    public void setId(String userId) {
        this.id = userId;
    }
    public String getId() {
        return id;
    }

    public void setName(String userName) {
        this.name = userName;
    }
    public String getName() {
        return name;
    }

    public void setPassword(String userPassword) {
        this.password = userPassword;
    }
    public String getPassword() {
        return password;
    }

    public void setPhoneNumber(String userPhoneNumber) {
        this.phoneNumber = userPhoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmailAddress(String userEmailAddress) {
        this.emailAddress = userEmailAddress;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
}

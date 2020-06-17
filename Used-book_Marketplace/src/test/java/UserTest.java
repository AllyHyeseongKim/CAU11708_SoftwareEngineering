import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class UserTest {
    User user = new User();

    String id = "id";
    String name = "name";
    String password = "password";
    String phoneNumber = "01000000000";
    String emailAddress = "email@google.com";
    String status = "activated";

    @BeforeEach
    void setUp() {
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setEmailAddress(emailAddress);
        user.setStatus(status);
    }

    @org.junit.jupiter.api.Test
    void setId() {
        String modifiedId = "modified id";
        user.setId(modifiedId);
        String idResult = user.getId();

        assertThat(idResult, is(modifiedId));
    }

    @org.junit.jupiter.api.Test
    void getId() {
        String idResult = user.getId();

        assertThat(idResult, is(id));
    }

    @org.junit.jupiter.api.Test
    void setName() {
        String modifiedName = "modified name";
        user.setName(modifiedName);
        String nameResult = user.getName();

        assertThat(nameResult, is(modifiedName));
    }

    @org.junit.jupiter.api.Test
    void getName() {
        String nameResult = user.getName();

        assertThat(nameResult, is(name));
    }

    @org.junit.jupiter.api.Test
    void setPassword() {
        String modifiedPassword = "modified password";
        user.setPassword(modifiedPassword);
        String passwordResult = user.getPassword();

        assertThat(passwordResult, is(modifiedPassword));
    }

    @org.junit.jupiter.api.Test
    void getPassword() {
        String passwordResult = user.getPassword();

        assertThat(passwordResult, is(password));
    }

    @org.junit.jupiter.api.Test
    void setPhoneNumber() {
        String modifiedPhoneNumber = "01011111111";
        user.setPhoneNumber(modifiedPhoneNumber);
        String phoneNumberResult = user.getPhoneNumber();

        assertThat(phoneNumberResult, is(modifiedPhoneNumber));
    }

    @org.junit.jupiter.api.Test
    void getPhoneNumber() {
        String phoneNumberResult = user.getPhoneNumber();

        assertThat(phoneNumberResult, is(phoneNumber));
    }

    @org.junit.jupiter.api.Test
    void setEmailAddress() {
        String modifiedEmailAddress = "modify@google.com";
        user.setEmailAddress(modifiedEmailAddress);
        String emailAddressResult = user.getEmailAddress();

        assertThat(emailAddressResult, is(modifiedEmailAddress));
    }

    @org.junit.jupiter.api.Test
    void getEmailAddress() {
        String emailAddressResult = user.getEmailAddress();

        assertThat(emailAddressResult, is(emailAddress));
    }

    @org.junit.jupiter.api.Test
    void setStatus() {
        String modifiedStatus = "deactivated";
        user.setStatus(modifiedStatus);
        String statusResult = user.getStatus();

        assertThat(statusResult, is(modifiedStatus));
    }

    @org.junit.jupiter.api.Test
    void getStatus() {
        String statusResult = user.getStatus();

        assertThat(statusResult, is(status));
    }

    @org.junit.jupiter.api.Test
    void isRightPhoneNumber() {
        Boolean isRightPhoneNumberResult = user.isRightPhoneNumber();
        user.setPhoneNumber("phone number");
        Boolean isNotRightPhoneNumberResult = user.isRightPhoneNumber();

        assertThat(isRightPhoneNumberResult, is(true));
        assertThat(isNotRightPhoneNumberResult, is(false));
    }

    @org.junit.jupiter.api.Test
    void isRightEmailAddress() {
        Boolean isRightEmailAddress = user.isRightEmailAddress();
        user.setEmailAddress("email address");
        Boolean isNotRightPhoneNumberResult = user.isRightEmailAddress();

        assertThat(isRightEmailAddress, is(true));
        assertThat(isNotRightPhoneNumberResult, is(false));
    }
}
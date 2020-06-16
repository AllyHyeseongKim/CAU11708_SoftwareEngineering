import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class UserTest {

    @Test
    public void test_add() {
        User user = new User();

        String id = "id";
        String name = "name";
        String password = "password";
        String phoneNumber = "phoneNumber";
        String emailAddress = "emailAddress";
        String status = "status";

        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setEmailAddress(emailAddress);
        user.setStatus(status);

        String idResult = user.getId();
        String nameResult = user.getName();
        String passwordResult = user.getPassword();
        String phoneNumberResult = user.getPhoneNumber();
        String emailAddressResult = user.getEmailAddress();
        String statusResult = user.getStatus();

        assertThat(idResult, is(id));
        assertThat(nameResult, is(name));
        assertThat(passwordResult, is(password));
        assertThat(phoneNumberResult, is(phoneNumber));
        assertThat(emailAddressResult, is(phoneNumber));
        assertThat(statusResult, is(status));
    }
}
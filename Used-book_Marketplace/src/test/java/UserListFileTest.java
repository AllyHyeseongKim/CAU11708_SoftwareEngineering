import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class UserListFileTest {
    @Test
    public void test_add() {
        UserListFile userListFile = new UserListFile("data/test-user.json");

        UserList userList = new UserList();
        User user = new User();

        String id = "id";
        String name = "name";
        String password = "password";
        String phoneNumber = "phoneNumber";
        String emailAddress = "emailAddress";
        String status = "activated";

        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setEmailAddress(emailAddress);
        user.setStatus(status);

        userList.addUser(user);

        userListFile.writeJSON(userList);
        UserList readJSONResult = userListFile.readJSON();

        assertThat(readJSONResult, is(userList));
    }
}

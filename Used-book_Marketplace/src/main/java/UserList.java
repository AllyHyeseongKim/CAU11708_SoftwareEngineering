import java.util.ArrayList;


public class UserList {

    private ArrayList<User> userList = new ArrayList<User>();

    public User getUser(int index) {
        try {
            return userList.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void addUser(User addedUser) {
        userList.add(addedUser);
    }

    public void modifyUser(int index, User modifiedUser) {
        try {
            userList.set(index, modifiedUser);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteUser(int index) {
        try {
            userList.remove(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void printUserList() {
        for (int i = 0; i < userList.size(); i++) {
            userList.get(i).printUserInformation();
        }
    }
}

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

    public int getNumUsers() {
        return this.userList.size();
    }

    public boolean isAdmin(String id, char[] secretPassword) {
        String password = "";
        for (int i = 0; i < secretPassword.length; i++) {
            password += Character.toString(secretPassword[i]);
        }

        if (id.equals("admin") && password.equals("nayana")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isActivated(String id) {
        for (User user : this.userList) {
            if (user.getId().equals(id)) {
                if (user.getStatus().equals("activated")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean login(String id, char[] secretPassword) {
        String password = "";
        for (int i = 0; i < secretPassword.length; i++) {
            password += Character.toString(secretPassword[i]);
        }

        for (User user : this.userList) {
            if (user.getId().equals(id)) {
                if (user.getPassword().equals(password)) {
                    return true;
                } else {
                    System.out.println("Wrong Password");
                    return false;
                }
            }
        }
        System.out.println("Id not found");
        return false;
    }

    public User searchUser(String id) {
        User searchedUser = null;

        for (User user : this.userList) {
            if (user.getId().equals(id)) {
                searchedUser = user;
            }
        }
        return searchedUser;
    }

    public void addUser(User addedUser) {
        userList.add(addedUser);
    }

    public void modifyUser(int index, User modifiedUser) {
        try {
            this.userList.set(index, modifiedUser);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public BookList deleteUser(int index, BookList bookList) {
        if (this.getUser(index).getStatus() == "deactivated") {
            try {
                for (int i = 0; i < bookList.getNumBooks(); i++) {
                    if (bookList.getBook(i).getSellerId() == this.getUser(index).getId()) {
                        bookList.deleteBook(i);
                        i--;
                    }
                }
                userList.remove(index);
                return bookList;
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                throw e;
            }
        } else {
            System.out.println("The User is activated.");
            System.out.println("Not available to delete the user.");
            return bookList;
        }
    }

    public boolean isConflictedId(String id) {
        for (User user: this.userList) {
            if (user.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void printUserList() {
        for (int i = 0; i < userList.size(); i++) {
            userList.get(i).printUserInformation();
        }
    }
}

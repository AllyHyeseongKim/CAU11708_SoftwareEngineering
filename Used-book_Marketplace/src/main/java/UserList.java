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

    public void printUserList() {
        for (int i = 0; i < userList.size(); i++) {
            userList.get(i).printUserInformation();
        }
    }
}

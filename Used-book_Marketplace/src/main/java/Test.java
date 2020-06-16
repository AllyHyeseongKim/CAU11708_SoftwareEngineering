public class Test {
    public static void main(String[] args) {

        BookListFile bookListFile = new BookListFile();
        BookList bookList = bookListFile.readJSON("data/book.json");
        UserListFile userListFile = new UserListFile();
        UserList userList = userListFile.readJSON("data/user.json");
        LoginFrame loginFrame = new LoginFrame(userList);
    }
}
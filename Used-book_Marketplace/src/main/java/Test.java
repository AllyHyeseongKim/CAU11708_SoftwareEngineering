public class Test {
    public static void main(String[] args) {
        BookListFile bookListFile = new BookListFile("data/book.json");
        BookList bookList = bookListFile.readJSON();
        UserListFile userListFile = new UserListFile("data/user.json");
        UserList userList = userListFile.readJSON();
        LoginFrame loginFrame = new LoginFrame(userList, bookList);
    }
}
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class UserListTest {
    @Test
    public void test_add() {
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

        User getUserResult = userList.getUser(0);
        int getNumUsersResult = userList.getNumUsers();
        boolean isAdminResult = userList.isAdmin("admin", "nayana".toCharArray());
        boolean isActivatedResult = userList.isActivated(id);
        boolean loginResult = userList.login(id, password.toCharArray());
        User searchUserResult = userList.searchUser(id);

        UserList modifiedUserList = userList;
        User modifiedUser = user;
        modifiedUser.setId("Modified Id");
        modifiedUserList.modifyUser(0, modifiedUser);
        User modifiedUserResult = modifiedUserList.getUser(0);

        UserList deletedUserList = userList;
        BookList bookList = new BookList();
        Book book = new Book();

        String bookId = "id";
        String ISBN = "ISBN";
        String title = "title";
        String author = "author";
        String publisher = "publisher";
        String year = "year";
        String price = "price";
        String bookStatus = "status";

        book.setSellerId(bookId);
        book.setISBN(ISBN);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setYear(year);
        book.setPrice(price);
        book.setStatus(bookStatus);

        bookList.addBook(book);

        Book newBook = new Book();

        bookId = "new id";
        ISBN = "ISBN";
        title = "title";
        author = "author";
        publisher = "publisher";
        year = "year";
        price = "price";
        bookStatus = "status";

        book.setSellerId(bookId);
        book.setISBN(ISBN);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setYear(year);
        book.setPrice(price);
        book.setStatus(bookStatus);

        bookList.addBook(newBook);
        deletedUserList.addUser(modifiedUser);
        BookList deletedUserBookListResult = deletedUserList.deleteUser(1, bookList);

        boolean isConflictedResult = userList.isConflictedId(id);

        assertThat(getUserResult, is(user));
        assertThat(getNumUsersResult, is(1));
        assertThat(isAdminResult, is(true));
        assertThat(isActivatedResult, is(true));
        assertThat(loginResult, is(true));
        assertThat(searchUserResult, is(user));
        assertThat(modifiedUserResult, is(modifiedUser));
        assertThat(deletedUserList, is(userList));
        assertThat(deletedUserBookListResult, is(newBook));
        assertThat(isConflictedResult, is(true));
    }
}

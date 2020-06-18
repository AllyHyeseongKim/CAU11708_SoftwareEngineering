package com.AllyHyeseongKim.usedbookmarketplace.controller;

import com.AllyHyeseongKim.usedbookmarketplace.model.Book;
import com.AllyHyeseongKim.usedbookmarketplace.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


class UserControllerTest {
    UserListFile userListFile = new UserListFile("data/user.json");
    ArrayList<User> userList = userListFile.readJSON();

    User user = userList.get(0);
    String userId = user.getId();

    @Test
    void addBook() {
        Book searchBook = new Book();

        String title = "title";
        String author = "author";
        String publisher = "publisher";
        String year = "2020";

        searchBook.setTitle(title);
        searchBook.setAuthor(author);
        searchBook.setPublisher(publisher);
        searchBook.setYear(year);

        UserController userController = new UserController(this.userId, this.userList);
        ArrayList<Book> bookListResult = userController.addBook(searchBook);
        Book bookResult = bookListResult.get(bookListResult.size() - 1);

        String titleResult = bookResult.getTitle();
        String authorResult = bookResult.getAuthor();
        String publisherResult = bookResult.getPublisher();
        String yearResult = bookResult.getYear();

        assertThat(titleResult, is(searchBook.getTitle()));
        assertThat(authorResult, is(searchBook.getAuthor()));
        assertThat(publisherResult, is(searchBook.getPublisher()));
        assertThat(yearResult, is(searchBook.getYear()));
    }

    @Test
    void searchAPI() {
        BookListFile bookListFile = new BookListFile("data/bookAPI.json");
        ArrayList<Book> bookList = bookListFile.readJSON();

        Book searchBook = bookList.get(0);

        UserController userController = new UserController(this.userId, this.userList);
        Book bookResult = userController.searchAPI(searchBook);

        String idResult = null;
        String ISBNResult = bookResult.getISBN();
        String titleResult = bookResult.getTitle();
        String authorResult = bookResult.getAuthor();
        String publisherResult = bookResult.getPublisher();
        String yearResult = bookResult.getYear();
        String priceResult = null;
        String statusResult = null;

        assertThat(idResult, is(searchBook.getSellerId()));
        assertThat(ISBNResult, is(searchBook.getISBN()));
        assertThat(titleResult, is(searchBook.getTitle()));
        assertThat(authorResult, is(searchBook.getAuthor()));
        assertThat(publisherResult, is(searchBook.getPublisher()));
        assertThat(yearResult, is(searchBook.getYear()));
        assertThat(priceResult, is(searchBook.getPrice()));
        assertThat(statusResult, is(searchBook.getStatus()));
    }

    @Test
    void searchBooks() {
        BookListFile bookListFile = new BookListFile("data/test-book.json");
        ArrayList<Book> bookList = new ArrayList<>();

        Book book = new Book();

        String id = "id";
        String ISBN = "ISBN";
        String title = "title";
        String author = "author";
        String publisher = "publisher";
        String year = "2020";

        book.setSellerId(id);
        book.setISBN(ISBN);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setYear(year);

        bookList.add(book);

        bookListFile.writeJSON(bookList);

        ArrayList<Book> bookListResult = bookListFile.readJSON();

        UserController userController = new UserController(this.userId, this.userList);
        ArrayList<Book> sellerIdSearchedBookList = userController.searchBooks("Seller Id", id, bookListResult);
        ArrayList<Book> ISBNSearchedBookList = userController.searchBooks("ISBN", ISBN, bookListResult);
        ArrayList<Book> titleSearchedBookList = userController.searchBooks("Title", title, bookListResult);
        ArrayList<Book> authorSearchedBookList = userController.searchBooks("Author", author, bookListResult);
        ArrayList<Book> publisherSearchedBookList = userController.searchBooks("Publisher", publisher, bookListResult);
        ArrayList<Book> yearSearchedBookList = userController.searchBooks("Year", year, bookListResult);

        Book sellerIdSearchedBookResult = sellerIdSearchedBookList.get(0);
        Book ISBNSearchedBookResult = ISBNSearchedBookList.get(0);
        Book titleSearchedBookResult = titleSearchedBookList.get(0);
        Book authorSearchedBookResult = authorSearchedBookList.get(0);
        Book publisherSearchedBookResult = publisherSearchedBookList.get(0);
        Book yearSearchedBookResult = yearSearchedBookList.get(0);

        String idResult = sellerIdSearchedBookResult.getSellerId();
        String ISBNResult = ISBNSearchedBookResult.getISBN();
        String titleResult = titleSearchedBookResult.getTitle();
        String authorResult = authorSearchedBookResult.getAuthor();
        String publisherResult = publisherSearchedBookResult.getPublisher();
        String yearResult = yearSearchedBookResult.getYear();

        assertThat(idResult, is(id));
        assertThat(ISBNResult, is(ISBN));
        assertThat(titleResult, is(title));
        assertThat(authorResult, is(author));
        assertThat(publisherResult, is(publisher));
        assertThat(yearResult, is(year));
    }

    @Test
    void searchUser() {
        UserController userController = new UserController(this.userId, this.userList);

        User userResult = userController.searchUser(this.userId);

        String idResult = userResult.getId();
        String passwordResult = userResult.getPassword();
        String phoneNumberResult = userResult.getPhoneNumber();
        String emailAddressResult = userResult.getEmailAddress();
        String statusResult = userResult.getStatus();

        assertThat(idResult, is(user.getId()));
        assertThat(passwordResult, is(user.getPassword()));
        assertThat(phoneNumberResult, is(user.getPhoneNumber()));
        assertThat(emailAddressResult, is(user.getEmailAddress()));
        assertThat(statusResult, is(user.getStatus()));
    }

    @Test
    void modifyBooks() {
        Book modifiedBook = new Book();

        String modifiedId = "modified id";
        String modifiedISBN = "modified ISBN";
        String modifiedTitle = "modified title";
        String modifiedAuthor = "modified author";
        String modifiedPublisher = "modified publisher";
        String modifiedYear = "2000";
        String modifiedPrice = "10000";
        String modifiedStatus = "Good";

        modifiedBook.setSellerId(modifiedId);
        modifiedBook.setISBN(modifiedISBN);
        modifiedBook.setTitle(modifiedTitle);
        modifiedBook.setAuthor(modifiedAuthor);
        modifiedBook.setPublisher(modifiedPublisher);
        modifiedBook.setYear(modifiedYear);
        modifiedBook.setPrice(modifiedPrice);
        modifiedBook.setStatus(modifiedStatus);

        UserController userController = new UserController(this.userId, this.userList);
        Book bookResult = userController.modifyBook(0, modifiedBook);

        String idResult = bookResult.getSellerId();
        String ISBNResult = bookResult.getISBN();
        String titleResult = bookResult.getTitle();
        String authorResult = bookResult.getAuthor();
        String publisherResult = bookResult.getPublisher();
        String yearResult = bookResult.getYear();
        String priceResult = bookResult.getPrice();
        String statusResult = bookResult.getStatus();

        assertThat(idResult, is(modifiedId));
        assertThat(ISBNResult, is(modifiedISBN));
        assertThat(titleResult, is(modifiedTitle));
        assertThat(authorResult, is(modifiedAuthor));
        assertThat(publisherResult, is(modifiedPublisher));
        assertThat(yearResult, is(modifiedYear));
        assertThat(priceResult, is(modifiedPrice));
        assertThat(statusResult, is(modifiedStatus));
    }
}
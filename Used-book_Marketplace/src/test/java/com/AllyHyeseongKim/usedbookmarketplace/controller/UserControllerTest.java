package com.AllyHyeseongKim.usedbookmarketplace.controller;

import com.AllyHyeseongKim.usedbookmarketplace.model.Book;
import com.AllyHyeseongKim.usedbookmarketplace.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


class UserControllerTest {
    BookListFile bookListFile = new BookListFile("data/bookAPI.json");
    ArrayList<Book> bookList = bookListFile.readJSON();

    Book searchBook = bookList.get(0);

    String userId = "user id";

    ArrayList<User> userList = new ArrayList<>();

    @BeforeEach
    void setUp() {
    }

    @Test
    void addBook() {
    }

    @Test
    void searchAPI() {
        UserController userController = new UserController(userId, this.userList);
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
    }

    @Test
    void purchaseBooks() {
    }

    @Test
    void searchUser() {
    }

    @Test
    void modifyBooks() {
    }
}
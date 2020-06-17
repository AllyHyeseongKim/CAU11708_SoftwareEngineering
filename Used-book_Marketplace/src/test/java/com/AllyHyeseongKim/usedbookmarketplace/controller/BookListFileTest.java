package com.AllyHyeseongKim.usedbookmarketplace.controller;

import com.AllyHyeseongKim.usedbookmarketplace.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


class BookListFileTest {
    String filePath = "data/test-book.json";

    BookListFile bookListFile = new BookListFile(this.filePath);

    ArrayList<Book> bookList = new ArrayList<>();

    Book book = new Book();

    String id = "id";
    String ISBN = "ISBN";
    String title = "title";
    String author = "author";
    String publisher = "publisher";
    String year = "2020";
    String price = "20000";
    String status = "Excellent";

    @BeforeEach
    void setUp() {
        book.setSellerId(id);
        book.setISBN(ISBN);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setYear(year);
        book.setPrice(price);
        book.setStatus(status);

        bookList.add(book);

        bookListFile.writeJSON(bookList);
    }

    @Test
    void readJSON() {
        ArrayList<Book> bookListResult = bookListFile.readJSON();

        Book bookResult = bookListResult.get(0);

        String idResult = bookResult.getSellerId();
        String ISBNResult = bookResult.getISBN();
        String titleResult = bookResult.getTitle();
        String authorResult = bookResult.getAuthor();
        String publisherResult = bookResult.getPublisher();
        String yearResult = bookResult.getYear();
        String priceResult = bookResult.getPrice();
        String statusResult = bookResult.getStatus();

        assertThat(idResult, is(id));
        assertThat(ISBNResult, is(ISBN));
        assertThat(titleResult, is(title));
        assertThat(authorResult, is(author));
        assertThat(publisherResult, is(publisher));
        assertThat(yearResult, is(year));
        assertThat(priceResult, is(price));
        assertThat(statusResult, is(status));
    }

    @Test
    void writeJSON() {
        String modifiedId = "modified id";
        String modifiedISBN = "modified ISBN";
        String modifiedTitle = "modified title";
        String modifiedAuthor = "modified author";
        String modifiedPublisher = "modified publisher";
        String modifiedYear = "2000";
        String modifiedPrice = "10000";
        String modifiedStatus = "Good";

        book.setSellerId(modifiedId);
        book.setISBN(modifiedISBN);
        book.setTitle(modifiedTitle);
        book.setAuthor(modifiedAuthor);
        book.setPublisher(modifiedPublisher);
        book.setYear(modifiedYear);
        book.setPrice(modifiedPrice);
        book.setStatus(modifiedStatus);

        bookList.add(book);

        bookListFile.writeJSON(bookList);

        ArrayList<Book> bookListResult = bookListFile.readJSON();

        Book bookResult = bookListResult.get(0);

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
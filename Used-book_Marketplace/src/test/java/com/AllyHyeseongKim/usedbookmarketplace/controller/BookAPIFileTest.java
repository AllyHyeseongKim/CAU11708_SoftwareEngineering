package com.AllyHyeseongKim.usedbookmarketplace.controller;

import com.AllyHyeseongKim.usedbookmarketplace.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


class BookAPIFileTest {
    String filePath = "data/test-bookAPI.json";

    BookListFile bookListFile = new BookListFile(this.filePath);

    ArrayList<Book> bookList = new ArrayList<>();

    Book book = new Book();

    String id = "id";
    String ISBN = "ISBN";
    String title = "title";
    String author = "author";
    String publisher = "publisher";
    String year = "year";
    String price = "price";
    String status = "status";

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
        BookAPIFile bookAPIFile = new BookAPIFile();

        id = "";
        price = "";
        status = "";

        book.setSellerId(id);
        book.setPrice(price);
        book.setStatus(status);

        ArrayList<Book> readJSONResult = bookAPIFile.readJSON(this.filePath);
        Book bookResult = readJSONResult.get(0);

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
}
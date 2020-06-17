package com.AllyHyeseongKim.usedbookmarketplace.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

class BookTest {
    Book book = new Book();

    String id = "id";
    String ISBN = "ISBN";
    String title = "title";
    String author = "author";
    String publisher = "publisher";
    String year = "2020";
    String price = "20000";
    String status = "Excellent";
    String information = "* Book Title: " + title + "\n* ISBN: " + ISBN + "\n* Author: " + author + "\n* Publisher: " + publisher + "\n* Publish Year: " + year;

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
    }

    @Test
    void setSellerId() {
        String modifiedId = "modified id";
        book.setSellerId(modifiedId);
        String idResult = book.getSellerId();

        assertThat(idResult, is(modifiedId));
    }

    @Test
    void getSellerId() {
        String idResult = book.getSellerId();

        assertThat(idResult, is(id));
    }

    @Test
    void setISBN() {
        String modifiedISBN = "modified ISBN";
        book.setISBN(modifiedISBN);
        String ISBNResult = book.getISBN();

        assertThat(ISBNResult, is(modifiedISBN));
    }

    @Test
    void getISBN() {
        String ISBNResult = book.getISBN();

        assertThat(ISBNResult, is(ISBN));
    }

    @Test
    void setTitle() {
        String modifiedTitle = "modified title";
        book.setTitle(modifiedTitle);
        String titleResult = book.getTitle();

        assertThat(titleResult, is(modifiedTitle));
    }

    @Test
    void getTitle() {
        String titleResult = book.getTitle();

        assertThat(titleResult, is(title));
    }

    @Test
    void setAuthor() {
        String modifiedAuthor = "modified author";
        book.setAuthor(modifiedAuthor);
        String authorResult = book.getAuthor();

        assertThat(authorResult, is(modifiedAuthor));
    }

    @Test
    void getAuthor() {
        String authorResult = book.getAuthor();

        assertThat(authorResult, is(author));
    }

    @Test
    void setPublisher() {
        String modifiedPublisher = book.getPublisher();
        book.setPublisher(modifiedPublisher);
        String publisherResult = book.getPublisher();

        assertThat(publisherResult, is(modifiedPublisher));
    }

    @Test
    void getPublisher() {
        String publisherResult = book.getPublisher();

        assertThat(publisherResult, is(publisher));
    }

    @Test
    void setYear() {
        String modifiedYear = "2000";
        book.setYear(modifiedYear);
        String yearResult = book.getYear();

        assertThat(yearResult, is(modifiedYear));
    }

    @Test
    void getYear() {
        String yearResult = book.getYear();

        assertThat(yearResult, is(year));
    }

    @Test
    void setPrice() {
        String modifiedPrice = "10000";
        book.setPrice(modifiedPrice);
        String priceResult = book.getPrice();

        assertThat(priceResult, is(modifiedPrice));
    }

    @Test
    void getPrice() {
        String priceResult = book.getPrice();

        assertThat(priceResult, is(price));
    }

    @Test
    void setStatus() {
        String modifiedStatus = "Good";
        book.setStatus(modifiedStatus);
        String statusResult = book.getStatus();

        assertThat(statusResult, is(modifiedStatus));
    }

    @Test
    void getStatus() {
        String statusResult = book.getStatus();

        assertThat(statusResult, is(status));
    }

    @Test
    void information() {
        String informationResult = book.information();

        assertThat(informationResult, is(information));
    }
}
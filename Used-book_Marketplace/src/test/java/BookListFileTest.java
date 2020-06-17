import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class BookListFileTest {
    @org.junit.jupiter.api.Test
    void readJSON() {
        BookListFile bookListFile = new BookListFile("data/test-book.json");

        BookList bookList = new BookList();

        Book book = new Book();

        String id = "id";
        String ISBN = "ISBN";
        String title = "title";
        String author = "author";
        String publisher = "publisher";
        String year = "year";
        String price = "price";
        String status = "status";

        book.setSellerId(id);
        book.setISBN(ISBN);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setYear(year);
        book.setPrice(price);
        book.setStatus(status);

        bookList.addBook(book);

        bookListFile.writeJSON(bookList);
        BookList readJSONResult = bookListFile.readJSON();
        Book bookResult = readJSONResult.getBook(0);

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

    @org.junit.jupiter.api.Test
    void writeJSON() {
        BookListFile bookListFile = new BookListFile("data/test-book.json");

        BookList bookList = new BookList();

        Book book = new Book();

        String id = "id";
        String ISBN = "ISBN";
        String title = "title";
        String author = "author";
        String publisher = "publisher";
        String year = "year";
        String price = "price";
        String status = "status";

        book.setSellerId(id);
        book.setISBN(ISBN);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setYear(year);
        book.setPrice(price);
        book.setStatus(status);

        bookList.addBook(book);

        bookListFile.writeJSON(bookList);

        id = "";
        price = "";
        status = "";

        book.setSellerId(id);
        book.setPrice(price);
        book.setStatus(status);

        BookList readJSONResult = bookListFile.readJSON();
        Book bookResult = readJSONResult.getBook(0);

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

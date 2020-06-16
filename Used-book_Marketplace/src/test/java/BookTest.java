import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class BookTest {

    @Test
    public void test_add() {
        Book book = new Book();

        String id = "id";
        String ISBN = "ISBN";
        String title = "title";
        String author = "author";
        String publisher = "publisher";
        String year = "year";
        String price = "price";
        String status = "status";
        String information = "* Book Title: " + title + "\n* ISBN: " + ISBN + "\n* Author: " + author + "\n* Publisher: " + publisher + "\n* Publish Year: " + year;

        book.setSellerId(id);
        book.setISBN(ISBN);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setYear(year);
        book.setPrice(price);
        book.setStatus(status);

        String idResult = book.getSellerId();
        String ISBNResult = book.getISBN();
        String titleResult = book.getTitle();
        String authorResult = book.getAuthor();
        String publisherResult = book.getPublisher();
        String yearResult = book.getYear();
        String priceResult = book.getPrice();
        String statusResult = book.getStatus();
        String informationResult = book.information();

        assertThat(idResult, is(id));
        assertThat(ISBNResult, is(ISBN));
        assertThat(titleResult, is(titleResult));
        assertThat(authorResult, is(author));
        assertThat(publisherResult, is(publisher));
        assertThat(yearResult, is(year));
        assertThat(priceResult, is(price));
        assertThat(statusResult, is(status));
        assertThat(informationResult, is(information));
    }
}

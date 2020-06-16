import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class BookListFileTest {
    @Test
    public void test_add() {
        BookListFile bookListFile = new BookListFile("data/test-user.json");

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

        assertThat(readJSONResult, is(bookList));
    }
}

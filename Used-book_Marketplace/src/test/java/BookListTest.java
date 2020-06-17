import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class BookListTest {
    BookList bookList = new BookList();

    Book book = new Book();

    String id = "id";
    String ISBN = "111111";
    String title = "title";
    String author = "author";
    String publisher = "publisher";
    String year = "2000";
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

        bookList.addBook(book);
    }

    @org.junit.jupiter.api.Test
    void getBook() {
        Book bookResult = bookList.getBook(0);

        assertThat(bookResult, is(book));
    }

    @org.junit.jupiter.api.Test
    void findIndex() {
        int findIndexResult = bookList.findIndex(book);

        assertThat(findIndexResult, is(0));
    }

    @org.junit.jupiter.api.Test
    void getNumBooks() {
        int getNumBooksResult = bookList.getNumBooks();

        assertThat(getNumBooksResult, is(1));
    }

    @org.junit.jupiter.api.Test
    void searchBooks() {

    }

    @org.junit.jupiter.api.Test
    void searchISBN() {
    }

    @org.junit.jupiter.api.Test
    void addBook() {
    }

    @org.junit.jupiter.api.Test
    void modifyBook() {
    }

    @org.junit.jupiter.api.Test
    void deleteBook() {
    }
}

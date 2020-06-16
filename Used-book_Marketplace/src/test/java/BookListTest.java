import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class BookListTest {
    @Test
    public void test_add() {
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

        Book getBookResult = bookList.getBook(0);
        int findIndexResult = bookList.findIndex(book);
        int getNumBooksResult = bookList.getNumBooks();
        BookList searchBooksResult1 = bookList.searchBooks("Title", title);
        BookList searchBooksResult2 = bookList.searchBooks("ISBN", ISBN);
        BookList searchBooksResult3 = bookList.searchBooks("Author", author);
        BookList searchBooksResult4 = bookList.searchBooks("Publisher", publisher);
        BookList searchBooksResult5 = bookList.searchBooks("Year", year);
        BookList searchBooksResult6 = bookList.searchBooks("Seller Id", id);
        Book searchISBNResult = bookList.searchISBN(book);

        BookList modifiedBookList = bookList;
        Book modifiedBook = book;
        modifiedBook.setTitle("Title Modified");
        modifiedBookList.modifyBook(0, modifiedBook);
        Book modifiedBookResult = modifiedBookList.getBook(0);

        BookList deletedBookList = bookList;
        deletedBookList.addBook(modifiedBook);
        deletedBookList.deleteBook(1);

        assertThat(getBookResult, is(book));
        assertThat(findIndexResult, is(0));
        assertThat(getNumBooksResult, is(1));
        assertThat(searchBooksResult1, is(bookList));
        assertThat(searchBooksResult2, is(bookList));
        assertThat(searchBooksResult3, is(bookList));
        assertThat(searchBooksResult4, is(bookList));
        assertThat(searchBooksResult5, is(bookList));
        assertThat(searchBooksResult6, is(bookList));
        assertThat(searchISBNResult, is(book));
        assertThat(modifiedBookResult, is(modifiedBook));
        assertThat(deletedBookList, is(bookList));
    }
}

import java.util.ArrayList;


public class BookList {

    private ArrayList<Book> bookList = new ArrayList<Book>();

    public Book getBook(int index) {
        try {
            return bookList.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void addBook(Book addedBook) {
        bookList.add(addedBook);
    }

    public void modifyBook(int index, Book modifiedBook) {
        try {
            bookList.set(index, modifiedBook);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public void deleteBook(int index) {
        try {
            bookList.remove(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void printBookList() {
        for (int i = 0; i < bookList.size(); i++) {
            bookList.get(i).printBookInformation();
        }
    }


}

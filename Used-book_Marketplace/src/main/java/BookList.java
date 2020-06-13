import java.util.ArrayList;


public class BookList {

    private ArrayList<Book> bookList = new ArrayList<Book>();

    public Book getBook(int index) {
        try {
            return this.bookList.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public int getNumBooks() {
        return this.bookList.size();
    }

    public BookList searchBook(String searchFilter, String searchString) {
        BookList searchedBookList = new BookList();

        if (searchFilter == "name") {
            for (Book book : this.bookList) {
                if (book.getName() == searchString) {
                    searchedBookList.addBook(book);
                }
            }
        } else if (searchFilter == "ISBN") {
            for (Book book : this.bookList) {
                if (book.getISBN() == searchString) {
                    searchedBookList.addBook(book);
                }
            }
        } else if (searchFilter == "author") {
            for (Book book : this.bookList) {
                if (book.getAuthor() == searchString) {
                    searchedBookList.addBook(book);
                }
            }
        } else if (searchFilter == "publisher") {
            for (Book book : this.bookList) {
                if (book.getPublisher() == searchString) {
                    searchedBookList.addBook(book);
                }
            }
        } else if (searchFilter == "year") {
            for (Book book : this.bookList) {
                if (book.getYear() == searchString) {
                    searchedBookList.addBook(book);
                }
            }
        } else if (searchFilter == "sellerId") {
            for (Book book : this.bookList) {
                if (book.getSellerId() == searchString) {
                    searchedBookList.addBook(book);
                }
            }
        } else {
            System.out.println("Wrong Searching Filter");
        }

        return searchedBookList;
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

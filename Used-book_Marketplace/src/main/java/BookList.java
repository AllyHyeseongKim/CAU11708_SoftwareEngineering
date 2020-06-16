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

    public int findIndex(Book book) {
        return this.bookList.indexOf(book);
    }

    public int getNumBooks() {
        return this.bookList.size();
    }

    public BookList searchBooks(String searchFilter, String searchString) {
        BookList searchedBookList = new BookList();

        if (searchFilter.equals("Title")) {
            for (Book book : this.bookList) {
                if (book.getTitle().equals(searchString)) {
                    searchedBookList.addBook(book);
                    book.printBookInformation();
                }
            }
        } else if (searchFilter.equals("ISBN")) {
            for (Book book : this.bookList) {
                if (book.getISBN().equals(searchString)) {
                    searchedBookList.addBook(book);
                }
            }
        } else if (searchFilter.equals("Author")) {
            for (Book book : this.bookList) {
                if (book.getAuthor().equals(searchString)) {
                    searchedBookList.addBook(book);
                }
            }
        } else if (searchFilter.equals("Publisher")) {
            for (Book book : this.bookList) {
                if (book.getPublisher().equals(searchString)) {
                    searchedBookList.addBook(book);
                }
            }
        } else if (searchFilter.equals("Year")) {
            for (Book book : this.bookList) {
                if (book.getYear().equals(searchString)) {
                    searchedBookList.addBook(book);
                }
            }
        } else if (searchFilter.equals("Seller Id")) {
            for (Book book : this.bookList) {
                if (book.getSellerId().equals(searchString)) {
                    searchedBookList.addBook(book);
                }
            }
        } else {
            System.out.println("Wrong Searching Filter");
        }

        return searchedBookList;
    }

    public Book searchISBN(Book searchBook) {
        for (Book book : this.bookList) {
            if (searchBook.getAuthor().equals("") && searchBook.getPublisher().equals("")) {
                if (book.getTitle().equals(searchBook.getTitle())) {
                    return book;
                }
            } else if (searchBook.getAuthor().equals("")) {
                if (book.getTitle().equals(searchBook.getTitle()) && book.getPublisher().equals(searchBook.getPublisher())) {
                    return book;
                }
            } else if (searchBook.getAuthor().equals("")) {
                if (book.getTitle().equals(searchBook.getTitle()) && book.getPublisher().equals(searchBook.getPublisher())) {
                    return book;
                }
            } else {
                if (book.getTitle().equals(searchBook.getTitle()) && book.getAuthor().equals(searchBook.getAuthor()) && book.getPublisher().equals(searchBook.getPublisher())) {
                    return book;
                }
            }
        }
        return null;
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

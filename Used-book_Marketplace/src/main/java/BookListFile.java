import java.io.*;
import java.util.Scanner;


public class BookListFile {

    private BookList bookList = new BookList();

    public static String filePath;

    public BookList readCSV(String filePath) {

        BookListFile.filePath = filePath;

        Scanner inputCSV = scanFile(filePath);

        if (inputCSV != null) {
            while (inputCSV.hasNext()) {
                String line = inputCSV.nextLine();
                Book book = parser(line);
                if (book != null) {
                    try {
                        bookList.addBook(book);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }
            }
            inputCSV.close();
            return bookList;
        } else {
            System.out.println("Empty CSV");
            return bookList;
        }
    }

    private Scanner scanFile(String filePath) {

        try {
            // make a file instance
            File file = new File(filePath);
            return new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Book parser(String line) {

        Book book = null;
        if (!isHeaderLine(line) && !isEmptyLine(line)) {
            String bookInformation[] = line.split(",");

            book = new Book();

            book.setSellerId(bookInformation[0]);
            book.setISBN(bookInformation[1]);
            book.setName(bookInformation[2]);
            book.setAuthor(bookInformation[3]);
            book.setPublisher(bookInformation[4]);
            book.setYear(bookInformation[5]);
            book.setCost(bookInformation[6]);
            book.setStatus(bookInformation[7]);

            return book;
        } else {
            return null;
        }
    }

    private boolean isHeaderLine(String line) {
        String content[] = line.split(",");
        if (content[1] == "ISBN") {
            return true;
        } else {
            return false;
        }
    }

    private boolean isEmptyLine(String line) {
        if (line == null) {
            return true;
        } else {
            return false;
        }
    }
}

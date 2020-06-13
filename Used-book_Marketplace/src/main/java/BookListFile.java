import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class BookListFile {

    private BookList bookList = new BookList();

    public static String filePath;

    public BookList readJSON(String filePath) {
        this.filePath = filePath;

        JSONParser parser = new JSONParser();
        Book book = null;

        try {
            JSONArray books = (JSONArray) parser.parse(new FileReader(filePath));
            if (books != null) {
                for (Object object : books) {
                    JSONObject bookObject = (JSONObject) object;

                    book = parser(bookObject);
                    bookList.addBook(book);
                }
            } else {
                System.out.println("Empty json");
                return bookList;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Book parser(JSONObject jsonObject) {
        Book book = new Book();

        book.setSellerId((String) jsonObject.get("sellerId"));
        book.setISBN((String) jsonObject.get("ISBN"));
        book.setName((String) jsonObject.get("name"));
        book.setAuthor((String) jsonObject.get("author"));
        book.setPublisher((String) jsonObject.get("publisher"));
        book.setYear((String) jsonObject.get("year"));
        book.setCost((String) jsonObject.get("cost"));
        book.setStatus((String) jsonObject.get("status"));

        book.printBookInformation();

        return book;
    }
}
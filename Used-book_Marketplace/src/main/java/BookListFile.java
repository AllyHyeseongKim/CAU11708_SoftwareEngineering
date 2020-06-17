import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class BookListFile {

    private BookList bookList = new BookList();

    public static String filePath;

    public BookListFile(String filePath) {
        this.filePath = filePath;
    }

    public BookList readJSON() {

        JSONParser parser = new JSONParser();

        try {
            JSONArray books = (JSONArray) parser.parse(new FileReader(filePath));
            if (books != null) {
                for (Object object : books) {
                    JSONObject bookObject = (JSONObject) object;

                    Book book = parser(bookObject);
                    bookList.addBook(book);
                }
            } else {
                System.out.println("Empty json");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    private Book parser(JSONObject jsonObject) {
        Book book = new Book();

        book.setSellerId((String) jsonObject.get("sellerId"));
        book.setISBN((String) jsonObject.get("ISBN"));
        book.setTitle((String) jsonObject.get("title"));
        book.setAuthor((String) jsonObject.get("author"));
        book.setPublisher((String) jsonObject.get("publisher"));
        book.setYear((String) jsonObject.get("year"));
        book.setPrice((String) jsonObject.get("price"));
        book.setStatus((String) jsonObject.get("status"));

        return book;
    }

    public void writeJSON(BookList bookList) {
        this.bookList = bookList;

        JSONArray books = new JSONArray();

        for (int i = 0; i < this.bookList.getNumBooks(); i++) {
            Book book = this.bookList.getBook(i);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sellerId", book.getSellerId());
            jsonObject.put("ISBN", book.getISBN());
            jsonObject.put("title", book.getTitle());
            jsonObject.put("author", book.getAuthor());
            jsonObject.put("publisher", book.getPublisher());
            jsonObject.put("year", book.getYear());
            jsonObject.put("price", book.getPrice());
            jsonObject.put("status", book.getStatus());

            books.add(jsonObject);
        }

        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            fileWriter.write(books.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
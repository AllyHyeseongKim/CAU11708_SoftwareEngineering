package com.AllyHyeseongKim.usedbookmarketplace.controller;

import com.AllyHyeseongKim.usedbookmarketplace.model.Book;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class BookAPIFile {

    private ArrayList<Book> bookList = new ArrayList<>();

    public static String filePath;

    public ArrayList<Book> readJSON(String filePath) {
        this.filePath = filePath;

        JSONParser parser = new JSONParser();

        try {
            JSONArray books = (JSONArray) parser.parse(new FileReader(filePath));
            if (books != null) {
                for (Object object : books) {
                    JSONObject bookObject = (JSONObject) object;

                    Book book = parser(bookObject);
                    this.bookList.add(book);
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
        return this.bookList;
    }

    private Book parser(JSONObject jsonObject) {
        Book book = new Book();

        book.setSellerId("");
        book.setStatus("");
        book.setPrice("");
        book.setISBN((String) jsonObject.get("ISBN"));
        book.setTitle((String) jsonObject.get("title"));
        book.setAuthor((String) jsonObject.get("author"));
        book.setPublisher((String) jsonObject.get("publisher"));
        book.setYear((String) jsonObject.get("year"));

        return book;
    }
}

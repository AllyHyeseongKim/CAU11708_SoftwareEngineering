package com.AllyHyeseongKim.usedbookmarketplace.view;

import com.AllyHyeseongKim.usedbookmarketplace.view.ButtonColumn;
import com.AllyHyeseongKim.usedbookmarketplace.model.Book;
import com.AllyHyeseongKim.usedbookmarketplace.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;


public class BookInformationPanel extends JPanel {
    private ArrayList<Book> bookList;
    private ArrayList<Book> userBookList;
    private ArrayList<User> userList;

    private String headers[] = {"Seller Id", "ISBN", "Book Title", "Author", "Publisher", "Published Year", "Price (Won)", "Status", ""};
    private Object data[][];
    public DefaultTableModel defaultTableModel = new DefaultTableModel(data, headers);
    public JTable jTable = new JTable(defaultTableModel);

    private Action deleteBookAction;
    public Action deleteUserBookAction;
    public Action modifyBookAction;
    public Action purchaseBookAction;

    // [Admin : Book Management] Only Delete
    public BookInformationPanel(ArrayList<Book> bookList, Action deleteBookAction) {
        this.bookList = bookList;

        this.deleteBookAction = deleteBookAction;

        this.setLayout(new GridLayout(1, 1));

        ButtonColumn deleteButtonColumn = new ButtonColumn(jTable, this.deleteBookAction, 8);
        JScrollPane jScrollPane = new JScrollPane(jTable);

        for (int i = 0; i < this.bookList.size(); i++) {
            Object[] object = new Object[9];
            Book book = this.bookList.get(i);

            object[0] = book.getSellerId();
            object[1] = book.getISBN();
            object[2] = book.getTitle();
            object[3] = book.getAuthor();
            object[4] = book.getPublisher();
            object[5] = book.getYear();
            object[6] = book.getPrice();
            object[7] = book.getStatus();
            object[8] = "Delete";

            defaultTableModel.addRow(object);
        }
        this.add(jScrollPane);

        this.setVisible(true);
    }

    // [User : Search Book] Only Purchase
    public BookInformationPanel(ArrayList<Book> bookList, ArrayList<User> userList, Action purchaseBookAction) {
        this.bookList = bookList;
        this.userList = userList;

        this.purchaseBookAction = purchaseBookAction;

        this.setLayout(new GridLayout(1, 1));

        ButtonColumn purchaseButtonColumn = new ButtonColumn(jTable, purchaseBookAction, 8);
        JScrollPane jScrollPane = new JScrollPane(jTable);

        for (int i = 0; i < this.bookList.size(); i++) {
            Object[] object = new Object[9];
            Book book = this.bookList.get(i);

            object[0] = book.getSellerId();
            object[1] = book.getISBN();
            object[2] = book.getTitle();
            object[3] = book.getAuthor();
            object[4] = book.getPublisher();
            object[5] = book.getYear();
            object[6] = book.getPrice();
            object[7] = book.getStatus();
            object[8] = "Purchase";

            defaultTableModel.addRow(object);
        }
        this.add(jScrollPane);

        this.setVisible(true);
    }

    // [User : Book Management] Modify & Delete
    public BookInformationPanel(ArrayList<Book> bookList, ArrayList<Book> userBookList, Action modifyBookAction, Action deleteUserBookAction) {
        this.bookList = bookList;
        this.userBookList = userBookList;

        this.modifyBookAction = modifyBookAction;
        this.deleteUserBookAction = deleteUserBookAction;

        this.setLayout(new GridLayout(1, 1));

        defaultTableModel.addColumn("");
        ButtonColumn modifyButtonColumn = new ButtonColumn(jTable, modifyBookAction, 8);
        ButtonColumn deleteButtonColumn = new ButtonColumn(jTable, deleteUserBookAction, 9);
        JScrollPane jScrollPane = new JScrollPane(jTable);

        for (int i = 0; i < this.userBookList.size(); i++) {
            Object[] object = new Object[10];
            Book book = this.userBookList.get(i);

            object[0] = book.getSellerId();
            object[1] = book.getISBN();
            object[2] = book.getTitle();
            object[3] = book.getAuthor();
            object[4] = book.getPublisher();
            object[5] = book.getYear();
            object[6] = book.getPrice();
            object[7] = book.getStatus();
            object[8] = "Modify";
            object[9] = "Delete";

            defaultTableModel.addRow(object);
        }
        this.add(jScrollPane);

        this.setVisible(true);
    }
}

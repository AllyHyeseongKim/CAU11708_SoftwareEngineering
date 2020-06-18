package com.AllyHyeseongKim.usedbookmarketplace.controller;

import com.AllyHyeseongKim.usedbookmarketplace.model.Book;
import com.AllyHyeseongKim.usedbookmarketplace.model.User;
import com.AllyHyeseongKim.usedbookmarketplace.view.AdminView;
import com.AllyHyeseongKim.usedbookmarketplace.view.BookInformationPanel;
import com.AllyHyeseongKim.usedbookmarketplace.view.UserInformationPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class AdminController {
    private AdminView adminView;

    private BookInformationPanel bookInformationPanel;
    private UserInformationPanel userInformationPanel;

    private ArrayList<User> userList;
    private ArrayList<Book> bookList;

    Action deleteBookAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTable jTable = (JTable)e.getSource();
            int row = Integer.valueOf(e.getActionCommand());
            deleteBook(row);
            int okButton = JOptionPane.DEFAULT_OPTION;
            int result = JOptionPane.showConfirmDialog(null, "SUCCESS: Book deleted.", "Success Message", okButton, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {
                ((DefaultTableModel) jTable.getModel()).removeRow(row);
            }
        }
    };

    Action deleteUserAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTable jTable = (JTable)e.getSource();
            int row = Integer.valueOf(e.getActionCommand());
            if (jTable.getModel().getValueAt(row, 4).equals("deactivated")) {
                deleteUser(row);
                int okButton = JOptionPane.DEFAULT_OPTION;
                int result = JOptionPane.showConfirmDialog(null, "SUCCESS: Deactivated user deleted.", "Success Message", okButton, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {
                    ((DefaultTableModel) jTable.getModel()).removeRow(row);
                }
            } else {
                JOptionPane.showMessageDialog(null, "FAILED: Deactivate the activated user first.", "Error Message", JOptionPane.PLAIN_MESSAGE);
            }
        }
    };

    Action changeStatusAction = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            System.out.println("!");
            JTable jTable = (JTable)e.getSource();
            System.out.println("!!");
            int row = Integer.valueOf(e.getActionCommand());
            System.out.println(row);
            if (jTable.getModel().getValueAt(row, 4).equals("activated")) {
                jTable.getModel().setValueAt("deactivated", row, 4);
            } else {
                jTable.getModel().setValueAt("activated", row, 4);
            }
            changeStatus(row);
        }
    };

    public AdminController(ArrayList<User> userList) {
        this.userList = userList;

        BookListFile bookListFile = new BookListFile("data/book.json");
        this.bookList = bookListFile.readJSON();

        this.bookInformationPanel = new BookInformationPanel(this.bookList, this.deleteBookAction);
        this.userInformationPanel = new UserInformationPanel(this.userList, this.bookList, this.deleteUserAction, this.changeStatusAction);
        this.adminView = new AdminView(this.bookInformationPanel, this.userInformationPanel);
    }

    private void deleteBook(int index) {
        BookListFile bookListFile = new BookListFile("data/book.json");

        this.bookList.remove(index);
        bookListFile.writeJSON(this.bookList);
    }

    private void deleteUser(int index) {
        BookListFile bookListFile = new BookListFile("data/book.json");
        UserListFile userListFile = new UserListFile("data/user.json");

        User user = this.userList.get(index);

        if (user.getStatus() == "deactivated") {
            try {
                for (int i = 0; i < this.bookList.size(); i++) {
                    if (this.bookList.get(i).getSellerId() == user.getId()) {
                        this.bookList.remove(i);
                        i--;
                    }
                }
                this.userList.remove(index);
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                throw e;
            }
        }

        bookListFile.writeJSON(this.bookList);
        userListFile.writeJSON(this.userList);
    }

    private void changeStatus(int index) {
        UserListFile userListFile = new UserListFile("data/user.json");

        User user = this.userList.get(index);
        if (user.getStatus().equals("activated")) {
            user.setStatus("deactivated");
        } else {
            user.setStatus("activated");
        }

        try {
            this.userList.set(index, user);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            throw e;
        }

        userListFile.writeJSON(this.userList);
    }
}

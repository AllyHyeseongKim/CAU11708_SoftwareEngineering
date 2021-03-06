package com.AllyHyeseongKim.usedbookmarketplace.controller;

import com.AllyHyeseongKim.usedbookmarketplace.model.Book;
import com.AllyHyeseongKim.usedbookmarketplace.model.User;
import com.AllyHyeseongKim.usedbookmarketplace.view.AdminView;
import com.AllyHyeseongKim.usedbookmarketplace.view.BookInformationPanel;
import com.AllyHyeseongKim.usedbookmarketplace.view.UserInformationPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class AdminController implements ChangeListener, ActionListener {
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

        this.adminView.adminTab.addChangeListener(this);
        this.adminView.searchButton.addActionListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
        int selectedIndex = tabbedPane.getSelectedIndex();
        if (selectedIndex == 0) {
            updateBooks();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.adminView.searchButton)) {
            search();
        }
    }

    private void updateBooks() {
        adminView.searchBookPanel.removeAll();
        this.bookInformationPanel = new BookInformationPanel(this.bookList, this.deleteBookAction);
        adminView.bookInformationPanel = this.bookInformationPanel;
        adminView.addBookManagementPanel();
        adminView.searchBookPanel.revalidate();
        adminView.searchBookPanel.repaint();
    }

    private void search() {
        ArrayList<Book> searchedBookList = this.bookList;

        String selectedFilter = adminView.searchFilter.getSelectedItem().toString();
        String searchedText = adminView.searchTextField.getText();
        if (!searchedText.equals("")) {
            searchedBookList = searchBooks(selectedFilter, searchedText, this.bookList);
        }
        this.bookInformationPanel.removeAll();
        this.bookInformationPanel = new BookInformationPanel(searchedBookList, this.deleteBookAction);
        this.bookInformationPanel.setVisible(true);

        adminView.searchBookPanel.add(this.bookInformationPanel, "Center");
        this.bookInformationPanel.revalidate();
        this.bookInformationPanel.repaint();
    }

    protected ArrayList<Book> searchBooks(String searchFilter, String searchString, ArrayList<Book> bookList) {
        ArrayList<Book> searchedBookList = new ArrayList<>();

        if (searchFilter.equals("Title")) {
            for (Book book : bookList) {
                if (book.getTitle().equals(searchString)) {
                    searchedBookList.add(book);
                }
            }
        } else if (searchFilter.equals("ISBN")) {
            for (Book book : bookList) {
                if (book.getISBN().equals(searchString)) {
                    searchedBookList.add(book);
                }
            }
        } else if (searchFilter.equals("Author")) {
            for (Book book : bookList) {
                if (book.getAuthor().equals(searchString)) {
                    searchedBookList.add(book);
                }
            }
        } else if (searchFilter.equals("Publisher")) {
            for (Book book : bookList) {
                if (book.getPublisher().equals(searchString)) {
                    searchedBookList.add(book);
                }
            }
        } else if (searchFilter.equals("Year")) {
            for (Book book : bookList) {
                if (book.getYear().equals(searchString)) {
                    searchedBookList.add(book);
                }
            }
        } else if (searchFilter.equals("Seller Id")) {
            for (Book book : bookList) {
                if (book.getSellerId().equals(searchString)) {
                    searchedBookList.add(book);
                }
            }
        }

        return searchedBookList;
    }

    private void deleteBook(int index) {
        BookListFile bookListFile = new BookListFile("data/book.json");

        this.bookList.remove(index);
        bookListFile.writeJSON(this.bookList);
    }

    private void deleteUser(int index) {
        User user = this.userList.get(index);

        if (user.getStatus().equals("deactivated")) {
            try {
                for (int i = 0; i < this.bookList.size(); i++) {
                    if (this.bookList.get(i).getSellerId().equals(user.getId())) {
                        this.bookList.remove(i);
                        i--;
                    }
                }
                this.userList.remove(index);

                BookListFile bookListFile = new BookListFile("data/book.json");
                UserListFile userListFile = new UserListFile("data/user.json");
                bookListFile.writeJSON(this.bookList);
                userListFile.writeJSON(this.userList);
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                throw e;
            }
        }
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

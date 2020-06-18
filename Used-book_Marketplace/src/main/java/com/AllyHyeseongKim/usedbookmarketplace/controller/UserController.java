package com.AllyHyeseongKim.usedbookmarketplace.controller;

import com.AllyHyeseongKim.usedbookmarketplace.model.Book;
import com.AllyHyeseongKim.usedbookmarketplace.model.User;
import com.AllyHyeseongKim.usedbookmarketplace.view.AddBookPanel;
import com.AllyHyeseongKim.usedbookmarketplace.view.BookInformationPanel;
import com.AllyHyeseongKim.usedbookmarketplace.view.UserView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class UserController implements ChangeListener, ActionListener {
    private UserView userView;

    private AddBookPanel addBookPanel;
    private BookInformationPanel bookInformationPanel;
    private BookInformationPanel userBookInformationPanel;

    private String userId;

    private ArrayList<User> userList;
    private ArrayList<Book> bookList;
    private ArrayList<Book> userBookList;

    public Action purchaseBookAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTable jTable = (JTable)e.getSource();
            int row = Integer.valueOf(e.getActionCommand());
            String sellerId = (String) jTable.getModel().getValueAt(row, 0);
            purchaseBook(sellerId);
        }
    };

    public Action deleteUserBookAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTable jTable = (JTable)e.getSource();
            int row = Integer.valueOf(e.getActionCommand());
            deleteUserBook(row);
            int okButton = JOptionPane.DEFAULT_OPTION;
            int result = JOptionPane.showConfirmDialog(null, "SUCCESS: Book deleted.", "Success Message", okButton, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {
                ((DefaultTableModel) jTable.getModel()).removeRow(row);
            }
        }
    };

    public Action modifyBookAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = Integer.valueOf(e.getActionCommand());

            BookListFile bookListFile = new BookListFile("data/book.json");

            Book modifiedBook = new Book();

            modifiedBook.setSellerId((String) userBookInformationPanel.jTable.getValueAt(row, 0));
            modifiedBook.setISBN((String) userBookInformationPanel.jTable.getValueAt(row, 1));
            modifiedBook.setTitle((String) userBookInformationPanel.jTable.getValueAt(row, 2));
            modifiedBook.setAuthor((String) userBookInformationPanel.jTable.getValueAt(row, 3));
            modifiedBook.setPublisher((String) userBookInformationPanel.jTable.getValueAt(row, 4));
            modifiedBook.setYear((String) userBookInformationPanel.jTable.getValueAt(row, 5));
            modifiedBook.setPrice((String) userBookInformationPanel.jTable.getValueAt(row, 6));
            modifiedBook.setStatus((String) userBookInformationPanel.jTable.getValueAt(row, 7));

            modifyBook(row, modifiedBook);

            bookListFile.writeJSON(bookList);

            JOptionPane.showMessageDialog(null, "SUCCESS: Book modified.", "Success Message", JOptionPane.PLAIN_MESSAGE);

        }
    };

    public UserController(String userId, ArrayList<User> userList) {
        this.userId = userId;
        this.userList = userList;

        BookListFile bookListFile = new BookListFile("data/book.json");
        this.bookList = bookListFile.readJSON();

        this.userBookList = searchBooks("Seller Id", this.userId, this.bookList);

        this.addBookPanel = new AddBookPanel();
        this.addBookPanel.statusComboBox.addActionListener(this);
        this.addBookPanel.addBookButton.addActionListener(this);

        this.bookInformationPanel = new BookInformationPanel(this.bookList, this.userList, this.purchaseBookAction);
        this.userBookInformationPanel = new BookInformationPanel(this.bookList, this.userBookList, this.modifyBookAction, this.deleteUserBookAction);

        this.userView = new UserView(this.bookInformationPanel, this.userBookInformationPanel, this.addBookPanel);
        this.userView.userTab.addChangeListener(this);
        this.userView.searchButton.addActionListener(this);
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
        if (e.getSource().equals(this.userView.searchButton)) {
            search();
        } else if(e.getSource().equals(this.addBookPanel.addBookButton)) {
            if (this.addBookPanel.titleTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "FAILED: Please enter the title of the book.", "Error Message", JOptionPane.PLAIN_MESSAGE);
            } else {
                Book book = new Book();
                book.setSellerId("");
                book.setISBN("");
                book.setTitle(this.addBookPanel.titleTextField.getText());
                book.setAuthor(this.addBookPanel.authorTextField.getText());
                book.setPublisher(this.addBookPanel.publisherTextField.getText());
                book.setYear(this.addBookPanel.yearTextField.getText());
                book.setPrice(this.addBookPanel.priceTextField.getText());
                book.setStatus((String) this.addBookPanel.statusComboBox.getSelectedItem());

                Book searchedBook = searchAPI(book);

                String input = null;
                if (searchedBook != null) {
                    int yesNoButton = JOptionPane.YES_NO_OPTION;
                    String message = "RECOMMENDATION:\n" + searchedBook.information() + "\nIs it a right information?";
                    int result = JOptionPane.showConfirmDialog(null, message, "Information Check", yesNoButton, JOptionPane.PLAIN_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        int okButton = JOptionPane.DEFAULT_OPTION;
                        int result2 = JOptionPane.showConfirmDialog(null, "SUCCESS: Book added.", "Success Message", okButton, JOptionPane.PLAIN_MESSAGE);
                        if (result2 == JOptionPane.OK_OPTION || result2 == JOptionPane.CLOSED_OPTION) {
                            searchedBook.setSellerId(this.userId);
                            searchedBook.setPrice(book.getPrice());
                            searchedBook.setStatus(book.getStatus());
                            this.bookList = addBook(searchedBook);

                            BookListFile bookListFile = new BookListFile("data/book.json");
                            bookListFile.writeJSON(this.bookList);
                        }
                    } else if (result == JOptionPane.NO_OPTION) {
                        input = JOptionPane.showInputDialog("ENTER ISBN: Enter the ISBN number.\nIf you don't want to enter the ISBN number, just click the Cancel button.", "ISBN");
                    }
                } else {
                    input = JOptionPane.showInputDialog("ENTER ISBN: No recommendation found.\nIf you don't want to enter the ISBN number, just click the Cancel button.", "ISBN");
                }
                book.setSellerId(this.userId);
                book.setStatus(book.getStatus());
                if (input != null) {
                    book.setISBN(input);
                } else {
                    book.setISBN("");
                }
                this.bookList = addBook(book);

                BookListFile bookListFile = new BookListFile("data/book.json");
                bookListFile.writeJSON(this.bookList);

                this.addBookPanel.titleTextField.setText("");
                this.addBookPanel.authorTextField.setText("");
                this.addBookPanel.publisherTextField.setText("");
                this.addBookPanel.yearTextField.setText("");
                this.addBookPanel.priceTextField.setText("");
                this.addBookPanel.statusComboBox.setSelectedItem("Excellent");
            }
        }
    }

    private void updateBooks() {
        userView.searchBookPanel.removeAll();
        this.bookInformationPanel = new BookInformationPanel(this.bookList, this.userList, this.purchaseBookAction);
        userView.bookInformationPanel = this.bookInformationPanel;
        userView.addSearchBookPanel();
        userView.searchBookPanel.revalidate();
        userView.searchBookPanel.repaint();
    }

    private void deleteUserBook(int row) {
        BookListFile bookListFile = new BookListFile("data/book.json");

        Book book = this.userBookList.get(row);
        int index = this.bookList.indexOf(book);

        this.userBookList.remove(row);
        this.bookList.remove(index);
        bookListFile.writeJSON(this.bookList);
    }

    protected ArrayList<Book> addBook(Book searchedBook) {
        this.userBookList.add(searchedBook);
        this.bookList.add(searchedBook);

        Object[] object = new Object[10];

        object[0] = searchedBook.getSellerId();
        object[1] = searchedBook.getISBN();
        object[2] = searchedBook.getTitle();
        object[3] = searchedBook.getAuthor();
        object[4] = searchedBook.getPublisher();
        object[5] = searchedBook.getYear();
        object[6] = searchedBook.getPrice();
        object[7] = searchedBook.getStatus();
        object[8] = "Modify";
        object[9] = "Delete";

        this.userBookInformationPanel.defaultTableModel.addRow(object);

        return this.bookList;
    }

    protected Book searchAPI(Book searchBook) {
        BookAPIFile bookAPIFile = new BookAPIFile();
        ArrayList<Book> APIBookList = bookAPIFile.readJSON("data/bookAPI.json");

        for (Book book : APIBookList) {
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

    private void purchaseBook(String sellerId) {
        User seller = searchUser(sellerId);
        User user = searchUser(this.userId);
        JOptionPane.showMessageDialog(null, "PURCHASE: Email sent to seller (" + seller.getEmailAddress() + ").\n (Sender: " + user.getEmailAddress() + ")", "Success Message", JOptionPane.PLAIN_MESSAGE);
    }

    protected User searchUser(String id) {
        User searchedUser = null;

        for (User user : this.userList) {
            if (user.getId().equals(id)) {
                searchedUser = user;
            }
        }
        return searchedUser;
    }

    protected Book modifyBook(int row, Book modifiedBook) {
        Book book = this.userBookList.get(row);
        int index = this.bookList.indexOf(book);

        try {
            this.userBookList.set(row, modifiedBook);
            this.bookList.set(index, modifiedBook);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            throw e;
        }

        return this.bookList.get(index);
    }

    private void search() {
        ArrayList<Book> searchedBookList = this.bookList;

        String selectedFilter = userView.searchFilter.getSelectedItem().toString();
        String searchedText = userView.searchTextField.getText();
        if (!searchedText.equals("")) {
            searchedBookList = searchBooks(selectedFilter, searchedText, this.bookList);
        }
        this.bookInformationPanel.removeAll();
        this.bookInformationPanel = new BookInformationPanel(searchedBookList, this.userList, this.purchaseBookAction);
        this.bookInformationPanel.setVisible(true);

        userView.searchBookPanel.add(this.bookInformationPanel, "Center");
        this.bookInformationPanel.revalidate();
        this.bookInformationPanel.repaint();
    }
}

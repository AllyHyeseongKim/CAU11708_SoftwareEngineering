import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;


public class BookInformationPanel extends JPanel {
    private BookList bookList;
    private BookList userBookList;
    private UserList userList;

    private String userId;

    private String headers[] = {"Seller Id", "ISBN", "Book Title", "Author", "Publisher", "Published Year", "Price (Won)", "Status", ""};
    private Object data[][];
    private DefaultTableModel defaultTableModel = new DefaultTableModel(data, headers);
    private JTable jTable = new JTable(defaultTableModel);

    Action deleteBook = new AbstractAction() {
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

    Action deleteUserBook = new AbstractAction() {
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

    Action modifyBook = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTable jTable = (JTable)e.getSource();
            int row = Integer.valueOf(e.getActionCommand());
            modifyBook(row);
        }
    };

    Action purchaseBook = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = Integer.valueOf(e.getActionCommand());
            String sellerId = (String) jTable.getModel().getValueAt(row, 0);
            purchaseBook(sellerId);
        }
    };

    // [Admin : Book Management] Only Delete
    public BookInformationPanel(BookList bookList) {
        this.bookList = bookList;

        this.setLayout(new GridLayout(1, 1));

        ButtonColumn deleteButtonColumn = new ButtonColumn(jTable, deleteBook, 8);
        JScrollPane jScrollPane = new JScrollPane(jTable);

        for (int i = 0; i < bookList.getNumBooks(); i++) {
            Object[] object = new Object[9];
            Book book = bookList.getBook(i);

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
    public BookInformationPanel(BookList bookList, UserList userList, String userId) {
        this.bookList = bookList;
        this.userList = userList;

        this.userId = userId;

        this.setLayout(new GridLayout(1, 1));

        ButtonColumn purchaseButtonColumn = new ButtonColumn(jTable, purchaseBook, 8);
        JScrollPane jScrollPane = new JScrollPane(jTable);

        for (int i = 0; i < bookList.getNumBooks(); i++) {
            Object[] object = new Object[9];
            Book book = bookList.getBook(i);

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
    public BookInformationPanel(BookList bookList, BookList userBookList, String userId) {
        this.bookList = bookList;
        this.userBookList = userBookList;

        this.userId = userId;

        this.setLayout(new GridLayout(1, 1));

        defaultTableModel.addColumn("");
        ButtonColumn modifyButtonColumn = new ButtonColumn(jTable, modifyBook, 8);
        ButtonColumn deleteButtonColumn = new ButtonColumn(jTable, deleteUserBook, 9);
        JScrollPane jScrollPane = new JScrollPane(jTable);

        for (int i = 0; i < userBookList.getNumBooks(); i++) {
            Object[] object = new Object[10];
            Book book = userBookList.getBook(i);

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

    private void deleteBook(int index) {
        BookListFile bookListFile = new BookListFile("data/book.json");

        this.bookList.deleteBook(index);
        bookListFile.writeJSON(this.bookList);
    }

    private void deleteUserBook(int row) {
        // TODO: [Issue] 추천 ISBN으로 add book 혹은 ISBN 없이 add book 후 바로 delete 할 때 book의 index가 -1
        BookListFile bookListFile = new BookListFile("data/book.json");

        Book book = this.userBookList.getBook(row);
        int index = this.bookList.findIndex(book);

        this.userBookList.deleteBook(row);
        this.bookList.deleteBook(index);
        bookListFile.writeJSON(this.bookList);
    }

    private void purchaseBook(String sellerId) {
        User seller = this.userList.searchUser(sellerId);
        User user = this.userList.searchUser(userId);
        JOptionPane.showMessageDialog(null, "PURCHASE: Email sent to seller (" + seller.getEmailAddress() + ").\n (Sender: " + user.getEmailAddress() + ")", "Success Message", JOptionPane.PLAIN_MESSAGE);
    }

    private void modifyBook(int row) {
        BookListFile bookListFile = new BookListFile("data/book.json");

        Book book = this.userBookList.getBook(row);
        int index = this.bookList.findIndex(book);

        Book modifiedBook = new Book();

        modifiedBook.setSellerId((String)jTable.getValueAt(row, 0));
        modifiedBook.setISBN((String)jTable.getValueAt(row, 1));
        modifiedBook.setTitle((String)jTable.getValueAt(row, 2));
        modifiedBook.setAuthor((String)jTable.getValueAt(row, 3));
        modifiedBook.setPublisher((String)jTable.getValueAt(row, 4));
        modifiedBook.setYear((String)jTable.getValueAt(row, 5));
        modifiedBook.setPrice((String)jTable.getValueAt(row, 6));
        modifiedBook.setStatus((String)jTable.getValueAt(row, 7));

        this.userBookList.modifyBook(row, modifiedBook);
        this.bookList.modifyBook(index, modifiedBook);

        bookListFile.writeJSON(this.bookList);

        JOptionPane.showMessageDialog(null, "SUCCESS: Book modified.", "Success Message", JOptionPane.PLAIN_MESSAGE);
    }

    public Book addBook(Book book) {
        BookAPIFile bookAPIFile = new BookAPIFile();
        BookList APIBookList = bookAPIFile.readJSON("data/bookAPI.json");
        Book searchedBook = APIBookList.searchISBN(book);

        String input = null;
        if (searchedBook != null) {
            int yesNoButton = JOptionPane.YES_NO_OPTION;
            String message = "RECOMMENDATION:\n" + searchedBook.information() + "\nIs it a right information?";
            int result = JOptionPane.showConfirmDialog(null, message, "Information Check", yesNoButton, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                int okButton = JOptionPane.DEFAULT_OPTION;
                int result2 = JOptionPane.showConfirmDialog(null, "SUCCESS: Book added.", "Success Message", okButton, JOptionPane.PLAIN_MESSAGE);
                if (result2 == JOptionPane.OK_OPTION || result2 == JOptionPane.CLOSED_OPTION) {
                    BookListFile bookListFile = new BookListFile("data/book.json");

                    searchedBook.setSellerId(this.userId);
                    searchedBook.setPrice(book.getPrice());
                    searchedBook.setStatus(book.getStatus());
                    this.userBookList.addBook(searchedBook);
                    this.bookList.addBook(searchedBook);
                    bookListFile.writeJSON(this.bookList);

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

                    defaultTableModel.addRow(object);
                }
                return searchedBook;
            } else if (result == JOptionPane.NO_OPTION) {
                input = JOptionPane.showInputDialog("ENTER ISBN: Enter the ISBN number.\nIf you don't want to enter the ISBN number, just click the Cancel button.", "ISBN");
            }
        } else {
            input = JOptionPane.showInputDialog("ENTER ISBN: No recommendation found.\nIf you don't want to enter the ISBN number, just click the Cancel button.", "ISBN");
        }
        BookListFile bookListFile = new BookListFile("data/book.json");

        book.setSellerId(this.userId);
        book.setStatus(book.getStatus());
        if (input != null) {
            book.setISBN(input);
        } else {
            book.setISBN("");
        }
        this.userBookList.addBook(book);
        this.bookList.addBook(book);
        bookListFile.writeJSON(this.bookList);

        Object[] object = new Object[10];

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

        this.defaultTableModel.addRow(object);

        return book;
    }
}

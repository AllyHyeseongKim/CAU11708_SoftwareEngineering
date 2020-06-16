import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserFrame extends JFrame{
    private String userId;
    private BookList bookList;
    private UserList userList;

    private JPanel searchBookPanel = new JPanel();
    private JPanel searchPanel = new JPanel();
    private JPanel bookInformationPanel = new JPanel();
    private JPanel bookManagementPanel = new JPanel();

    private JTabbedPane userTab = new JTabbedPane();

    private String filters[] = {"Title", "ISBN", "Author", "Publisher", "Year", "Seller Id"};
    private JComboBox<String> searchFilter = new JComboBox<String>(filters);
    private JTextField searchTextField = new JTextField();
    private JButton searchButton = new JButton("Search");

    public UserFrame(String userId, BookList bookList, UserList userList) {
        this.userId = userId;
        this.userList = userList;
        this.bookList = bookList;

        this.setLayout(new BorderLayout());
        this.setSize(1330, 1000);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("[User Page] Used-book Marketplace");
        this.addSearchBookPanel();
        this.addBookManagementPanel();
        this.addMenuBar();
        this.setVisible(true);
    }

    private void addMenuBar() {
        userTab.add("Search Book", searchBookPanel);
        userTab.add("Book Management", bookManagementPanel);

        userTab.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
                int selectedIndex = tabbedPane.getSelectedIndex();
                if (selectedIndex == 0) {
                    updateBooks();
                }
            }
        });

        this.add(userTab, "Center");
    }

    private void addSearchBookPanel() {
        searchBookPanel.setLayout(new BorderLayout());
        searchBookPanel.setSize(1330, 1000);
        addSearchPanel();
        addBookListPanel(this.bookList);
        searchBookPanel.setVisible(true);
    }

    private void addSearchPanel() {
        searchPanel.setLayout(new BorderLayout());
        searchPanel.setSize(1330, 10);
        searchPanel.add(searchFilter, "West");
        searchPanel.add(searchTextField, "Center");
        searchPanel.add(searchButton, "East");
        searchPanel.setVisible(true);

        searchBookPanel.add(searchPanel, "North");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBooks();
            }
        });
    }

    private void searchBooks() {
        BookList searchedBookList = this.bookList;

        String selectedFilter = searchFilter.getSelectedItem().toString();
        String searchedText = searchTextField.getText();
        if (!searchedText.equals("")) {
            searchedBookList = bookList.searchBooks(selectedFilter, searchedText);
        }
        bookInformationPanel.removeAll();
        addBookListPanel(searchedBookList);
        bookInformationPanel.revalidate();
        bookInformationPanel.repaint();
    }

    private void updateBooks() {
        BookListFile bookListFile = new BookListFile("data/book.json");
        this.bookList = bookListFile.readJSON();
        bookInformationPanel.removeAll();
        addBookListPanel(this.bookList);
        bookInformationPanel.revalidate();
        bookInformationPanel.repaint();
    }

    private void addBookListPanel(BookList searchedBookList) {
        bookInformationPanel.setLayout(new GridLayout(1, 1));
        bookInformationPanel.add(new BookInformationPanel(searchedBookList, this.userList, this.userId));
        bookInformationPanel.setVisible(true);

        searchBookPanel.add(bookInformationPanel, "Center");
    }

    private void addBookManagementPanel() {
        BookList userBookList = this.bookList.searchBooks("Seller Id", this.userId);
        System.out.println("!!!");
        userBookList.printBookList();
        BookInformationPanel bookInformationPanel = new BookInformationPanel(this.bookList, userBookList, this.userId);

        AddBookPanel addBookPanel = new AddBookPanel(bookInformationPanel);
        addBookPanel.setSize(1330, 40);
        addBookPanel.setVisible(true);

        bookManagementPanel.setLayout(new BorderLayout());
        bookManagementPanel.add(bookInformationPanel, "Center");
        bookManagementPanel.add(addBookPanel, "South");
        bookManagementPanel.setVisible(true);
    }
}

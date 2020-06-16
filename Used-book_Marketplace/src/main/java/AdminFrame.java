import javax.swing.*;
import java.awt.*;


public class AdminFrame extends JFrame {
    private UserList userList;
    private BookList bookList;

    private JPanel bookManagementPanel = new JPanel();
    private JPanel userManagementPanel = new JPanel();

    private JTabbedPane adminTab = new JTabbedPane();

    public AdminFrame(UserList userList, BookList bookList) {
        this.userList = userList;
        this.bookList = bookList;

        this.setLayout(new BorderLayout());
        this.setSize(1330, 1000);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("[Admin Page] Used-book Marketplace");
        this.addBookManagementPanel();
        this.addUserManagementPanel();
        this.addMenuBar();
        this.setVisible(true);
    }

    private void addMenuBar() {
        adminTab.setSize(1330, 20);
        adminTab.addTab("Book Management", this.bookManagementPanel);
        adminTab.addTab("User Management", this.userManagementPanel);
        adminTab.setVisible(true);

        this.add(adminTab, "Center");
    }

    private void addBookManagementPanel() {
        bookManagementPanel.setLayout(new GridLayout(1, 1));
        bookManagementPanel.add(new BookInformationPanel(this.bookList));
        bookManagementPanel.setVisible(true);
    }

    private void addUserManagementPanel() {
        userManagementPanel.setLayout(new GridLayout(1, 1));
        userManagementPanel.add(new UserInformationPanel(this.userList, this.bookList));
        userManagementPanel.setVisible(true);
    }
}

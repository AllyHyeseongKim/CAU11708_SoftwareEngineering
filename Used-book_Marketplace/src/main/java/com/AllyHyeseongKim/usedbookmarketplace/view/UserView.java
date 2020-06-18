package com.AllyHyeseongKim.usedbookmarketplace.view;

import javax.swing.*;
import java.awt.*;


public class UserView extends JFrame {
    public JPanel searchBookPanel = new JPanel();
    public JPanel searchPanel = new JPanel();
    public JPanel bookManagementPanel = new JPanel();

    public AddBookPanel addBookPanel;
    public BookInformationPanel bookInformationPanel;
    public BookInformationPanel userBookInformationPanel;

    public JTabbedPane userTab = new JTabbedPane();

    private String filters[] = {"Title", "ISBN", "Author", "Publisher", "Year", "Seller Id"};
    public JComboBox<String> searchFilter = new JComboBox<String>(filters);
    public JTextField searchTextField = new JTextField();
    public JButton searchButton = new JButton("Search");

    public UserView(BookInformationPanel bookInformationPanel, BookInformationPanel userBookInformationPanel, AddBookPanel addBookPanel) {
        this.addBookPanel = addBookPanel;
        this.bookInformationPanel = bookInformationPanel;
        this.userBookInformationPanel = userBookInformationPanel;

        this.setLayout(new BorderLayout());
        this.setSize(1330, 1000);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("[User Page] Used-book Marketplace");
        this.addSearchBookPanel();
        this.addBookManagementPanel();
        this.addTabBar();
        this.setVisible(true);
    }

    private void addTabBar() {
        userTab.add("Search Book", searchBookPanel);
        userTab.add("Book Management", bookManagementPanel);

        this.add(userTab, "Center");
    }

    public void addSearchBookPanel() {
        searchBookPanel.setLayout(new BorderLayout());
        searchBookPanel.setSize(1330, 1000);
        addSearchPanel();
        this.bookInformationPanel.setVisible(true);
        searchBookPanel.add(this.bookInformationPanel, "Center");
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
    }

    private void addBookManagementPanel() {
        addBookPanel.setSize(1330, 40);
        addBookPanel.setVisible(true);

        bookManagementPanel.setLayout(new BorderLayout());
        bookManagementPanel.add(this.userBookInformationPanel, "Center");
        bookManagementPanel.add(addBookPanel, "South");
        bookManagementPanel.setVisible(true);
    }
}

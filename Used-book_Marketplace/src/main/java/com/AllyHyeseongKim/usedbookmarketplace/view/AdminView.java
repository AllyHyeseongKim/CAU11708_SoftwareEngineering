package com.AllyHyeseongKim.usedbookmarketplace.view;

import javax.swing.*;
import java.awt.*;


public class AdminView extends JFrame {
    public JPanel searchBookPanel = new JPanel();
    public JPanel searchPanel = new JPanel();
    private JPanel bookManagementPanel = new JPanel();
    private JPanel userManagementPanel = new JPanel();

    public BookInformationPanel bookInformationPanel;
    public UserInformationPanel userInformationPanel;

    public JTabbedPane adminTab = new JTabbedPane();

    private String filters[] = {"Title", "ISBN", "Author", "Publisher", "Year", "Seller Id"};
    public JComboBox<String> searchFilter = new JComboBox<String>(filters);
    public JTextField searchTextField = new JTextField();
    public JButton searchButton = new JButton("Search");

    public AdminView(BookInformationPanel bookInformationPanel, UserInformationPanel userInformationPanel) {

        this.bookInformationPanel = bookInformationPanel;
        this.userInformationPanel = userInformationPanel;

        this.setLayout(new BorderLayout());
        this.setSize(1330, 1000);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("[Admin Page] Used-book Marketplace");
        this.addBookManagementPanel();
        this.addUserManagementPanel();
        this.addTabBar();
        this.setVisible(true);
    }

    private void addTabBar() {
        adminTab.setSize(1330, 20);
        adminTab.addTab("Book Management", this.searchBookPanel);
        adminTab.addTab("User Management", this.userManagementPanel);
        adminTab.setVisible(true);

        this.add(adminTab, "Center");
    }

    private void addBookManagementPanel() {
        searchBookPanel.setLayout(new BorderLayout());
        searchBookPanel.setSize(1330, 1000);
        addSearchPanel();
        searchBookPanel.add(bookInformationPanel, "Center");
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

    private void addUserManagementPanel() {
        userManagementPanel.setLayout(new GridLayout(1, 1));
        userManagementPanel.add(userInformationPanel);
        userManagementPanel.setVisible(true);
    }
}

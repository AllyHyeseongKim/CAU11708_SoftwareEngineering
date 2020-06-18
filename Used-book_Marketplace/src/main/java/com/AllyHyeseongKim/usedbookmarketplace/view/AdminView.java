package com.AllyHyeseongKim.usedbookmarketplace.view;

import javax.swing.*;
import java.awt.*;


public class AdminView extends JFrame {
    private JPanel bookManagementPanel = new JPanel();
    private JPanel userManagementPanel = new JPanel();

    public BookInformationPanel bookInformationPanel;
    public UserInformationPanel userInformationPanel;

    public JTabbedPane adminTab = new JTabbedPane();

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
        adminTab.addTab("Book Management", this.bookManagementPanel);
        adminTab.addTab("User Management", this.userManagementPanel);
        adminTab.setVisible(true);

        this.add(adminTab, "Center");
    }

    private void addBookManagementPanel() {
        bookManagementPanel.setLayout(new GridLayout(1, 1));
        bookManagementPanel.add(bookInformationPanel);
        bookManagementPanel.setVisible(true);
    }

    private void addUserManagementPanel() {
        userManagementPanel.setLayout(new GridLayout(1, 1));
        userManagementPanel.add(userInformationPanel);
        userManagementPanel.setVisible(true);
    }
}

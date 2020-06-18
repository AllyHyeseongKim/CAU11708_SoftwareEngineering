package com.AllyHyeseongKim.usedbookmarketplace.view;

import javax.swing.*;
import java.awt.*;


public class AddBookPanel extends JPanel {
    private JPanel bookInformationLabelPanel = new JPanel();
    private JPanel bookInformationTextFieldPanel = new JPanel();

    private JLabel titleLabel = new JLabel("Book Title");
    private JLabel authorLabel = new JLabel("Author");
    private JLabel publisherLabel = new JLabel("Publisher");
    private JLabel yearLabel = new JLabel("Published Year");
    private JLabel priceLabel = new JLabel("Price (Won)");
    private JLabel statusLabel = new JLabel("Status");
    private JLabel blankLabel = new JLabel("");
    public JTextField titleTextField = new JTextField();
    public JTextField authorTextField = new JTextField();
    public JTextField publisherTextField = new JTextField();
    public JTextField yearTextField = new JTextField();
    public JTextField priceTextField = new JTextField();

    private String[] status= {"Excellent", "Good", "Fair"};
    public JComboBox statusComboBox = new JComboBox(status);

    public JButton addBookButton = new JButton("Add Book");

    public AddBookPanel() {
        this.setLayout(new BorderLayout());
        addLabels();
        addTextFields();
        this.add(addBookButton, "East");

        this.setVisible(true);
    }

    private void addLabels() {
        bookInformationLabelPanel.setSize(1300, 10);
        titleLabel.setPreferredSize(new Dimension(250, 10));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        bookInformationLabelPanel.add(titleLabel);
        authorLabel.setPreferredSize(new Dimension(200, 10));
        authorLabel.setHorizontalAlignment(JLabel.CENTER);
        bookInformationLabelPanel.add(authorLabel);
        publisherLabel.setPreferredSize(new Dimension(200, 10));
        publisherLabel.setHorizontalAlignment(JLabel.CENTER);
        bookInformationLabelPanel.add(publisherLabel);
        yearLabel.setPreferredSize(new Dimension(150, 10));
        yearLabel.setHorizontalAlignment(JLabel.CENTER);
        bookInformationLabelPanel.add(yearLabel);
        priceLabel.setPreferredSize(new Dimension(200, 10));
        priceLabel.setHorizontalAlignment(JLabel.CENTER);
        bookInformationLabelPanel.add(priceLabel);
        statusLabel.setPreferredSize(new Dimension(150, 10));
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        bookInformationLabelPanel.add(statusLabel);
        blankLabel.setPreferredSize(new Dimension(110, 10));
        bookInformationLabelPanel.add(blankLabel);

        this.add(bookInformationLabelPanel, "North");
    }

    private void addTextFields() {
        bookInformationTextFieldPanel.setSize(1300, 30);
        titleTextField.setPreferredSize(new Dimension(250, 30));
        bookInformationTextFieldPanel.add(titleTextField);
        authorTextField.setPreferredSize(new Dimension(200, 30));
        bookInformationTextFieldPanel.add(authorTextField);
        publisherTextField.setPreferredSize(new Dimension(200, 30));
        bookInformationTextFieldPanel.add(publisherTextField);
        yearTextField.setPreferredSize(new Dimension(150, 30));
        bookInformationTextFieldPanel.add(yearTextField);
        priceTextField.setPreferredSize(new Dimension(200, 30));
        bookInformationTextFieldPanel.add(priceTextField);
        statusComboBox.setPreferredSize(new Dimension(160, 30));
        bookInformationTextFieldPanel.add(statusComboBox);

        this.add(bookInformationTextFieldPanel, "Center");
    }
}

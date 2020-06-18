package com.AllyHyeseongKim.usedbookmarketplace.view;

import javax.swing.*;
import java.awt.*;


public class CreateAccountView extends JFrame{
    private JPanel createAccountButtonPanel = new JPanel();
    private JPanel labelPanel = new JPanel();
    private JPanel textFieldPanel = new JPanel();

    private JLabel idLabel = new JLabel(" ID ");
    private JLabel nameLabel = new JLabel(" NAME ");
    private JLabel passwordLabel = new JLabel(" PASSWORD ");
    private JLabel phoneNumberLabel = new JLabel(" PHONE NUMBER ");
    private JLabel emailAddressLabel = new JLabel(" EMAIL ADDRESS ");
    public JTextField idTextField = new JTextField();
    public JTextField nameTextField = new JTextField();
    public JPasswordField passwordTextField = new JPasswordField();
    public JTextField phoneNumberTextField = new JTextField();
    public JTextField emailAddressTextField = new JTextField();
    public JButton createAccountButton = new JButton("Create Account");

    public CreateAccountView() {
        this.setLayout(new BorderLayout());
        this.setSize(330, 212);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Create Account");
        this.addCreateAccountTextLabels();
        this.addCreateAccountTextFields();
        this.addCreateAccountButton();
        this.setVisible(true);
    }

    private void addCreateAccountTextLabels() {
        labelPanel.setLayout(new GridLayout(5, 1));
        labelPanel.add(idLabel);
        labelPanel.add(nameLabel);
        labelPanel.add(passwordLabel);
        labelPanel.add(phoneNumberLabel);
        labelPanel.add(emailAddressLabel);
        labelPanel.setVisible(true);

        this.add(labelPanel, "West");
    }

    private void addCreateAccountTextFields() {
        textFieldPanel.setLayout(new GridLayout(5, 1));
        textFieldPanel.add(idTextField);
        textFieldPanel.add(nameTextField);
        textFieldPanel.add(passwordTextField);
        textFieldPanel.add(phoneNumberTextField);
        textFieldPanel.add(emailAddressTextField);
        textFieldPanel.setVisible(true);

        this.add(textFieldPanel, "Center");
    }

    private void addCreateAccountButton() {
        createAccountButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        createAccountButtonPanel.add(createAccountButton);

        this.add(createAccountButtonPanel, "South");
    }
}

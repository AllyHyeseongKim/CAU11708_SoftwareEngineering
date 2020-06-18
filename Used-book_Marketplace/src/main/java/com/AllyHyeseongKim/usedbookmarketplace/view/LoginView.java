package com.AllyHyeseongKim.usedbookmarketplace.view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame{
    private JPanel loginButtonPanel = new JPanel();
    private JPanel signUpButtonPanel = new JPanel();
    private JPanel labelPanel = new JPanel();
    private JPanel textFieldPanel = new JPanel();
    private JPanel messagePanel = new JPanel();

    private JLabel idLabel = new JLabel("  ID ");
    private JLabel passwordLabel = new JLabel("  PASSWORD ");
    public JTextField idTextField = new JTextField();
    public JPasswordField passwordTextField = new JPasswordField();
    public JButton loginButton = new JButton("Login");
    public JButton createAccountButton = new JButton("Create Account");

    public LoginView() {
        this.setLayout(new BorderLayout());
        this.setSize(330, 120);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Login");
        this.addLoginTextLabels();
        this.addLoginTextFields();
        this.addLoginButton();
        this.addSignUpButton();
        this.setVisible(true);
    }

    private void addLoginTextLabels() {
        labelPanel.setLayout(new GridLayout(2, 1));
        labelPanel.add(idLabel);
        labelPanel.add(passwordLabel);
        labelPanel.setVisible(true);

        this.add(labelPanel, "West");
    }

    private void addLoginTextFields() {
        textFieldPanel.setLayout(new GridLayout(2, 1));
        textFieldPanel.add(idTextField);
        textFieldPanel.add(passwordTextField);
        textFieldPanel.setVisible(true);

        this.add(textFieldPanel, "Center");
    }

    private void addLoginButton() {
        loginButtonPanel.setLayout(new GridLayout(1, 1));
        loginButtonPanel.add(loginButton);
        loginButtonPanel.setVisible(true);

        this.add(loginButtonPanel, "East");
    }

    private void addSignUpButton() {
        signUpButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        signUpButtonPanel.add(createAccountButton);
        signUpButtonPanel.setVisible(true);

        this.add(signUpButtonPanel, "South");
    }
}

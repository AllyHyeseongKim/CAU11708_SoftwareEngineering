package com.AllyHyeseongKim.usedbookmarketplace.controller;

import com.AllyHyeseongKim.usedbookmarketplace.model.User;
import com.AllyHyeseongKim.usedbookmarketplace.view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class LoginController implements ActionListener {
    LoginView loginView;

    ArrayList<User> userList;

    public LoginController() {
        loginView = new LoginView();

        loginView.loginButton.addActionListener(this);
        loginView.createAccountButton.addActionListener(this);
    }

    public static void main(String[] args) {
        new LoginController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(loginView.loginButton)) {
            UserListFile userListFile = new UserListFile("data/user.json");
            this.userList = userListFile.readJSON();
            loginCheck();
        } else if (e.getSource() == loginView.createAccountButton) {
            UserListFile userListFile = new UserListFile("data/user.json");
            this.userList = userListFile.readJSON();
            new CreateAccountController(this.userList);
        }
    }

    private void loginCheck() {
        String id = loginView.idTextField.getText();
        char[] secretPassword = loginView.passwordTextField.getPassword();
        String password = "";
        for (int i = 0; i < secretPassword.length; i++) {
            password += Character.toString(secretPassword[i]);
        }

        if (isAdmin(id, password)) {
            int okButton = JOptionPane.DEFAULT_OPTION;
            int result = JOptionPane.showConfirmDialog(null, "SUCCESS: Login to Admin.", "Success Message", okButton, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {
                loginView.dispose();
                new AdminController(this.userList);
            }
        } else if (isRegisteredUser(id, password) && isActivated(id)) {
            int okButton = JOptionPane.DEFAULT_OPTION;
            int result = JOptionPane.showConfirmDialog(null, "SUCCESS: Login to User.", "Success Message", okButton, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {
                loginView.dispose();
                new UserController(id, this.userList);
            }
        } else {
            JOptionPane.showMessageDialog(null, "FAILED: User not found.", "Error Message", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private boolean isAdmin(String id, String password) {
        if (id.equals("admin") && password.equals("nayana")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isRegisteredUser(String id, String password) {
        for (User user : this.userList) {
            if (user.getId().equals(id) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private boolean isActivated(String id) {
        for (User user : this.userList) {
            if (user.getId().equals(id)) {
                if (user.getStatus().equals("activated")) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}

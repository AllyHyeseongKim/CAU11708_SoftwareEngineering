package com.AllyHyeseongKim.usedbookmarketplace.controller;

import com.AllyHyeseongKim.usedbookmarketplace.model.User;
import com.AllyHyeseongKim.usedbookmarketplace.view.CreateAccountView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class CreateAccountController implements ActionListener {
    CreateAccountView createAccountView;

    ArrayList<User> userList;

    public CreateAccountController(ArrayList<User> userList) {
        this.userList = userList;

        createAccountView = new CreateAccountView();

        createAccountView.createAccountButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(createAccountView.createAccountButton)) {
            if (!isConflictedId()) {
                createAccount();
            }
        }
    }

    private boolean isConflictedId() {
        boolean isConflicted = false;
        String id = createAccountView.idTextField.getText();

        for (User user: this.userList) {
            if (user.getId().equals(id)) {
                isConflicted = true;
            }
        }

        if (isConflicted) {
            JOptionPane.showMessageDialog(null, "FAILED: Id conflicted.", "Error Message", JOptionPane.PLAIN_MESSAGE);

            return true;
        } else {
            return false;
        }
    }

    private void createAccount() {
        UserListFile userListFile = new UserListFile("data/user.json");

        User user = new User();

        String id = createAccountView.idTextField.getText();
        String name = createAccountView.nameTextField.getText();
        char[] secretPassword = createAccountView.passwordTextField.getPassword();
        String password = "";
        for (int i = 0; i < secretPassword.length; i++) {
            password += Character.toString(secretPassword[i]);
        }
        String phoneNumber = createAccountView.phoneNumberTextField.getText().replace("-", "");
        String emailAddress = createAccountView.emailAddressTextField.getText();

        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setEmailAddress(emailAddress);
        user.setStatus("activated");

        if (id.equals("") || name.equals("") || password.equals("") || phoneNumber.equals("") || emailAddress.equals("")) {
            JOptionPane.showMessageDialog(null, "FAILED: Please enter all information.", "Error Message", JOptionPane.PLAIN_MESSAGE);
        } else if (user.isRightPhoneNumber() && user.isRightEmailAddress()) {
            this.userList.add(user);
            userListFile.writeJSON(this.userList);

            int okButton = JOptionPane.DEFAULT_OPTION;
            int result = JOptionPane.showConfirmDialog(null, "SUCCESS: Account Created.", "Success Message", okButton, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {
                createAccountView.dispose();
            }
        } else if (!user.isRightPhoneNumber()) {
            JOptionPane.showMessageDialog(null, "FAILED: Wrong Format - Phone Number", "Error Message", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "FAILED: Wrong Format - Email Address", "Error Message", JOptionPane.PLAIN_MESSAGE);
        }
    }
}

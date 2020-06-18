package com.AllyHyeseongKim.usedbookmarketplace.controller;

import com.AllyHyeseongKim.usedbookmarketplace.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


class UserListFileTest {
    String filePath = "data/test-user.json";

    UserListFile userListFile = new UserListFile(this.filePath);

    ArrayList<User> userList = new ArrayList<>();

    User user = new User();

    String id = "id";
    String name = "name";
    String password = "password";
    String phoneNumber = "01000000000";
    String emailAddress = "email@google.com";
    String status = "activated";

    @BeforeEach
    void setUp() {
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setEmailAddress(emailAddress);
        user.setStatus(status);

        userList.add(user);

        userListFile.writeJSON(userList);
    }

    @Test
    void readJSON() {
        ArrayList<User> userListResult = userListFile.readJSON();

        User userResult = userListResult.get(0);

        String idResult = userResult.getId();
        String nameResult = userResult.getName();
        String passwordResult = userResult.getPassword();
        String phoneNumberResult = userResult.getPhoneNumber();
        String emailAddressResult = userResult.getEmailAddress();
        String statusResult = userResult.getStatus();

        assertThat(idResult, is(id));
        assertThat(nameResult, is(name));
        assertThat(passwordResult, is(password));
        assertThat(phoneNumberResult, is(phoneNumber));
        assertThat(emailAddressResult, is(emailAddress));
        assertThat(statusResult, is(status));
    }

    @Test
    void writeJSON() {
        String modifiedId = "modified id";
        String modifiedName = "modified name";
        String modifiedPassword = "modified password";
        String modifiedPhoneNumber = "01011111111";
        String modifiedEemailAddress = "email@naver.com";
        String modifiedStatus = "deactivated";

        user.setId(modifiedId);
        user.setName(modifiedName);
        user.setPassword(modifiedPassword);
        user.setPhoneNumber(modifiedPhoneNumber);
        user.setEmailAddress(modifiedEemailAddress);
        user.setStatus(modifiedStatus);

        userList.add(user);

        userListFile.writeJSON(userList);

        ArrayList<User> userListResult = userListFile.readJSON();

        User userResult = userListResult.get(0);

        String idResult = userResult.getId();
        String nameResult = userResult.getName();
        String passwordResult = userResult.getPassword();
        String phoneNumberResult = userResult.getPhoneNumber();
        String emailAddressResult = userResult.getEmailAddress();
        String statusResult = userResult.getStatus();

        assertThat(idResult, is(modifiedId));
        assertThat(nameResult, is(modifiedName));
        assertThat(passwordResult, is(modifiedPassword));
        assertThat(phoneNumberResult, is(modifiedPhoneNumber));
        assertThat(emailAddressResult, is(modifiedEemailAddress));
        assertThat(statusResult, is(modifiedStatus));
    }
}
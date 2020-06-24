package com.AllyHyeseongKim.usedbookmarketplace.view;

import com.AllyHyeseongKim.usedbookmarketplace.model.Book;
import com.AllyHyeseongKim.usedbookmarketplace.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;


public class UserInformationPanel extends JPanel {
    private ArrayList<User> userList;
    private ArrayList<Book> bookList;

    private String headers[] = {"Id", "Name", "Phone Number", "Email Address", "Status", "", ""};
    private Object data[][];
    private DefaultTableModel defaultTableModel = new DefaultTableModel(data, headers);
    private JTable jTable = new JTable(defaultTableModel);

    Action deleteUserAction;
    Action changeStatusAction;

    private ButtonColumn deleteButtonColumn;
    private ButtonColumn changeStatusButtonColumn;

    private JScrollPane jScrollPane = new JScrollPane(jTable);

    public UserInformationPanel(ArrayList<User> userList, ArrayList<Book> bookList, Action deleteUserAction, Action changeStatusAction) {
        this.userList = userList;
        this.bookList = bookList;

        this.deleteUserAction = deleteUserAction;
        this.changeStatusAction = changeStatusAction;

        this.deleteButtonColumn = new ButtonColumn(jTable, deleteUserAction, 6);
        this.changeStatusButtonColumn = new ButtonColumn(jTable, changeStatusAction, 5);

        this.setLayout(new GridLayout(1, 1));

        for (int i = 0; i < this.userList.size(); i++) {
            Object[] object = new Object[7];
            User user = this.userList.get(i);

            object[0] = user.getId();
            object[1] = user.getName();
            object[2] = user.getPhoneNumber();
            object[3] = user.getEmailAddress();
            object[4] = user.getStatus();
            object[5] = "Change Status";
            object[6] = "Delete";

            defaultTableModel.addRow(object);
        }
        this.add(jScrollPane);

        this.setVisible(true);
    }
}

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;


public class UserInformationPanel extends JPanel {
    private UserList userList;
    private BookList bookList;

    private String headers[] = {"Id", "Name", "Phone Number", "Email Address", "Status", "", ""};
    private Object data[][];
    private DefaultTableModel defaultTableModel = new DefaultTableModel(data, headers);
    private JTable jTable = new JTable(defaultTableModel);

    Action deleteUser = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTable jTable = (JTable)e.getSource();
            int row = Integer.valueOf(e.getActionCommand());
            if (jTable.getModel().getValueAt(row, 4).equals("deactivated")) {
                deleteUser(row);
                int okButton = JOptionPane.DEFAULT_OPTION;
                int result = JOptionPane.showConfirmDialog(null, "SUCCESS: Deactivated user deleted.", "Success Message", okButton, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {
                    ((DefaultTableModel) jTable.getModel()).removeRow(row);
                }
            } else {
                JOptionPane.showMessageDialog(null, "FAILED: Deactivate the activated user first.", "Error Message", JOptionPane.PLAIN_MESSAGE);
            }
        }
    };

    Action changeStatus = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTable jTable = (JTable)e.getSource();
            int row = Integer.valueOf(e.getActionCommand());
            if (jTable.getModel().getValueAt(row, 4).equals("activated")) {
                jTable.getModel().setValueAt("deactivated", row, 4);
            } else {
                jTable.getModel().setValueAt("activated", row, 4);
            }
            changeStatus(row);
        }
    };

    private ButtonColumn changeStatusButtonColumn = new ButtonColumn(jTable, changeStatus, 5);
    private ButtonColumn deleteButtonColumn = new ButtonColumn(jTable, deleteUser, 6);
    private JScrollPane jScrollPane = new JScrollPane(jTable);

    public UserInformationPanel(UserList userList, BookList bookList) {
        this.userList = userList;
        this.bookList = bookList;

        this.setLayout(new GridLayout(1, 1));

        for (int i = 0; i < userList.getNumUsers(); i++) {
            Object[] object = new Object[7];
            User user = userList.getUser(i);

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

    private void changeStatus(int index) {
        UserListFile userListFile = new UserListFile("data/user.json");

        User user = this.userList.getUser(index);
        if (user.getStatus().equals("activated")) {
            user.setStatus("deactivated");
        } else {
            user.setStatus("activated");
        }
        this.userList.modifyUser(index, user);
        userListFile.writeJSON(this.userList);
    }

    private void deleteUser(int index) {
        BookListFile bookListFile = new BookListFile("data/book.json");
        UserListFile userListFile = new UserListFile("data/user.json");

        this.bookList = this.userList.deleteUser(index, this.bookList);

        bookListFile.writeJSON(this.bookList);
        userListFile.writeJSON(this.userList);
    }
}

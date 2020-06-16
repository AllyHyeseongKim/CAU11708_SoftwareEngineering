import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CreateAccountFrame extends JFrame{
    UserList userList;

    JPanel createAccountButtonPanel = new JPanel();
    JPanel labelPanel = new JPanel();
    JPanel textFieldPanel = new JPanel();

    JLabel idLabel = new JLabel(" ID ");
    JLabel nameLabel = new JLabel(" NAME ");
    JLabel passwordLabel = new JLabel(" PASSWORD ");
    JLabel phoneNumberLabel = new JLabel(" PHONE NUMBER ");
    JLabel emailAddressLabel = new JLabel(" EMAIL ADDRESS ");
    JTextField idTextField = new JTextField();
    JTextField nameTextField = new JTextField();
    JPasswordField passwordTextField = new JPasswordField();
    JTextField phoneNumberTextField = new JTextField();
    JTextField emailAddressTextField = new JTextField();
    JButton createAccountButton = new JButton("Create Account");

    public CreateAccountFrame(UserList userList) {
        this.userList = userList;

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

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!idConflicted()) {
                    createAccount();
                }
            }
        });
    }

    private void createAccount() {
        UserListFile userListFile = new UserListFile("data/user.json");

        User user = new User();

        String id = idTextField.getText();
        String name = nameTextField.getText();
        char[] secretPassword = passwordTextField.getPassword();
        String password = "";
        for (int i = 0; i < secretPassword.length; i++) {
            password += Character.toString(secretPassword[i]);
        }
        String phoneNumber = phoneNumberTextField.getText().replace("-", "");
        String emailAddress = emailAddressTextField.getText();

        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setEmailAddress(emailAddress);
        user.setStatus("activated");
        System.out.println(id);
        if (id.equals("") || name.equals("") || password.equals("") || phoneNumber.equals("") || emailAddress.equals("")) {
            JOptionPane.showMessageDialog(null, "FAILED: Please enter all information.", "Error Message", JOptionPane.PLAIN_MESSAGE);
        } else if (user.isRightPhoneNumber() && user.isRightEmailAddress()) {
            this.userList.addUser(user);
            userListFile.writeJSON(this.userList);

            int okButton = JOptionPane.DEFAULT_OPTION;
            int result = JOptionPane.showConfirmDialog(null, "SUCCESS: Account Created.", "Success Message", okButton, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {
                this.dispose();
            }
        } else if (!user.isRightPhoneNumber()) {
            JOptionPane.showMessageDialog(null, "FAILED: Wrong Format - Phone Number", "Error Message", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "FAILED: Wrong Format - Email Address", "Error Message", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private boolean idConflicted() {
        String id = idTextField.getText();
        if (this.userList.isConflictedId(id)) {
            JOptionPane.showMessageDialog(null, "FAILED: Id conflicted.", "Error Message", JOptionPane.PLAIN_MESSAGE);

            return true;
        } else {
            return false;
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginFrame extends JFrame {

    private UserList userList;
    private BookList bookList;

    private JPanel loginButtonPanel = new JPanel();
    private JPanel signUpButtonPanel = new JPanel();
    private JPanel labelPanel = new JPanel();
    private JPanel textFieldPanel = new JPanel();
    private JPanel messagePanel = new JPanel();

    private JLabel idLabel = new JLabel("  ID ");
    private JLabel passwordLabel = new JLabel("  PASSWORD ");
    private JTextField idTextField = new JTextField();
    private JPasswordField passwordTextField = new JPasswordField();
    private JButton loginButton = new JButton("Login");
    private JButton createAccountButton = new JButton("Create Account");

    public LoginFrame(UserList userList, BookList bookList) {
        this.userList = userList;
        this.bookList = bookList;

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

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginCheck();
            }
        });
    }

    private void addSignUpButton() {
        signUpButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        signUpButtonPanel.add(createAccountButton);
        signUpButtonPanel.setVisible(true);

        this.add(signUpButtonPanel, "South");

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateAccountFrame(userList);
            }
        });
    }

    private void loginCheck() {
        String id = idTextField.getText();
        char[] secretPassword = passwordTextField.getPassword();

        if (this.userList.isAdmin(id, secretPassword)) {
            System.out.println("SUCCESS: Login to Admin.");
            int okButton = JOptionPane.DEFAULT_OPTION;
            int result = JOptionPane.showConfirmDialog(messagePanel, "SUCCESS: Login to Admin.", "Success Message", okButton, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {
                this.dispose();
                new AdminFrame(this.userList, this.bookList);
            }
        } else if (this.userList.login(id, secretPassword) && this.userList.isActivated(id)) {
            System.out.println("SUCCESS: Login to User.");
            int okButton = JOptionPane.DEFAULT_OPTION;
            int result = JOptionPane.showConfirmDialog(messagePanel, "SUCCESS: Login to User.", "Success Message", okButton, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {
                this.dispose();
                new UserFrame(id, this.bookList, this.userList);
            }
        } else {
            System.out.println("FAILED: User not found.");
            JOptionPane.showMessageDialog(messagePanel, "FAILED: User not found.", "Error Message", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private UserList userList;

    private JPanel buttonPanel = new JPanel();
    private JPanel labelPanel = new JPanel();
    private JPanel textFieldPanel = new JPanel();

    private JLabel idLabel = new JLabel("  ID ");
    private JLabel passwordLabel = new JLabel("  PASSWORD ");
    private JTextField idTextField = new JTextField();
    private JPasswordField passwordTextField = new JPasswordField();
    private JButton loginButton = new JButton("Login");

    public JButton getLoginButton() {
        return this.loginButton;
    }

    public LoginFrame(UserList userList) {
        this.userList = userList;

        this.setLayout(new BorderLayout());
        this.setSize(330, 100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Login");
        this.addLoginTextLabels();
        this.addLoginTextFields();
        this.addLoginButton();
        this.setVisible(true);
    }

    public void addLoginTextLabels() {
        labelPanel.setLayout(new GridLayout(2, 0));
        labelPanel.add(idLabel);
        labelPanel.add(passwordLabel);
        setVisible(true);

        this.add(labelPanel, "West");
    }

    public void addLoginTextFields() {
        textFieldPanel.setLayout(new GridLayout(2, 0));
        textFieldPanel.add(idTextField);
        textFieldPanel.add(passwordTextField);
        setVisible(true);

        this.add(textFieldPanel, "Center");
    }

    public void addLoginButton() {
        buttonPanel.setLayout(new GridLayout(1, 1));
        buttonPanel.add(loginButton);
        setVisible(true);

        this.add(buttonPanel, "East");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginCheck();
            }
        });
    }

    private void loginCheck() {
        String id = idTextField.getText();
        char[] secretPassword = passwordTextField.getPassword();

        if (this.userList.isAdmin(id, secretPassword)) {
            System.out.println("SUCCESS: Login to Admin.");
            JOptionPane.showMessageDialog(null, "SUCCESS: Login to Admin.", "Success Message", JOptionPane.PLAIN_MESSAGE);
        } else if (this.userList.login(id, secretPassword)) {
            System.out.println("SUCCESS: Login to User.");
            JOptionPane.showMessageDialog(null, "SUCCESS: Login to User.", "Success Message", JOptionPane.PLAIN_MESSAGE);
        } else {
            System.out.println("FAILED: User not found.");
            JOptionPane.showMessageDialog(null, "FAILED: User not found.", "Error Message", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
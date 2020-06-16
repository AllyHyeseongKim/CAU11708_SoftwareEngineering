import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddBookPanel extends JPanel {
    private JPanel bookInformationPanel;

    private JPanel bookInformationLabelPanel = new JPanel();
    private JPanel bookInformationTextFieldPanel = new JPanel();

    private JLabel titleLabel = new JLabel("Book Title");
    private JLabel authorLabel = new JLabel("Author");
    private JLabel publisherLabel = new JLabel("Publisher");
    private JLabel yearLabel = new JLabel("Published Year");
    private JLabel priceLabel = new JLabel("Price (Won)");
    private JLabel statusLabel = new JLabel("Status");
    private JLabel blankLabel = new JLabel("");
    private JTextField titleTextField = new JTextField();
    private JTextField authorTextField = new JTextField();
    private JTextField publisherTextField = new JTextField();
    private JTextField yearTextField = new JTextField();
    private JTextField priceTextField = new JTextField();

    private String[] status= {"Excellent", "Good", "Fair"};
    private JComboBox statusComboBox = new JComboBox(status);

    private JButton addBookButton = new JButton("Add Book");

    public AddBookPanel(BookInformationPanel bookInformationPanel) {
        this.bookInformationPanel = bookInformationPanel;

        this.setLayout(new BorderLayout());
        addLabels();
        addTextFields();
        this.add(addBookButton, "East");

        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (titleTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "FAILED: Please enter the title of the book.", "Error Message", JOptionPane.PLAIN_MESSAGE);
                } else {
                    Book book = new Book();
                    book.setSellerId("");
                    book.setISBN("");
                    book.setTitle(titleTextField.getText());
                    book.setAuthor(authorTextField.getText());
                    book.setPublisher(publisherTextField.getText());
                    book.setYear(yearTextField.getText());
                    book.setPrice(priceTextField.getText());
                    book.setStatus((String) statusComboBox.getSelectedItem());

                    bookInformationPanel.addBook(book);

                    titleTextField.setText("");
                    authorTextField.setText("");
                    publisherTextField.setText("");
                    yearTextField.setText("");
                    priceTextField.setText("");
                    statusComboBox.setSelectedItem("Excellent");
                }
            }
        });

        this.setVisible(true);
    }

    private void addLabels() {
        bookInformationLabelPanel.setSize(1300, 10);
        titleLabel.setPreferredSize(new Dimension(250, 10));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        bookInformationLabelPanel.add(titleLabel);
        authorLabel.setPreferredSize(new Dimension(200, 10));
        authorLabel.setHorizontalAlignment(JLabel.CENTER);
        bookInformationLabelPanel.add(authorLabel);
        publisherLabel.setPreferredSize(new Dimension(200, 10));
        publisherLabel.setHorizontalAlignment(JLabel.CENTER);
        bookInformationLabelPanel.add(publisherLabel);
        yearLabel.setPreferredSize(new Dimension(150, 10));
        yearLabel.setHorizontalAlignment(JLabel.CENTER);
        bookInformationLabelPanel.add(yearLabel);
        priceLabel.setPreferredSize(new Dimension(200, 10));
        priceLabel.setHorizontalAlignment(JLabel.CENTER);
        bookInformationLabelPanel.add(priceLabel);
        statusLabel.setPreferredSize(new Dimension(150, 10));
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        bookInformationLabelPanel.add(statusLabel);
        blankLabel.setPreferredSize(new Dimension(110, 10));
        bookInformationLabelPanel.add(blankLabel);

        this.add(bookInformationLabelPanel, "North");
    }

    private void addTextFields() {
        bookInformationTextFieldPanel.setSize(1300, 30);
        titleTextField.setPreferredSize(new Dimension(250, 30));
        bookInformationTextFieldPanel.add(titleTextField);
        authorTextField.setPreferredSize(new Dimension(200, 30));
        bookInformationTextFieldPanel.add(authorTextField);
        publisherTextField.setPreferredSize(new Dimension(200, 30));
        bookInformationTextFieldPanel.add(publisherTextField);
        yearTextField.setPreferredSize(new Dimension(150, 30));
        bookInformationTextFieldPanel.add(yearTextField);
        priceTextField.setPreferredSize(new Dimension(200, 30));
        bookInformationTextFieldPanel.add(priceTextField);
        statusComboBox.setPreferredSize(new Dimension(160, 30));
        bookInformationTextFieldPanel.add(statusComboBox);

        this.add(bookInformationTextFieldPanel, "Center");
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRegistrationForm extends JFrame {
    private JTextField nameField, mobileField, dobField, addressField;
    private JComboBox<String> genderComboBox;
    private JCheckBox termsCheckBox;

    public UserRegistrationForm() {
        // Set up the frame
        setTitle("User Registration Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(9, 2));

        // Create form components
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel mobileLabel = new JLabel("Mobile:");
        mobileField = new JTextField();

        JLabel genderLabel = new JLabel("Gender:");
        String[] genders = {"Male", "Female", "Other"};
        genderComboBox = new JComboBox<>(genders);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobField = new JTextField();

        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField();

        termsCheckBox = new JCheckBox("Accept Terms and Conditions");

        JButton submitButton = new JButton("Submit");
        JButton resetButton = new JButton("Reset");

        // Add components to the frame
        add(nameLabel);
        add(nameField);
        add(mobileLabel);
        add(mobileField);
        add(genderLabel);
        add(genderComboBox);
        add(dobLabel);
        add(dobField);
        add(addressLabel);
        add(addressField);
        add(new JLabel()); // Placeholder for an empty cell
        add(termsCheckBox);
        add(new JLabel()); // Placeholder for an empty cell
        add(submitButton);
        add(resetButton);

        // Add action listeners
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (termsCheckBox.isSelected()) {
                    saveToDatabase();
                    JOptionPane.showMessageDialog(UserRegistrationForm.this, "Registration successful!");
                } else {
                    JOptionPane.showMessageDialog(UserRegistrationForm.this, "Please accept Terms and Conditions");
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });
    }

    private void saveToDatabase() {
        // TODO: Implement database connection and insertion logic here
        // Example: JDBC code to insert data into a database
        try {
            Connection connection = DriverManager.getConnection("jdbc:your_database_connection_string");
            String query = "INSERT INTO users (name, mobile, gender, dob, address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nameField.getText());
            preparedStatement.setString(2, mobileField.getText());
            preparedStatement.setString(3, (String) genderComboBox.getSelectedItem());
            preparedStatement.setString(4, dobField.getText());
            preparedStatement.setString(5, addressField.getText());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void resetForm() {
        nameField.setText("");
        mobileField.setText("");
        genderComboBox.setSelectedIndex(0);
        dobField.setText("");
        addressField.setText("");
        termsCheckBox.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserRegistrationForm().setVisible(true);
            }
        });
    }
}

package swingApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Registration3 extends JFrame {
    private JTextField textField, textField_2, textField_3, textField_4, textField_5, textField_6, textField_7;
    private JCheckBox chckbxNewCheckBox, chckbxNewCheckBox_1;

    public Registration3() {
        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 500);
        getContentPane().setLayout(null);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(50, 30, 100, 20);
        getContentPane().add(lblName);

        textField = new JTextField();
        textField.setBounds(150, 30, 200, 20);
        getContentPane().add(textField);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setBounds(50, 70, 100, 20);
        getContentPane().add(lblAge);

        textField_2 = new JTextField();
        textField_2.setBounds(150, 70, 200, 20);
        getContentPane().add(textField_2);

        JLabel lblMobile = new JLabel("Mobile:");
        lblMobile.setBounds(50, 110, 100, 20);
        getContentPane().add(lblMobile);

        textField_3 = new JTextField();
        textField_3.setBounds(150, 110, 200, 20);
        getContentPane().add(textField_3);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 150, 100, 20);
        getContentPane().add(lblEmail);

        textField_4 = new JTextField();
        textField_4.setBounds(150, 150, 200, 20);
        getContentPane().add(textField_4);

        JLabel lblGender = new JLabel("Gender:");
        lblGender.setBounds(50, 190, 100, 20);
        getContentPane().add(lblGender);

        chckbxNewCheckBox = new JCheckBox("Male");
        chckbxNewCheckBox.setBounds(150, 190, 80, 20);
        getContentPane().add(chckbxNewCheckBox);

        chckbxNewCheckBox_1 = new JCheckBox("Female");
        chckbxNewCheckBox_1.setBounds(240, 190, 80, 20);
        getContentPane().add(chckbxNewCheckBox_1);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(chckbxNewCheckBox);
        genderGroup.add(chckbxNewCheckBox_1);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 230, 100, 20);
        getContentPane().add(lblUsername);

        textField_5 = new JTextField();
        textField_5.setBounds(150, 230, 200, 20);
        getContentPane().add(textField_5);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 270, 100, 20);
        getContentPane().add(lblPassword);

        textField_6 = new JPasswordField();
        textField_6.setBounds(150, 270, 200, 20);
        getContentPane().add(textField_6);

        JLabel lblConfirmPassword = new JLabel("Confirm Password:");
        lblConfirmPassword.setBounds(50, 310, 150, 20);
        getContentPane().add(lblConfirmPassword);

        textField_7 = new JPasswordField();
        textField_7.setBounds(150, 310, 200, 20);
        getContentPane().add(textField_7);

        JButton btnRegister = new JButton("Register");
        btnRegister.setBounds(150, 360, 100, 30);
        getContentPane().add(btnRegister);

        JButton btnReset = new JButton("Reset");
        btnReset.setBounds(260, 360, 100, 30);
        getContentPane().add(btnReset);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });
    }

    private void registerUser() {
        String name = textField.getText().trim();
        String age = textField_2.getText().trim();
        String mobile = textField_3.getText().trim();
        String email = textField_4.getText().trim();
        String username = textField_5.getText().trim();
        String password = textField_6.getText().trim();
        String confirmPassword = textField_7.getText().trim();

        String gender = null;
        if (chckbxNewCheckBox.isSelected()) {
            gender = "Male";
        } else if (chckbxNewCheckBox_1.isSelected()) {
            gender = "Female";
        }

        if (gender == null) {
            JOptionPane.showMessageDialog(this, "Please select a gender.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (name.isEmpty() || age.isEmpty() || mobile.isEmpty() || email.isEmpty() ||
                username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Connection conn = DatabaseConnection.getConnection("user1"); // Pass the database name
            String query = "INSERT INTO Registration1 (name, age, mobile, email, gender, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, age);
            pstmt.setString(3, mobile);
            pstmt.setString(4, email);
            pstmt.setString(5, gender);
            pstmt.setString(6, username);
            pstmt.setString(7, password);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                resetForm();
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetForm() {
        textField.setText("");
        textField_2.setText("");
        textField_3.setText("");
        textField_4.setText("");
        textField_5.setText("");
        textField_6.setText("");
        textField_7.setText("");
        chckbxNewCheckBox.setSelected(false);
        chckbxNewCheckBox_1.setSelected(false);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Registration3 frame = new Registration3();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

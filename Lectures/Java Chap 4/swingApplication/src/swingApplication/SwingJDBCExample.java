package swingApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SwingJDBCExample extends JFrame {
    private JTextArea textArea;
    private JButton fetchButton;

    // Database details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    public SwingJDBCExample() {
        // Set up the JFrame
        setTitle("JDBC and Swing Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create components
        textArea = new JTextArea();
        textArea.setEditable(false);
        fetchButton = new JButton("Fetch Data");

        // Add components to the frame
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(fetchButton, BorderLayout.SOUTH);

        // Fetch button action listener
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchData();
            }
        });
    }

    private void fetchData() {
        String query = "SELECT * FROM users";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            StringBuilder sb = new StringBuilder();
            sb.append("ID\tName\tEmail\n");
            sb.append("-----------------------------\n");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                sb.append(id).append("\t").append(name).append("\t").append(email).append("\n");
            }

            textArea.setText(sb.toString());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching data: " + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Load the MySQL driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "MySQL Driver not found: " + e.getMessage(),
                    "Driver Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create and display the Swing application
        SwingUtilities.invokeLater(() -> {
            SwingJDBCExample app = new SwingJDBCExample();
            app.setVisible(true);
        });
    }
}

package handson2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StudentManagementSystem extends JFrame {
    private JTextField nameField, rollField, courseField;
    private JTable studentTable;
    private DefaultTableModel tableModel;

    public StudentManagementSystem() {
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(null);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(20, 20, 100, 25);
        getContentPane().add(lblName);

        nameField = new JTextField();
        nameField.setBounds(100, 20, 150, 25);
        getContentPane().add(nameField);

        JLabel lblRoll = new JLabel("Roll No:");
        lblRoll.setBounds(20, 60, 100, 25);
        getContentPane().add(lblRoll);

        rollField = new JTextField();
        rollField.setBounds(100, 60, 150, 25);
        getContentPane().add(rollField);

        JLabel lblCourse = new JLabel("Course:");
        lblCourse.setBounds(20, 100, 100, 25);
        getContentPane().add(lblCourse);

        courseField = new JTextField();
        courseField.setBounds(100, 100, 150, 25);
        getContentPane().add(courseField);

        JButton addButton = new JButton("Add");
        addButton.setBounds(20, 140, 100, 30);
        getContentPane().add(addButton);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(130, 140, 100, 30);
        getContentPane().add(updateButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(240, 140, 100, 30);
        getContentPane().add(deleteButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBounds(350, 140, 100, 30);
        getContentPane().add(refreshButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 180, 540, 150);
        getContentPane().add(scrollPane);

        studentTable = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Roll No", "Course"}, 0);
        studentTable.setModel(tableModel);
        scrollPane.setViewportView(studentTable);

        // Button actions
        addButton.addActionListener(e -> addStudent());
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        refreshButton.addActionListener(e -> loadStudentData());

        // Load student data on startup
        loadStudentData();
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        String rollNo = rollField.getText().trim();
        String course = courseField.getText().trim();

        if (name.isEmpty() || rollNo.isEmpty() || course.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = getConnection()) {
            String query = "INSERT INTO students (name, roll_no, course) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, rollNo);
            pstmt.setString(3, course);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadStudentData();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding student: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = tableModel.getValueAt(selectedRow, 0).toString();
        String name = nameField.getText().trim();
        String rollNo = rollField.getText().trim();
        String course = courseField.getText().trim();

        if (name.isEmpty() || rollNo.isEmpty() || course.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = getConnection()) {
            String query = "UPDATE students SET name = ?, roll_no = ?, course = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, rollNo);
            pstmt.setString(3, course);
            pstmt.setString(4, id);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadStudentData();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating student: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = tableModel.getValueAt(selectedRow, 0).toString();

        try (Connection conn = getConnection()) {
            String query = "DELETE FROM students WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadStudentData();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting student: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadStudentData() {
        tableModel.setRowCount(0);

        try (Connection conn = getConnection()) {
            String query = "SELECT * FROM students";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("roll_no"),
                        rs.getString("course")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading student data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/StudentManagement", "root", "0000");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StudentManagementSystem frame = new StudentManagementSystem();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

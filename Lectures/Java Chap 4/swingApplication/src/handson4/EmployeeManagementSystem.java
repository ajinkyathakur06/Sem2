package handson4;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EmployeeManagementSystem extends JFrame {
    private JTextField idField, nameField, departmentField, salaryField;
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private JLabel totalSalaryLabel;

    public EmployeeManagementSystem() {
        setTitle("Employee Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        getContentPane().setLayout(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 20, 100, 25);
        getContentPane().add(lblId);

        idField = new JTextField();
        idField.setBounds(80, 20, 150, 25);
        getContentPane().add(idField);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(250, 20, 100, 25);
        getContentPane().add(lblName);

        nameField = new JTextField();
        nameField.setBounds(310, 20, 150, 25);
        getContentPane().add(nameField);

        JLabel lblDepartment = new JLabel("Department:");
        lblDepartment.setBounds(20, 60, 100, 25);
        getContentPane().add(lblDepartment);

        departmentField = new JTextField();
        departmentField.setBounds(110, 60, 150, 25);
        getContentPane().add(departmentField);

        JLabel lblSalary = new JLabel("Salary:");
        lblSalary.setBounds(280, 60, 100, 25);
        getContentPane().add(lblSalary);

        salaryField = new JTextField();
        salaryField.setBounds(340, 60, 150, 25);
        getContentPane().add(salaryField);

        JButton addButton = new JButton("Add");
        addButton.setBounds(510, 20, 100, 30);
        getContentPane().add(addButton);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(510, 60, 100, 30);
        getContentPane().add(updateButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(620, 20, 100, 30);
        getContentPane().add(deleteButton);

        JButton calculateButton = new JButton("Calculate Total Salary");
        calculateButton.setBounds(620, 60, 180, 30);
        getContentPane().add(calculateButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 120, 850, 350);
        getContentPane().add(scrollPane);

        employeeTable = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Department", "Salary"}, 0);
        employeeTable.setModel(tableModel);
        scrollPane.setViewportView(employeeTable);

        totalSalaryLabel = new JLabel("Total Salary: 0");
        totalSalaryLabel.setBounds(20, 490, 200, 30);
        getContentPane().add(totalSalaryLabel);

        // Button Actions
        addButton.addActionListener(e -> addEmployee());
        updateButton.addActionListener(e -> updateEmployee());
        deleteButton.addActionListener(e -> deleteEmployee());
        calculateButton.addActionListener(e -> calculateTotalSalary());

        // Load employee data on startup
        loadEmployeeData();
    }

    private void addEmployee() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String department = departmentField.getText().trim();
        String salary = salaryField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || department.isEmpty() || salary.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = getConnection()) {
            String query = "INSERT INTO employees (id, name, department, salary) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(id));
            pstmt.setString(2, name);
            pstmt.setString(3, department);
            pstmt.setDouble(4, Double.parseDouble(salary));
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Employee added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadEmployeeData();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding employee: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = tableModel.getValueAt(selectedRow, 0).toString();
        String name = nameField.getText().trim();
        String department = departmentField.getText().trim();
        String salary = salaryField.getText().trim();

        if (name.isEmpty() || department.isEmpty() || salary.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = getConnection()) {
            String query = "UPDATE employees SET name = ?, department = ?, salary = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, department);
            pstmt.setDouble(3, Double.parseDouble(salary));
            pstmt.setInt(4, Integer.parseInt(id));
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Employee updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadEmployeeData();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating employee: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = tableModel.getValueAt(selectedRow, 0).toString();

        try (Connection conn = getConnection()) {
            String query = "DELETE FROM employees WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(id));
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Employee deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadEmployeeData();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting employee: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateTotalSalary() {
        try (Connection conn = getConnection()) {
            String query = "SELECT SUM(salary) AS total_salary FROM employees";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                double totalSalary = rs.getDouble("total_salary");
                totalSalaryLabel.setText("Total Salary: " + totalSalary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error calculating total salary: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadEmployeeData() {
        tableModel.setRowCount(0);

        try (Connection conn = getConnection()) {
            String query = "SELECT * FROM employees";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("salary")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading employee data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/EmployeeManagement", "root", "0000");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                EmployeeManagementSystem frame = new EmployeeManagementSystem();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

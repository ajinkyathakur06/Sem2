package handson3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LibraryInventorySystem extends JFrame {
    private JTextField titleField, authorField, isbnField, quantityField, searchField;
    private JTable bookTable;
    private DefaultTableModel tableModel;

    public LibraryInventorySystem() {
        setTitle("Library Book Inventory System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("Title:");
        lblTitle.setBounds(20, 20, 100, 25);
        getContentPane().add(lblTitle);

        titleField = new JTextField();
        titleField.setBounds(80, 20, 150, 25);
        getContentPane().add(titleField);

        JLabel lblAuthor = new JLabel("Author:");
        lblAuthor.setBounds(250, 20, 100, 25);
        getContentPane().add(lblAuthor);

        authorField = new JTextField();
        authorField.setBounds(310, 20, 150, 25);
        getContentPane().add(authorField);

        JLabel lblISBN = new JLabel("ISBN:");
        lblISBN.setBounds(20, 60, 100, 25);
        getContentPane().add(lblISBN);

        isbnField = new JTextField();
        isbnField.setBounds(80, 60, 150, 25);
        getContentPane().add(isbnField);

        JLabel lblQuantity = new JLabel("Quantity:");
        lblQuantity.setBounds(250, 60, 100, 25);
        getContentPane().add(lblQuantity);

        quantityField = new JTextField();
        quantityField.setBounds(310, 60, 150, 25);
        getContentPane().add(quantityField);

        JButton addButton = new JButton("Add");
        addButton.setBounds(480, 20, 100, 30);
        getContentPane().add(addButton);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(480, 60, 100, 30);
        getContentPane().add(updateButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(590, 20, 100, 30);
        getContentPane().add(deleteButton);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(590, 60, 100, 30);
        getContentPane().add(searchButton);

        JLabel lblSearch = new JLabel("Search:");
        lblSearch.setBounds(20, 100, 100, 25);
        getContentPane().add(lblSearch);

        searchField = new JTextField();
        searchField.setBounds(80, 100, 380, 25);
        getContentPane().add(searchField);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 140, 750, 300);
        getContentPane().add(scrollPane);

        bookTable = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"ID", "Title", "Author", "ISBN", "Quantity"}, 0);
        bookTable.setModel(tableModel);
        scrollPane.setViewportView(bookTable);

        // Button actions
        addButton.addActionListener(e -> addBook());
        updateButton.addActionListener(e -> updateBook());
        deleteButton.addActionListener(e -> deleteBook());
        searchButton.addActionListener(e -> searchBooks());

        // Load book data on startup
        loadBookData();
    }

    private void addBook() {
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        String isbn = isbnField.getText().trim();
        String quantity = quantityField.getText().trim();

        if (title.isEmpty() || author.isEmpty() || isbn.isEmpty() || quantity.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = getConnection()) {
            String query = "INSERT INTO books (title, author, isbn, quantity) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, isbn);
            pstmt.setInt(4, Integer.parseInt(quantity));
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadBookData();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding book: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a book to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = tableModel.getValueAt(selectedRow, 0).toString();
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        String isbn = isbnField.getText().trim();
        String quantity = quantityField.getText().trim();

        if (title.isEmpty() || author.isEmpty() || isbn.isEmpty() || quantity.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = getConnection()) {
            String query = "UPDATE books SET title = ?, author = ?, isbn = ?, quantity = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, isbn);
            pstmt.setInt(4, Integer.parseInt(quantity));
            pstmt.setInt(5, Integer.parseInt(id));
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadBookData();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating book: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a book to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = tableModel.getValueAt(selectedRow, 0).toString();

        try (Connection conn = getConnection()) {
            String query = "DELETE FROM books WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(id));
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadBookData();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting book: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchBooks() {
        String searchText = searchField.getText().trim();
        tableModel.setRowCount(0);

        try (Connection conn = getConnection()) {
            String query = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%" + searchText + "%");
            pstmt.setString(2, "%" + searchText + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getInt("quantity")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error searching books: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadBookData() {
        tableModel.setRowCount(0);

        try (Connection conn = getConnection()) {
            String query = "SELECT * FROM books";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getInt("quantity")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading book data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/LibraryManagement", "root", "0000");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LibraryInventorySystem frame = new LibraryInventorySystem();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

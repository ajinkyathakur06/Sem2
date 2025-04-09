package handson5;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InvoiceGenerationSystem extends JFrame {
    private JTextField productField, priceField, quantityField, totalField;
    private JTable itemTable, invoiceTable;
    private DefaultTableModel itemTableModel, invoiceTableModel;

    public InvoiceGenerationSystem() {
        setTitle("Invoice Generation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 700);
        getContentPane().setLayout(null);

        JLabel lblProduct = new JLabel("Product:");
        lblProduct.setBounds(20, 20, 100, 25);
        getContentPane().add(lblProduct);

        productField = new JTextField();
        productField.setBounds(80, 20, 150, 25);
        getContentPane().add(productField);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setBounds(250, 20, 100, 25);
        getContentPane().add(lblPrice);

        priceField = new JTextField();
        priceField.setBounds(310, 20, 100, 25);
        getContentPane().add(priceField);

        JLabel lblQuantity = new JLabel("Quantity:");
        lblQuantity.setBounds(430, 20, 100, 25);
        getContentPane().add(lblQuantity);

        quantityField = new JTextField();
        quantityField.setBounds(500, 20, 100, 25);
        getContentPane().add(quantityField);

        JButton addItemButton = new JButton("Add Item");
        addItemButton.setBounds(620, 20, 100, 30);
        getContentPane().add(addItemButton);

        JScrollPane itemScrollPane = new JScrollPane();
        itemScrollPane.setBounds(20, 70, 850, 200);
        getContentPane().add(itemScrollPane);

        itemTable = new JTable();
        itemTableModel = new DefaultTableModel(new Object[]{"Product", "Price", "Quantity", "Total"}, 0);
        itemTable.setModel(itemTableModel);
        itemScrollPane.setViewportView(itemTable);

        JLabel lblTotal = new JLabel("Total Cost:");
        lblTotal.setBounds(20, 290, 100, 25);
        getContentPane().add(lblTotal);

        totalField = new JTextField();
        totalField.setEditable(false);
        totalField.setBounds(100, 290, 150, 25);
        getContentPane().add(totalField);

        JButton saveInvoiceButton = new JButton("Save Invoice");
        saveInvoiceButton.setBounds(620, 290, 150, 30);
        getContentPane().add(saveInvoiceButton);

        JScrollPane invoiceScrollPane = new JScrollPane();
        invoiceScrollPane.setBounds(20, 350, 850, 250);
        getContentPane().add(invoiceScrollPane);

        invoiceTable = new JTable();
        invoiceTableModel = new DefaultTableModel(new Object[]{"Invoice ID", "Date", "Total Cost"}, 0);
        invoiceTable.setModel(invoiceTableModel);
        invoiceScrollPane.setViewportView(invoiceTable);

        JButton viewInvoiceButton = new JButton("View Invoice");
        viewInvoiceButton.setBounds(620, 620, 150, 30);
        getContentPane().add(viewInvoiceButton);

        // Button Actions
        addItemButton.addActionListener(e -> addItem());
        saveInvoiceButton.addActionListener(e -> saveInvoice());
        viewInvoiceButton.addActionListener(e -> viewSelectedInvoice());

        // Load invoices on startup
        loadInvoices();
    }

    private void addItem() {
        String product = productField.getText().trim();
        String price = priceField.getText().trim();
        String quantity = quantityField.getText().trim();

        if (product.isEmpty() || price.isEmpty() || quantity.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double priceValue = Double.parseDouble(price);
            int quantityValue = Integer.parseInt(quantity);
            double total = priceValue * quantityValue;

            itemTableModel.addRow(new Object[]{product, priceValue, quantityValue, total});
            updateTotalCost();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid price or quantity.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTotalCost() {
        double totalCost = 0;
        for (int i = 0; i < itemTableModel.getRowCount(); i++) {
            totalCost += (double) itemTableModel.getValueAt(i, 3);
        }
        totalField.setText(String.valueOf(totalCost));
    }

    private void saveInvoice() {
        if (itemTableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Add items to save the invoice.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = getConnection()) {
            String insertInvoiceQuery = "INSERT INTO invoices (date, total_cost) VALUES (?, ?)";
            PreparedStatement invoiceStmt = conn.prepareStatement(insertInvoiceQuery, Statement.RETURN_GENERATED_KEYS);

            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            double totalCost = Double.parseDouble(totalField.getText());

            invoiceStmt.setString(1, date);
            invoiceStmt.setDouble(2, totalCost);
            invoiceStmt.executeUpdate();

            ResultSet generatedKeys = invoiceStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int invoiceId = generatedKeys.getInt(1);

                String insertItemQuery = "INSERT INTO items (invoice_id, product, price, quantity, total) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement itemStmt = conn.prepareStatement(insertItemQuery);

                for (int i = 0; i < itemTableModel.getRowCount(); i++) {
                    itemStmt.setInt(1, invoiceId);
                    itemStmt.setString(2, itemTableModel.getValueAt(i, 0).toString());
                    itemStmt.setDouble(3, (double) itemTableModel.getValueAt(i, 1));
                    itemStmt.setInt(4, (int) itemTableModel.getValueAt(i, 2));
                    itemStmt.setDouble(5, (double) itemTableModel.getValueAt(i, 3));
                    itemStmt.addBatch();
                }

                itemStmt.executeBatch();
                JOptionPane.showMessageDialog(this, "Invoice saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                itemTableModel.setRowCount(0);
                totalField.setText("");
                loadInvoices();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving invoice: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewSelectedInvoice() {
        int selectedRow = invoiceTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an invoice to view.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int invoiceId = (int) invoiceTableModel.getValueAt(selectedRow, 0);

        try (Connection conn = getConnection()) {
            String query = "SELECT * FROM items WHERE invoice_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, invoiceId);
            ResultSet rs = stmt.executeQuery();

            StringBuilder invoiceDetails = new StringBuilder("Invoice Details:\n");
            while (rs.next()) {
                invoiceDetails.append("Product: ").append(rs.getString("product"))
                        .append(", Price: ").append(rs.getDouble("price"))
                        .append(", Quantity: ").append(rs.getInt("quantity"))
                        .append(", Total: ").append(rs.getDouble("total"))
                        .append("\n");
            }

            JOptionPane.showMessageDialog(this, invoiceDetails.toString(), "Invoice Details", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching invoice details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadInvoices() {
        invoiceTableModel.setRowCount(0);

        try (Connection conn = getConnection()) {
            String query = "SELECT * FROM invoices";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                invoiceTableModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("date"),
                        rs.getDouble("total_cost")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading invoices: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "your_username", "your_password");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InvoiceGenerationSystem frame = new InvoiceGenerationSystem();
            frame.setVisible(true);
        });
    }
}

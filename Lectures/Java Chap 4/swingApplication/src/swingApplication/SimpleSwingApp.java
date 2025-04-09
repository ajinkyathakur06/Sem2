package swingApplication;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleSwingApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleSwingApp().createAndShowGUI());
    }

    private void createAndShowGUI() {
        // Create frame
        JFrame frame = new JFrame("Swing Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);

        // Create components
        JLabel label = new JLabel("Enter text:");
        label.setBounds(20, 20, 100, 30);

        JTextField textField = new JTextField();
        textField.setBounds(120, 20, 200, 30);

        JButton button = new JButton("Submit");
        button.setBounds(120, 60, 100, 30);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(20, 100, 340, 150);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 100, 340, 150);

        // Add action listener to button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textField.getText();
                textArea.append("Ram Ram "+inputText + "\n");
                textField.setText("");
            }
        });

        // Add components to frame
        frame.add(label);
        frame.add(textField);
        frame.add(button);
        frame.add(scrollPane);

        // Make frame visible
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ImageOperation {

    public static void operate(int key) {
        // Create a file chooser dialog for selecting an image file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();

        // Check if a file was selected
        if (file != null) {
            // Read the selected image file
            try {
                FileInputStream fis = new FileInputStream(file);
                byte[] data = new byte[fis.available()];
                fis.read(data);

                // Perform XOR operation on each byte with the given key
                int i = 0;
                for (byte b : data) {
                    data[i] = (byte) (b ^ key);
                    i++;
                }

                // Write the modified data back to the image file
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(data);
                fos.close();
                fis.close();
                JOptionPane.showMessageDialog(null, "Operation Completed Successfully");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        // Create the main frame for the application
        JFrame frame = new JFrame();
        frame.setTitle("Image Operation");
        frame.setSize(400, 150);  // Adjusted the height for better layout
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Roboto", Font.BOLD, 16);  // Adjusted the font size

        // Creating the "Open Image" button
        JButton openImageButton = new JButton("Open Image");
        openImageButton.setFont(font);

        // Creating a text field for entering the key
        JTextField keyTextField = new JTextField(10);
        keyTextField.setFont(font);

        // Adding an action listener to the button
        openImageButton.addActionListener(e -> {
            String keyText = keyTextField.getText();
            try {
                int key = Integer.parseInt(keyText);
                operate(key);  // Call the operate method with the provided key
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid key");
            }
        });

        // Create a panel for better organization of components
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(openImageButton);
        panel.add(keyTextField);

        // Add the panel to the main frame
        frame.add(panel);

        frame.setVisible(true);
    }
}

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UI {
    public static JFrame createFrame() {
        // Create a JLabel with an image icon
        JLabel label = new JLabel();
        ImageIcon image2Icon = new ImageIcon("src/logo.png");
        label.setText("Balatradora");
        label.setIcon(image2Icon);
        label.setHorizontalTextPosition(JLabel.LEFT);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setBounds(0,0,250,250);
       

        // Create a new JFrame
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setTitle("Botón de ejemplo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 640);
       
       
        JButton button = new JButton("Haz click aquí");
        Dimension buttonSize = new Dimension(100, 30); // Set button size
        button.setPreferredSize(buttonSize);

        int frameWidth = 420;  // Frame width
        int frameHeight = 640; // Frame height
        int buttonWidth = buttonSize.width;
        int buttonHeight = buttonSize.height;
        int x = (frameWidth - buttonWidth) / 2;
        int y = (frameHeight - buttonHeight) / 2;

        button.setBounds(x, y, buttonWidth, buttonHeight);

        frame.add(button);
        frame.add(label);
        frame.setVisible(true);

        // Set the icon image
        ImageIcon image = new ImageIcon("src/logo.png");
        frame.setIconImage(image.getImage());
        
        return frame;
    }
}

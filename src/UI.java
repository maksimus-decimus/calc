import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UI {
    public static JFrame createFrame() {
        
        JLabel label = new JLabel();
        ImageIcon image2Icon = new ImageIcon("src/logo.png");
        label.setText("Balatradora");
        label.setIcon(image2Icon);
        label.setHorizontalTextPosition(JLabel.LEFT);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setBounds(0,0,250,250);
       

       
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setTitle("Botón de ejemplo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 640);
       


        ImageIcon originalIcon = new ImageIcon("src/boton1.png");
        Image originalImage = originalIcon.getImage();
        

       // boton mal escalao
        int desiredWidth = 150;  
        int desiredHeight = 50; 
        Image scaledImage = originalImage.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JButton button = new JButton(scaledIcon);
        Dimension buttonSize = new Dimension(scaledIcon.getIconWidth(), scaledIcon.getIconHeight()); // Set button size to image size
        button.setPreferredSize(buttonSize);

        button.setText(null);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);



        int frameWidth = 420;  
        int frameHeight = 640; 
        int buttonWidth = buttonSize.width;
        int buttonHeight = buttonSize.height;
        int x = (frameWidth - buttonWidth) / 2;
        int y = (frameHeight - buttonHeight) / 2;

        button.setBounds(x, y, buttonWidth, buttonHeight);

        ImageIcon orImageIcon2 = new ImageIcon("src/boton2.png");
        Image originalImage2 = orImageIcon2.getImage();
        int desiredWidth2 = 150;  // cambiar dimensiones botn
        int desiredHeight2 = 50; // cambiar dimensiones botn
        Image scaledImage2 = originalImage2.getScaledInstance(desiredWidth2, desiredHeight2, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
        JButton button2 = new JButton(scaledIcon2);
        Dimension buttonSize2 = new Dimension(scaledIcon2.getIconWidth(), scaledIcon2.getIconHeight()); // Set button size to image size
        button2.setPreferredSize(buttonSize2);

        button2.setText(null);
        button2.setBorderPainted(false);
        button2.setFocusPainted(false);
        button2.setContentAreaFilled(false);

        int frameWidth2 = 420;  
        int frameHeight2 = 640; 
        int buttonWidth2 = buttonSize2.width;
        int buttonHeight2 = buttonSize2.height;
        int x2 = (frameWidth2 - buttonWidth2) / 2;
        int y2 = (frameHeight2 - buttonHeight2) / 2 + 100;

        button2.setBounds(x2, y2, buttonWidth2, buttonHeight2);
        
        frame.add(button2);
        frame.add(button);
        frame.add(label);
        frame.setVisible(true);




        // ICONO DEL PROGRAMA
        ImageIcon image = new ImageIcon("src/logo.png");
        frame.setIconImage(image.getImage());
        
        return frame;
    }
}

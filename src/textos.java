import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.RenderingHints.Key;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class textos {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Campos de Texto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // queda feo sin esto
        frame.setSize(400, 300);

        // Campo de texto pequeño
        JTextField textito = new JTextField("Escribe algo 1");
        textito.setForeground(Color.GRAY);
        textito.setBounds(50, 50, 300, 30); 

        textito.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textito.getText().equals("Escribe algo 1")) {
                    
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textito.getText().isEmpty()) {
                    textito.setForeground(Color.GRAY);
                    textito.setText("Escribe algo 1");
                }
            }
        });

        textito.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
               if (textito.getText().equals("Escribe algo 1")) {
                    textito.setText("");
                    textito.setForeground(Color.BLACK);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // sin esto peta 
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
            
        });
        

        frame.add(textito);

        // Área de texto grande con scroll
        JTextArea textazo = new JTextArea("Escribe algo 2");
        textazo.setForeground(Color.GRAY);

        JScrollPane scrollPane = new JScrollPane(textazo);
        scrollPane.setBounds(50, 90, 300, 150); 

        textazo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textazo.getText().equals("Escribe algo 2")) {
                    
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textazo.getText().isEmpty()) {
                    textazo.setForeground(Color.GRAY);
                    textazo.setText("Escribe algo 2");
                }
            }
        });

        textazo.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (textazo.getText().equals("Escribe algo 2")) {
                    textazo.setText("");
                    textazo.setForeground(Color.BLACK);
                    
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
            
        });
        frame.add(scrollPane);

        frame.setVisible(true);
    }
}
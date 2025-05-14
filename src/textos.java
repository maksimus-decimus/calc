import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class textos {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Campos de Texto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Usamos null layout para posicionamiento absoluto
        frame.setSize(400, 300);

        // Campo de texto pequeño
        JTextField textito = new JTextField("Escribe algo 1");
        textito.setForeground(Color.GRAY);
        textito.setBounds(50, 50, 300, 30); // x, y, ancho, alto

        textito.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textito.getText().equals("Escribe algo 1")) {
                    textito.setText("");
                    textito.setForeground(Color.BLACK);
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

        frame.add(textito);

        // Área de texto grande con scroll
        JTextArea textazo = new JTextArea("Escribe algo 2");
        textazo.setForeground(Color.GRAY);

        JScrollPane scrollPane = new JScrollPane(textazo);
        scrollPane.setBounds(50, 90, 300, 150); // x, y, ancho, alto

        textazo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textazo.getText().equals("Escribe algo 2")) {
                    textazo.setText("");
                    textazo.setForeground(Color.BLACK);
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
        frame.add(scrollPane);

        frame.setVisible(true);
    }
}
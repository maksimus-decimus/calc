import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class lista_desplegable {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lista Desplegable de Lenguajes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 1)); // fila 1

        // lenguaje y seleccion
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel("Lenguaje seleccionado: ");
        labelPanel.add(label);
        JLabel selectedLanguageLabel = new JLabel("Ninguno"); // seleccion
        labelPanel.add(selectedLanguageLabel);
        frame.add(labelPanel);

        // lista desplegable y botón
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String[] languages = {"Java", "Python", "C++"};
        JComboBox<String> comboBox = new JComboBox<>(languages);
        panel.add(comboBox);

        JButton selectButton = new JButton("Seleccionar");
        panel.add(selectButton);
        frame.add(panel);

        // eventos para cambiar el texto
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedLanguage = (String) comboBox.getSelectedItem();
                selectedLanguageLabel.setText(selectedLanguage); // Actualizar la etiqueta
            }
        });

        frame.setSize(500, 150);
        frame.setVisible(true);
    }
}
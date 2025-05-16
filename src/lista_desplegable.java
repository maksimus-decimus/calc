import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class lista_desplegable {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lista Desplegable de Lenguajes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 1)); // GridLayout para organizar en filas

        // Panel para la etiqueta y el lenguaje seleccionado
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel("Lenguaje seleccionado: ");
        labelPanel.add(label);
        JLabel selectedLanguageLabel = new JLabel("Ninguno"); // Inicialmente "Ninguno"
        labelPanel.add(selectedLanguageLabel);
        frame.add(labelPanel);

        // Panel para la lista desplegable y el botón
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String[] languages = {"Java", "Python", "C++"};
        JComboBox<String> comboBox = new JComboBox<>(languages);
        panel.add(comboBox);

        JButton selectButton = new JButton("Seleccionar");
        panel.add(selectButton);
        frame.add(panel);

        // ActionListener para el botón "Seleccionar"
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
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class kesuanji implements ActionListener {

    JFrame frame;
    JTextField textField;
    JTextArea historyArea; // Área de texto para el historial
    JScrollPane historyScrollPane; // Scroll para el historial

    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[10]; // Aumentar el tamaño para el botón de borrar historial
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton, clearHistoryButton; // Botón para borrar historial
    JPanel panel;

    Font fuente = new Font("Arial", Font.BOLD, 30);
    double num1, num2, result;
    char operator;

    BufferedImage backgroundImage;
    Random random = new Random();
    private List<String> history = new ArrayList<>(); // Lista para almacenar el historial

    kesuanji() {
        frame = new JFrame("Calculadora CASIO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 800); // Aumentar el tamaño para el historial
        frame.setLayout(null);

        // Cargar la imagen de fondo
        try {
            backgroundImage = ImageIO.read(new File("src/fondo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Crear un JPanel personalizado para dibujar la imagen de fondo
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setBounds(0, 0, 400, 700); // Aumentar el tamaño para el historial
        frame.setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null); // Establecer el layout a null para posicionar los componentes

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(fuente);
        textField.setEditable(false);
        backgroundPanel.add(textField); // Agregar al backgroundPanel

        // Área de texto para el historial
        historyArea = new JTextArea();
        historyArea.setFont(new Font("Arial", Font.PLAIN, 16));
        historyArea.setEditable(false);

        // Scroll para el historial
        historyScrollPane = new JScrollPane(historyArea);
        historyScrollPane.setBounds(50, 490, 300, 150); // Ajustar la posición y el tamaño
        backgroundPanel.add(historyScrollPane); // Agregar al backgroundPanel

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("C");
        clrButton = new JButton("CE");
        negButton = new JButton("(-)");
        clearHistoryButton = new JButton("Limpiar"); // Botón para borrar historial

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;
        functionButtons[9] = clearHistoryButton; // Botón para borrar historial

        for (int i = 0; i < 10; i++) {
            functionButtons[i].setFont(fuente);
            functionButtons[i].setFocusable(false);
            functionButtons[i].addActionListener(this);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(fuente);
            numberButtons[i].setFocusable(false);
            numberButtons[i].addActionListener(this);
        }

        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);
        clearHistoryButton.setBounds(50, 650, 300, 30); // Posicionar el botón de borrar historial
        backgroundPanel.add(clearHistoryButton); // Agregar el botón al backgroundPanel

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setOpaque(false); // Hacer el panel transparente

        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);

        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);

        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        backgroundPanel.add(panel); // Agregar al backgroundPanel
        backgroundPanel.add(negButton); // Agregar al backgroundPanel
        backgroundPanel.add(delButton); // Agregar al backgroundPanel
        backgroundPanel.add(clrButton); // Agregar al backgroundPanel

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        kesuanji calc = new kesuanji();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
                if (i == 9) {
                    playSound("src/nine.wav");
                }
            }
        }

        String textFieldText = textField.getText();

        if (textFieldText.equals("10") || textFieldText.equals("10.0")) {
            playSound("src/ten.wav");
        }

        if (textFieldText.equals("21") || textFieldText.equals("21.0")) {
            playSound("src/twennyone.wav");
        }

        if (e.getSource() == decButton) {
            if (!textField.getText().contains(".")) {
                textField.setText(textField.getText().concat("."));
            }
        }

        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }

        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }

        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
            playSound("src/whatsapp.wav");
        }

        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }

        if (e.getSource() == equButton) {
            try {
                num2 = Double.parseDouble(textField.getText());

                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        result = num1 / num2;
                        break;
                }

                // Simular animación de máquina tragaperras con el resultado
                animateSlotMachine(String.valueOf(result));
                String resultString = String.valueOf(result);
                addToHistory(num1 + " " + operator + " " + num2 + " = " + resultString); // Agregar al historial
                updateHistoryArea(); // Actualizar el área de texto del historial
                num1 = result;
                playSound("src/igual_a.wav"); // Reproducir sonido al presionar el botón de igual
            } catch (ArithmeticException ex) {
                textField.setText("Syntax Error");
                addToHistory("Syntax Error"); // Agregar al historial
                updateHistoryArea(); // Actualizar el área de texto del historial
            }
        }

        if (e.getSource() == clrButton) {
            textField.setText("");
        }

        if (e.getSource() == delButton) {
            String string = textField.getText();
            textField.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                textField.setText(textField.getText() + string.charAt(i));
            }
        }

        if (e.getSource() == negButton) {
            double temp = Double.parseDouble(textField.getText());
            temp *= -1;
            textField.setText(String.valueOf(temp));
        }

        if (e.getSource() == clearHistoryButton) {
            clearHistory(); // Borrar el historial
        }
    }

    private void animateSlotMachine(String result) {
        final Timer timer = new Timer(50, null); // Intervalo de 50ms
        final String[] symbols = {"@", "#", "$", "%", "&", "*", "+", "=", "?", "!"}; //Símbolos para la animación
        final int animationDuration = 30; // Duración de la animación (30 iteraciones)
        final String finalResult = result; // Resultado final de la operación
        final int numColumns = 4; // Número de columnas
        final String[][] columnSymbols = new String[numColumns][10]; // Matriz para almacenar los símbolos de cada columna

        // Inicializar la matriz de símbolos
        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < 10; j++) {
                columnSymbols[i][j] = symbols[random.nextInt(symbols.length)];
            }
        }

        timer.addActionListener(new ActionListener() {
            int count = 0;
            int[] offsets = new int[numColumns]; // Desplazamiento para cada columna

            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < numColumns; i++) {
                    offsets[i] = (offsets[i] + 1) % 10; // Desplazar el offset
                    sb.append(columnSymbols[i][offsets[i]]).append(" "); // Símbolo de la columna
                }
                textField.setText(sb.toString()); // Mostrar símbolos

                if (count >= animationDuration) { // Duración de la animación
                    timer.stop();
                    textField.setText(finalResult); // Mostrar el resultado final
                }
            }
        });

        timer.start();
    }

    public void playSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para agregar una entrada al historial
    private void addToHistory(String entry) {
        history.add(entry);
        if (history.size() > 10) { // Limitar el historial a 10 entradas
            history.remove(0);
        }
    }

    // Método para actualizar el área de texto del historial
    private void updateHistoryArea() {
        StringBuilder historyText = new StringBuilder();
        for (String entry : history) {
            historyText.append(entry).append("\n");
        }
        historyArea.setText(historyText.toString());
    }

    // Método para borrar el historial
    private void clearHistory() {
        history.clear(); // Limpiar la lista del historial
        updateHistoryArea(); // Actualizar el área de texto del historial
    }
}
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
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

public class KJ_UI extends JFrame implements ActionListener, KeyListener {

    private KJ_BE backend = new KJ_BE(); // Instancia del backend

    // Componentes visuales
    JFrame frame;
    JTextField textField;
    JTextArea historyArea; // Área de texto para el historial
    JScrollPane historyScrollPane; // Scroll para el historial
    JLabel gifLabel; // Label para mostrar el GIF

    private double num1, num2, result;
    private char operator;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[15]; // Aumentar el tamaño para las nuevas funciones
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton; // Botón para borrar historial
    JButton piButton, cosButton, tanButton, sqrButton, clearHistoryButton; // Nuevos botones
    JPanel panel;

    Font fuente = new Font("Arial", Font.BOLD, 30);

    BufferedImage backgroundImage;
    Random random = new Random();
    // private List<String> history = new ArrayList<>(); // Lista para almacenar el
    // historial

    public KJ_UI() {
        frame = new JFrame("Niño calculadora");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700); // Aumentar el tamaño para el historial
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
        backgroundPanel.setBounds(0, 0, 500, 700); // Aumentar el tamaño para el historial
        frame.setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null); // Establecer el layout a null para posicionar los componentes

        textField = new JTextField();
        textField.setBounds(50, 25, 400, 50);
        textField.setFont(fuente);
        textField.setEditable(false);
        backgroundPanel.add(textField); // Agregar al backgroundPanel

        // Área de texto para el historial
        historyArea = new JTextArea();
        historyArea.setFont(new Font("Arial", Font.PLAIN, 16));
        historyArea.setEditable(false);

        // Scroll para el historial
        historyScrollPane = new JScrollPane(historyArea);
        historyScrollPane.setBounds(50, 490, 400, 150); // Ajustar la posición y el tamaño
        backgroundPanel.add(historyScrollPane); // Agregar al backgroundPanel

        // Label para mostrar el GIF
        gifLabel = new JLabel();
        gifLabel.setBounds(50, 100, 300, 300); // Ajustar la posición y el tamaño
        gifLabel.setVisible(false); // Inicialmente invisible
        backgroundPanel.add(gifLabel); // Agregar al backgroundPanel

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("C");
        clrButton = new JButton("CE");
        negButton = new JButton("(+/-)");
        clearHistoryButton = new JButton("Limpiar"); // Botón para borrar historial
        piButton = new JButton("π");
        cosButton = new JButton("cos");
        tanButton = new JButton("tan");
        sqrButton = new JButton("sqr");

        // Establecer el color de fondo a gris y el color de texto a blanco
        Color buttonBackgroundColor = Color.BLACK;
        Color buttonForegroundColor = Color.WHITE;
        Color operationButtonColor = new Color(255, 128, 0); // Naranja suave (PeachPuff)
        Border roundedBorder = new LineBorder(buttonBackgroundColor, 1, true); // Borde redondeado

        // Establecer el color de fondo y el color de texto para los botones de función
        addButton.setBackground(operationButtonColor);
        addButton.setForeground(buttonBackgroundColor);
        addButton.setFocusPainted(false); // Eliminar el focus border
        addButton.setBorder(roundedBorder); // Establecer el borde redondeado

        subButton.setBackground(operationButtonColor);
        subButton.setForeground(buttonBackgroundColor);
        subButton.setFocusPainted(false);
        subButton.setBorder(roundedBorder);

        mulButton.setBackground(operationButtonColor);
        mulButton.setForeground(buttonBackgroundColor);
        mulButton.setFocusPainted(false);
        mulButton.setBorder(roundedBorder);

        divButton.setBackground(operationButtonColor);
        divButton.setForeground(buttonBackgroundColor);
        divButton.setFocusPainted(false);
        divButton.setBorder(roundedBorder);

        decButton.setBackground(buttonBackgroundColor);
        decButton.setForeground(buttonForegroundColor);
        decButton.setFocusPainted(false);
        decButton.setBorder(roundedBorder);

        equButton.setBackground(operationButtonColor);
        equButton.setForeground(buttonBackgroundColor);
        equButton.setFocusPainted(false);
        equButton.setBorder(roundedBorder);

        delButton.setBackground(buttonBackgroundColor);
        delButton.setForeground(buttonForegroundColor);
        delButton.setFocusPainted(false);
        delButton.setBorder(roundedBorder);

        clrButton.setBackground(buttonBackgroundColor);
        clrButton.setForeground(buttonForegroundColor);
        clrButton.setFocusPainted(false);
        clrButton.setBorder(roundedBorder);

        negButton.setBackground(buttonBackgroundColor);
        negButton.setForeground(buttonForegroundColor);
        negButton.setFocusPainted(false);
        negButton.setBorder(roundedBorder);

        clearHistoryButton.setBackground(buttonBackgroundColor);
        clearHistoryButton.setForeground(buttonForegroundColor);
        clearHistoryButton.setFocusPainted(false);
        clearHistoryButton.setBorder(roundedBorder);

        piButton.setBackground(new Color(0, 0, 255)); // Rosa claro (LightPink)
        piButton.setForeground(buttonForegroundColor);
        piButton.setFocusPainted(false);
        piButton.setBorder(roundedBorder);

        cosButton.setBackground(new Color(0, 0, 255));
        cosButton.setForeground(buttonForegroundColor);
        cosButton.setFocusPainted(false);
        cosButton.setBorder(roundedBorder);

        tanButton.setBackground(new Color(0, 0, 255));
        tanButton.setForeground(buttonForegroundColor);
        tanButton.setFocusPainted(false);
        tanButton.setBorder(roundedBorder);

        sqrButton.setBackground(new Color(0, 0, 255));
        sqrButton.setForeground(buttonForegroundColor);
        sqrButton.setFocusPainted(false);
        sqrButton.setBorder(roundedBorder);
        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;
        functionButtons[9] = piButton;
        functionButtons[10] = cosButton;
        functionButtons[11] = tanButton;
        functionButtons[12] = sqrButton;
        functionButtons[13] = clearHistoryButton;

        for (int i = 0; i < 14; i++) {
            functionButtons[i].setFont(fuente);
            functionButtons[i].setFocusable(false);
            functionButtons[i].addActionListener(this);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(fuente);
            numberButtons[i].setFocusable(false);
            numberButtons[i].addActionListener(this);
            numberButtons[i].setBackground(buttonBackgroundColor); // Establecer el color de fondo
            numberButtons[i].setForeground(buttonForegroundColor); // Establecer el color de texto
            numberButtons[i].setFocusPainted(false); // Eliminar el focus border
            numberButtons[i].setBorder(roundedBorder); // Establecer el borde redondeado
        }

        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);
        clearHistoryButton.setBounds(350, 430, 100, 50);
        piButton.setBounds(50, 370, 100, 50);
        cosButton.setBounds(150, 370, 100, 50);
        tanButton.setBounds(250, 370, 100, 50);
        sqrButton.setBounds(350, 370, 100, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 400, 260);
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
        backgroundPanel.add(piButton);
        backgroundPanel.add(cosButton);
        backgroundPanel.add(tanButton);
        backgroundPanel.add(sqrButton);
        backgroundPanel.add(clearHistoryButton);

        frame.setVisible(true);
        frame.addKeyListener(this); // Agregar el KeyListener al JFrame
        frame.setFocusable(true); // Asegurarse de que el JFrame pueda recibir eventos de teclado
        frame.requestFocusInWindow(); // Solicitar el foco para que el JFrame reciba los eventos

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
            if (!textField.getText().isEmpty()) {
                num1 = Double.parseDouble(textField.getText());
            }
            operator = '+';
            textField.setText("");
        }

        if (e.getSource() == subButton) {
            if (!textField.getText().isEmpty()) {
                num1 = Double.parseDouble(textField.getText());
            }
            operator = '-';
            textField.setText("");
        }

        if (e.getSource() == mulButton) {
            if (!textField.getText().isEmpty()) {
                num1 = Double.parseDouble(textField.getText());
            }
            operator = '*';
            textField.setText("");
            playSound("src/whatsapp.wav");
        }

        if (e.getSource() == divButton) {
            if (!textField.getText().isEmpty()) {
                num1 = Double.parseDouble(textField.getText());
            }
            operator = '/';
            textField.setText("");
        }

        if (e.getSource() == equButton) {

            try {
                num2 = Double.parseDouble(textField.getText());
                result = backend.operate(num1, num2, operator);

                tragaperras(String.valueOf(result));
                String resultString = String.valueOf(result);
                backend.addToHistory(num1 + " " + operator + " " + num2 + " = " + resultString);
                updateHistoryArea();
                num1 = result;
                playSound("src/igual_a.wav");
            } catch (ArithmeticException ex) {
                textField.setText("Syntax Error");
                backend.addToHistory("Syntax Error");
                updateHistoryArea();
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

        if (e.getSource() == piButton) {
            textField.setText(String.valueOf(backend.pi()));
        }

        if (e.getSource() == cosButton) {
            double val = Double.parseDouble(textField.getText());
            textField.setText(String.valueOf(backend.cos(val)));
        }

        if (e.getSource() == tanButton) {
            double val = Double.parseDouble(textField.getText());
            textField.setText(String.valueOf(backend.tan(val)));
        }

        if (e.getSource() == sqrButton) {
            double val = Double.parseDouble(textField.getText());
            textField.setText(String.valueOf(backend.sqr(val)));
        }
    }

    private void tragaperras(String result) {
        final Timer timer = new Timer(50, null); // Intervalo de 50ms
        final String[] symbols = { "@", "#", "$", "%", "&", "*", "+", "=", "?", "!" }; // Símbolos para la animación
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
        backend.addToHistory(entry);

    }

    // Método para actualizar el área de texto del historial
    private void updateHistoryArea() {
        StringBuilder historyText = new StringBuilder();
        for (String entry : backend.getHistory()) {
            historyText.append(entry).append("\n");
        }
        historyArea.setText(historyText.toString());
    }

    // Método para borrar el historial
    private void clearHistory() {
        backend.clearHistory(); // Limpiar la lista del historial
        updateHistoryArea(); // Actualizar el área de texto del historial
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (Character.isDigit(c) || c == '.') { // Si es un dígito o un punto
            textField.setText(textField.getText() + c); // Agregar al textField
        } else if (c == '+' || c == '-' || c == '*' || c == '/') { // Si es un operador
            if (!textField.getText().isEmpty()) {
                num1 = Double.parseDouble(textField.getText());
            }
            operator = c; // Establecer el operador
            textField.setText(""); // Limpiar el textField
        } else if (c == KeyEvent.VK_ENTER) { // Si es la tecla Enter
            try {
                num2 = Double.parseDouble(textField.getText());
                result = backend.operate(num1, num2, operator);
                textField.setText(String.valueOf(result));
                backend.addToHistory(num1 + " " + operator + " " + num2 + " = " + result);
                updateHistoryArea();
                num1 = result;
            } catch (ArithmeticException ex) {
                textField.setText("Syntax Error");
                backend.addToHistory("Syntax Error");
                updateHistoryArea();
            }
        } else if (c == KeyEvent.VK_BACK_SPACE) { // Si es la tecla de retroceso
            String string = textField.getText();
            textField.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                textField.setText(textField.getText() + string.charAt(i));
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // No necesitamos implementar este método en este caso
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // No necesitamos implementar este método en este caso
    }
}
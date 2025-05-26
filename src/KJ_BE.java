import java.util.ArrayList;
import java.util.List;

public class KJ_BE {
    private final List<String> history = new ArrayList<>();

    public double operate(double num1, double num2, char operator) {
        switch (operator) {
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
            case '/':
                if (num2 == 0) throw new ArithmeticException("División entre cero");
                return num1 / num2;
            default: throw new IllegalArgumentException("Operador no soportado");
        }
    }

    public double cos(double value) { return Math.cos(value); }
    public double tan(double value) { return Math.tan(value); }
    public double sqr(double value) { return value * value; }
    public double pi() { return Math.PI; }

    public void addToHistory(String entry) {
        history.add(entry);
        if (history.size() > 10) history.remove(0);
    }
    public List<String> getHistory() {
        return new ArrayList<>(history);
    }
    public void clearHistory() {
        history.clear();
    }
}
package uIDesigner;

/**
 * 1. Повторіть розробку застосунку з графічним інтерфейсом користувача "Геометричні фігури" з попереднього завдання,
 * але тепер використовуйте Графічний Дизайнер IDE.
 * 2. Зазначте у документованому коментарі класу номер завдання, своє ім'я та прізвище.
 * 3. Надішліть викладачеві лістинг коду програми, результат її роботи та скріншот з інтерфейсом програми.
 *
 * @author Olha Nozdriukhina
 * @version 1.0
 */

import javax.swing.*;

public class Calculator {
    private JPanel panelContent;
    private JTextField sideField;
    private JTextField heightField;
    private JTextField base1Field;
    private JTextField base2Field;
    private JTextField topField;
    private JButton resultButton;

    public Calculator() {
        resultButton.addActionListener(e -> calculateAndDisplay());
    }

    private void calculateAndDisplay() {
        try {
            double side = Double.parseDouble(sideField.getText());
            double height = Double.parseDouble(heightField.getText());
            double base1 = Double.parseDouble(base1Field.getText());
            double base2 = Double.parseDouble(base2Field.getText());
            double top = Double.parseDouble(topField.getText());

            String result = "Периметр квадрата: " + calculateSquarePerimeter(side) + "\n" +
                    "Площа трикутника: " + calculateTriangleArea(base1, height) + "\n" +
                    "Периметр прямокутника: " + calculateRectanglePerimeter(base1, base2) + "\n" +
                    "Площа трапеції: " + calculateTrapezoidArea(base1, base2, height, top);

            JOptionPane.showMessageDialog(panelContent, result, "Результати обчислень", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(panelContent, "Помилка введення. Введіть числові значення.", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double calculateSquarePerimeter(double side) {
        return 4 * side;
    }

    private double calculateTriangleArea(double base, double height) {
        return 0.5 * base * height;
    }

    private double calculateRectanglePerimeter(double base1, double base2) {
        return 2 * (base1 + base2);
    }

    private double calculateTrapezoidArea(double base1, double base2, double height, double top) {
        return 0.5 * (base1 + base2) * height;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().panelContent);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}

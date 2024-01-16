package task_3_5_2_gui;

/**
 * 1. Додайте пакет gui.
 * 2. Розробіть застосунок з графічним інтерфейсом користувача "Геометричні фігури", який обчислює периметр і площу
 * для квадрата, трикутника, прямокутника і трапеції, використовуючи дані, які вводить користувач до повторно
 * використовуваних текстових полів.
 * 3. Передбачте захист від помилкового введення користувачем із виведенням повідомлення про помилку за допомогою
 * компонента JOptionPane.
 *
 * @author Olha Nozdriukhina
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeometricFigures extends JFrame {

    final JTextField sideField, heightField, base1Field, base2Field, topField;

    public GeometricFigures() {
        setTitle("Геометричні фігури");
        setSize(700, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        JLabel sideLabel = new JLabel("Сторона квадрата:");
        sideField = new JTextField();
        add(sideLabel);
        add(sideField);

        JLabel heightLabel = new JLabel("Висота трикутника:");
        heightField = new JTextField();
        add(heightLabel);
        add(heightField);

        JLabel base1Label = new JLabel("Основа прямокутника:");
        base1Field = new JTextField();
        add(base1Label);
        add(base1Field);

        JLabel base2Label = new JLabel("Інша сторона прямокутника:");
        base2Field = new JTextField();
        add(base2Label);
        add(base2Field);

        JLabel topLabel = new JLabel("Верхня сторона трапеції:");
        topField = new JTextField();
        add(topLabel);
        add(topField);

        JButton calculateButton = new JButton("Обчислити");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAndDisplay();
            }
        });
        add(calculateButton);

        setVisible(true);
    }

    private void calculateAndDisplay() {
        try {
            double side = Double.parseDouble(sideField.getText());
            double height = Double.parseDouble(heightField.getText());
            double base1 = Double.parseDouble(base1Field.getText());
            double base2 = Double.parseDouble(base2Field.getText());
            double top = Double.parseDouble(topField.getText());


            JOptionPane.showMessageDialog(this, "Результати:\n" +
                    "Периметр квадрата: " + calculateSquarePerimeter(side) + "\n" +
                    "Площа трикутника: " + calculateTriangleArea(base1, height) + "\n" +
                    "Периметр прямокутника: " + calculateRectanglePerimeter(base1, base2) + "\n" +
                    "Площа трапеції: " + calculateTrapezoidArea(base1, base2, height, top));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Помилка введення. Введіть числові значення.");
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
        SwingUtilities.invokeLater(GeometricFigures::new);
    }
}


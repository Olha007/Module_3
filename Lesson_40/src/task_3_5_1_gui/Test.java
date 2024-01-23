package task_3_5_1_gui;

/**
 * 1. Додайте пакет gui.
 * 2. Розробіть застосунок з графічним інтерфейсом користувача "Тестування", що відображає 3 питання і кілька
 * відповідей до кожного з питань (передбачити одноваріантні і багатоваріантні відповіді).
 * 3. Програма повинна розраховувати відсоток правильних відповідей і у кінці тестування виводити його.
 * 4. Зазначте у документованому коментарі класу номер завдання, своє ім'я та прізвище.
 * 5. Надішліть викладачеві лістинг коду програми, результат її роботи та скріншот з інтерфейсом програми.
 *
 * @author Olha Nozdriukhina
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test extends JFrame {

    private int correctAnswers = 0;

    public Test() {
        setTitle("Testing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 300);
        setLocationRelativeTo(null); // Встановлено положення форми по центру

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        // Питання 1
        JLabel questionOne = new JLabel("<html>1. What programming language are you learning?</html>");
        JPanel answersPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup answerGroup1 = new ButtonGroup();
        JRadioButton answer1A = new JRadioButton("A) Java");
        JRadioButton answer1B = new JRadioButton("B) Python");
        JRadioButton answer1C = new JRadioButton("C) C++");
        answerGroup1.add(answer1A);
        answerGroup1.add(answer1B);
        answerGroup1.add(answer1C);
        answersPanel1.add(answer1A);
        answersPanel1.add(answer1B);
        answersPanel1.add(answer1C);

        // Питання 2
        JLabel questionSecond = new JLabel("<html>2. Which operating system do you prefer?</html>");
        JPanel answersPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup answerGroup2 = new ButtonGroup();
        JRadioButton answer2A = new JRadioButton("A) Windows");
        JRadioButton answer2B = new JRadioButton("B) macOS");
        JRadioButton answer2C = new JRadioButton("C) Linux");
        answerGroup2.add(answer2A);
        answerGroup2.add(answer2B);
        answerGroup2.add(answer2C);
        answersPanel2.add(answer2A);
        answersPanel2.add(answer2B);
        answersPanel2.add(answer2C);

        // Питання 3
        JLabel questionThird = new JLabel("<html>3. What programming languages do you consider mandatory to learn?</html>");
        JPanel answersPanel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JCheckBox answer3A = new JCheckBox("A) Java");
        JCheckBox answer3B = new JCheckBox("B) Python");
        JCheckBox answer3C = new JCheckBox("C) JavaScript");
        answersPanel3.add(answer3A);
        answersPanel3.add(answer3B);
        answersPanel3.add(answer3C);

        JButton checkButton = new JButton("Check the answers");
        JLabel result = new JLabel();

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                correctAnswers = 0;

                // Перевірка відповідей для питання 1
                if (answer1A.isSelected()) {
                    correctAnswers++;
                }

                // Перевірка відповідей для питання 2
                if (answer2B.isSelected()) {
                    correctAnswers++;
                }

                // Перевірка відповідей для питання 3
                if (answer3A.isSelected() && answer3B.isSelected() && answer3C.isSelected()) {
                    correctAnswers++;
                }

                double percentageCorrect = (double) correctAnswers / 3 * 100;
                result.setText("Your percentage of correct answers:" + percentageCorrect + "%");
            }
        });

        panel.add(questionOne);
        panel.add(questionOne);
        panel.add(answersPanel1);
        panel.add(questionSecond);
        panel.add(answersPanel2);
        panel.add(questionThird);
        panel.add(answersPanel3);
        panel.add(checkButton);
        panel.add(result);

        setContentPane(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Test::new);
    }
}



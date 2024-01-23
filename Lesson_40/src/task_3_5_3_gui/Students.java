package task_3_5_3_gui;

/**
 * 1. Розробіть застосунок з графічним інтерфейсом користувача "Студенти", який містить таблицю зі стовпцями:
 * прізвище, ім'я, дата народження та спеціальність студента та дозволяє виконувати додання, редагування та видалення
 * рядків з даними студента.
 * 2. Передбачте меню з командами запису даних таблиці до файлу формату значень, розділених комами (Comma-Separated Value - CSV).
 * 3. Передбачте перевірку валідного віку студенту у діапазоні 16-27 років та виведення діалогового
 * вікна з попередженням при спробі введення невалідної дати народження.
 * 4. Зазначте у документованому коментарі класу номер завдання, своє ім'я та прізвище.
 * 5. Надішліть викладачеві лістинг коду програми, результат її роботи та скріншот з інтерфейсом програми.
 *
 * @author Olha Nozdriukhina
 * @version 1.0
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Students extends JFrame {

    private final DefaultTableModel tableModel;
    private final JTable table;

    public Students() {
        setTitle("Студенти");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Прізвище");
        tableModel.addColumn("Ім'я");
        tableModel.addColumn("Дата народження");
        tableModel.addColumn("Спеціальність");

        table = new JTable(tableModel);

        JButton addButton = new JButton("Додати");
        addButton.addActionListener(e -> addStudent()); // Лямбда-вираз

        JButton editButton = new JButton("Редагувати");
        editButton.addActionListener(e -> editStudent()); // Лямбда-вираз

        JButton deleteButton = new JButton("Видалити");
        deleteButton.addActionListener(e -> deleteStudent()); // Лямбда-вираз

        JButton saveButton = new JButton("Зберегти в CSV");
        saveButton.addActionListener(e -> saveToCSV()); // Лямбда-вира

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void addStudent() {
        JDialog dialog = createStudentDialog("Додати студента");
        dialog.setLocationRelativeTo(this);

        JButton addButton = new JButton("Додати");
        addButton.addActionListener(e -> {
            processStudentData(dialog, false, -1);
        });

        dialog.add(addButton);
        dialog.setVisible(true);
    }

    private void editStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            JDialog dialog = createStudentDialog("Редагувати студента");
            JTextField lastNameField = (JTextField) dialog.getContentPane().getComponent(1);
            JTextField firstNameField = (JTextField) dialog.getContentPane().getComponent(3);
            JTextField birthDateField = (JTextField) dialog.getContentPane().getComponent(5);
            JTextField specialtyField = (JTextField) dialog.getContentPane().getComponent(7);

            lastNameField.setText(tableModel.getValueAt(selectedRow, 0).toString());
            firstNameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
            birthDateField.setText(tableModel.getValueAt(selectedRow, 2).toString());
            specialtyField.setText(tableModel.getValueAt(selectedRow, 3).toString());

            JButton editButton = new JButton("Редагувати");
            dialog.setLocationRelativeTo(this);
            editButton.addActionListener(e -> {
                processStudentData(dialog, true, selectedRow);
            });

            dialog.add(editButton);
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Виберіть студента для редагування");
        }
    }

    private void deleteStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Виберіть студента для видалення");
        }
    }

    private void saveToCSV() {
        // Збереження даних в CSV файл
        try (FileWriter writer = new FileWriter("students.csv")) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                List<String> rowData = new ArrayList<>();
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    rowData.add(tableModel.getValueAt(i, j).toString());
                }
                writer.write(String.join(",", rowData) + "\n");
            }
            JOptionPane.showMessageDialog(this, "Дані успішно збережено в CSV файл");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Помилка при збереженні в CSV файл");
        }
    }

    private JDialog createStudentDialog(String title) {
        JDialog dialog = new JDialog(this, title, true);
        dialog.setSize(300, 200);
        dialog.setLayout(new GridLayout(5, 2));

        dialog.add(new JLabel("Прізвище:"));
        dialog.add(new JTextField());
        dialog.add(new JLabel("Ім'я:"));
        dialog.add(new JTextField());
        dialog.add(new JLabel("Дата народження:"));
        dialog.add(new JTextField());
        dialog.add(new JLabel("Спеціальність:"));
        dialog.add(new JTextField());
        dialog.add(new JLabel());

        return dialog;
    }

    private void processStudentData(JDialog dialog, boolean isEdit, int selectedRow) {
        JTextField lastNameField = (JTextField) dialog.getContentPane().getComponent(1);
        JTextField firstNameField = (JTextField) dialog.getContentPane().getComponent(3);
        JTextField birthDateField = (JTextField) dialog.getContentPane().getComponent(5);
        JTextField specialtyField = (JTextField) dialog.getContentPane().getComponent(7);

        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String birthDateText = birthDateField.getText();
        String specialty = specialtyField.getText();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = dateFormat.parse(birthDateText);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(birthDate);
            int age = Calendar.getInstance().get(Calendar.YEAR) - calendar.get(Calendar.YEAR);

            if (age < 16 || age > 27) {
                JOptionPane.showMessageDialog(dialog, "Невалідний вік. Дозволено вік від 16 до 27 років");
                return;
            }

            if (isEdit) {
                tableModel.setValueAt(lastName, selectedRow, 0);
                tableModel.setValueAt(firstName, selectedRow, 1);
                tableModel.setValueAt(birthDateText, selectedRow, 2);
                tableModel.setValueAt(specialty, selectedRow, 3);
            } else {
                tableModel.addRow(new Object[]{lastName, firstName, birthDateText, specialty});
            }

            dialog.dispose();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(dialog, "Невалідна дата народження. Використовуйте формат yyyy-MM-dd");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Students().setVisible(true));
    }
}


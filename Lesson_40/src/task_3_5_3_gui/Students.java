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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Прізвище");
        tableModel.addColumn("Ім'я");
        tableModel.addColumn("Дата народження");
        tableModel.addColumn("Спеціальність");

        table = new JTable(tableModel);

        JButton addButton = new JButton("Додати");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        JButton editButton = new JButton("Редагувати");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editStudent();
            }
        });

        JButton deleteButton = new JButton("Видалити");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });

        JButton saveButton = new JButton("Зберегти в CSV");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToCSV();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void addStudent() {
        JDialog dialog = new JDialog(this, "Додати студента", true);
        dialog.setSize(300, 200);
        dialog.setLayout(new GridLayout(5, 2));

        JLabel lastNameLabel = new JLabel("Прізвище:");
        JTextField lastNameField = new JTextField();
        JLabel firstNameLabel = new JLabel("Ім'я:");
        JTextField firstNameField = new JTextField();
        JLabel birthDateLabel = new JLabel("Дата народження:");
        JTextField birthDateField = new JTextField();
        JLabel specialtyLabel = new JLabel("Спеціальність:");
        JTextField specialtyField = new JTextField();

        JButton addButton = new JButton("Додати");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lastName = lastNameField.getText();
                String firstName = firstNameField.getText();
                String birthDateText = birthDateField.getText();
                String specialty = specialtyField.getText();

                // Перевірка валідності дати народження
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date birthDate = dateFormat.parse(birthDateText);

                    // Перевірка віку у діапазоні 16-27 років
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(birthDate);
                    int age = Calendar.getInstance().get(Calendar.YEAR) - calendar.get(Calendar.YEAR);

                    if (age < 16 || age > 27) {
                        JOptionPane.showMessageDialog(dialog, "Невалідний вік. Дозволено вік від 16 до 27 років");
                        return;
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(dialog, "Невалідна дата народження. Використовуйте формат yyyy-MM-dd");
                    return;
                }

                tableModel.addRow(new Object[]{lastName, firstName, birthDateText, specialty});
                dialog.dispose();
            }
        });

        dialog.add(lastNameLabel);
        dialog.add(lastNameField);
        dialog.add(firstNameLabel);
        dialog.add(firstNameField);
        dialog.add(birthDateLabel);
        dialog.add(birthDateField);
        dialog.add(specialtyLabel);
        dialog.add(specialtyField);
        dialog.add(new JLabel());
        dialog.add(addButton);

        dialog.setVisible(true);
    }


    private void editStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            JDialog dialog = new JDialog(this, "Редагувати студента", true);
            dialog.setSize(300, 200);
            dialog.setLayout(new GridLayout(5, 2));

            JLabel lastNameLabel = new JLabel("Прізвище:");
            JTextField lastNameField = new JTextField(tableModel.getValueAt(selectedRow, 0).toString());
            JLabel firstNameLabel = new JLabel("Ім'я:");
            JTextField firstNameField = new JTextField(tableModel.getValueAt(selectedRow, 1).toString());
            JLabel birthDateLabel = new JLabel("Дата народження:");
            JTextField birthDateField = new JTextField(tableModel.getValueAt(selectedRow, 2).toString());
            JLabel specialtyLabel = new JLabel("Спеціальність:");
            JTextField specialtyField = new JTextField(tableModel.getValueAt(selectedRow, 3).toString());

            JButton editButton = new JButton("Редагувати");
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tableModel.setValueAt(lastNameField.getText(), selectedRow, 0);
                    tableModel.setValueAt(firstNameField.getText(), selectedRow, 1);
                    tableModel.setValueAt(birthDateField.getText(), selectedRow, 2);
                    tableModel.setValueAt(specialtyField.getText(), selectedRow, 3);
                    dialog.dispose();
                }
            });

            dialog.add(lastNameLabel);
            dialog.add(lastNameField);
            dialog.add(firstNameLabel);
            dialog.add(firstNameField);
            dialog.add(birthDateLabel);
            dialog.add(birthDateField);
            dialog.add(specialtyLabel);
            dialog.add(specialtyField);
            dialog.add(new JLabel());
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Students app = new Students();
                app.setVisible(true);
            }
        });
    }
}


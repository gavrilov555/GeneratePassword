package ru.gavrilov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneratedPasswordUI extends JFrame {

    private JTextField passwordField;
    private JComboBox<String> formatComboBox;
    private JSpinner lengthSpinner;

    private JTextField descriptionField;

    public GeneratedPasswordUI() {
        super("Генератор паролей");

        // Создаем компоненты для выбора формата и длины пароля
        String[] formats = {"Только цифры", "Только буквы", "Цифры и буквы", "Цифры, буквы и символы"};
        formatComboBox = new JComboBox<>(formats);
        SpinnerNumberModel lengthModel = new SpinnerNumberModel(8, 4, 32, 1);
        lengthSpinner = new JSpinner(lengthModel);

        // Создаем кнопку для генерации пароля
        JButton generateButton = new JButton("Создать пароль");
        JButton saveButton = new JButton("Сохранить пароль");


        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Генерируем пароль и отображаем его в текстовом поле
                String password = SimpleGeneratePassword.generatePassword(
                        (String) formatComboBox.getSelectedItem(),
                        (int) lengthSpinner.getValue(),
                        descriptionField.getText());
                passwordField.setText(password);

                // Устанавливаем цвет фона текстового поля в зависимости от сложности пароля
                if (SimpleGeneratePassword.checkPasswordStrength(password).equals("Слабый пароль")) {
                    passwordField.setBackground(Color.RED);
                } else if (SimpleGeneratePassword.checkPasswordStrength(password).equals("Умеренный пароль")) {
                    passwordField.setBackground(Color.YELLOW);
                } else {
                    passwordField.setBackground(Color.GREEN);
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Сохраняем пароль и описание в файл
                String password = passwordField.getText();
                String description = descriptionField.getText();
                SimpleGeneratePassword.savePassword(password, description);

                // Очищаем текстовые поля после сохранения
                passwordField.setText("");
                descriptionField.setText("");
            }
        });


        // Создаем текстовое поле для отображения пароля
        passwordField = new JTextField(20);
        passwordField.setEditable(false);

        // Создаем текстовое поле для ввода описания к паролю
        descriptionField = new JTextField(30);
        descriptionField.setEditable(true);

        // Создаем панель для компонентов выбора формата и длины пароля
        JPanel optionsPanel = new JPanel(new GridLayout(3, 2));
        optionsPanel.add(new JLabel(" Формат:"));
        optionsPanel.add(formatComboBox);
        optionsPanel.add(new JLabel(" Длина пароля:"));
        optionsPanel.add(lengthSpinner);
        optionsPanel.add(new JLabel());
        optionsPanel.add(generateButton);

        // Создаем панель для текстового поля с паролем
        JPanel passwordPanel = new JPanel(new FlowLayout());
        passwordPanel.add(new JLabel(" Пароль:"));
        passwordPanel.add(passwordField);


        JPanel savePanel = new JPanel(new FlowLayout());
        savePanel.add(new JLabel(" Описание к паролю:"));
        savePanel.add(descriptionField);
        savePanel.add(saveButton);


        // Добавляем панели на JFrame
        setLayout(new BorderLayout());
        add(optionsPanel, BorderLayout.NORTH);
        add(passwordPanel, BorderLayout.CENTER);
        add(savePanel, BorderLayout.SOUTH);

        // Настраиваем JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 180);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GeneratedPasswordUI();
    }
}

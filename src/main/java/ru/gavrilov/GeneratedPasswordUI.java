package ru.gavrilov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneratedPasswordUI extends JFrame {

    private JTextField passwordField;
    private JComboBox<String> formatComboBox;
    private JSpinner lengthSpinner;

    public GeneratedPasswordUI() {
        super("Генератор паролей");

        // Создаем компоненты для выбора формата и длины пароля
        String[] formats = {"Только цифры","Только буквы", "Цифры и буквы", "Цифры, буквы и символы"};
        formatComboBox = new JComboBox<>(formats);
        SpinnerNumberModel lengthModel = new SpinnerNumberModel(8, 4, 32, 1);
        lengthSpinner = new JSpinner(lengthModel);

        // Создаем кнопку для генерации пароля
        JButton generateButton = new JButton("Создать пароль");
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Генерируем пароль и отображаем его в текстовом поле
                String password = SimpleGeneratePassword.generatePassword(
                        (String) formatComboBox.getSelectedItem(),
                        (int) lengthSpinner.getValue());
                passwordField.setText(password);
            }
        });

        // Создаем текстовое поле для отображения пароля
        passwordField = new JTextField(20);
        passwordField.setEditable(false);

        // Создаем панель для компонентов выбора формата и длины пароля
        JPanel optionsPanel = new JPanel(new GridLayout(3, 2));
        optionsPanel.add(new JLabel("Формат:"));
        optionsPanel.add(formatComboBox);
        optionsPanel.add(new JLabel("Длина пароля:"));
        optionsPanel.add(lengthSpinner);
        optionsPanel.add(new JLabel());
        optionsPanel.add(generateButton);

        // Создаем панель для текстового поля с паролем
        JPanel passwordPanel = new JPanel(new FlowLayout());
        passwordPanel.add(new JLabel("Пароль:"));
        passwordPanel.add(passwordField);

        // Добавляем панели на JFrame
        setLayout(new BorderLayout());
        add(optionsPanel, BorderLayout.NORTH);
        add(passwordPanel, BorderLayout.CENTER);

        // Настраиваем JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GeneratedPasswordUI();
    }
}

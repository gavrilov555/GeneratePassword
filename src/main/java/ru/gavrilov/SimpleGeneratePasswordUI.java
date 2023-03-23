package ru.gavrilov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SimpleGeneratePasswordUI extends JFrame implements ActionListener {
    private JLabel passwordLabel;
    private JTextField passwordField;

    private JTextField lengthPassword;

    private JButton generateButton;


    public SimpleGeneratePasswordUI() {
        setTitle("Генератор паролей");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel lengthLabel = new JLabel(" Укажите длину пароля:");
        panel.add(lengthLabel);

        lengthPassword = new JTextField();
        panel.add(lengthPassword);

        generateButton = new JButton(" Сгенерировать");
        generateButton.addActionListener(this);
        panel.add(generateButton);

        passwordLabel = new JLabel(" Сгенерированный пароль:");
        panel.add(passwordLabel);


       passwordField = new JTextField();
        passwordField.setEditable(false);
        panel.add(passwordField);



        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateButton) {
            String regex = "\\d+"; // регулярное выражение, которое соответствует только цифрам
            if (lengthPassword.getText().matches(regex)) {
                int length = Integer.parseInt(lengthPassword.getText());
                String password = SimpleGeneratePassword.generatePassword(length);
                passwordField.setText(password);
            } else {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Некорректный ввод длины пароля. Введите только число.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SimpleGeneratePasswordUI ui = new SimpleGeneratePasswordUI();
    }

}

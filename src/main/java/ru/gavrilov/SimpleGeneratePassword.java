package ru.gavrilov;

import java.security.SecureRandom;
import java.util.Scanner;

public class SimpleGeneratePassword {

    public void passwordLength() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите желаемое кол-во символов: ");
        int passwordLength = scanner.nextInt();
        String password = generatePassword(passwordLength);
        System.out.println("Ваш пароль: " + password);
        }

        public void passwordUpdate() {
        }


    private String generatePassword(int length) {
        String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefgijklmnopqrstuvwxyz0123456789!@#$%&*()_-<>";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedCharacters.length());
            char randomChar = allowedCharacters.charAt(index);
            password.append(randomChar);
        }
        return password.toString();
    }

    public static void main(String[] args) {
        SimpleGeneratePassword sgp = new SimpleGeneratePassword();
        sgp.passwordLength();
    }

}
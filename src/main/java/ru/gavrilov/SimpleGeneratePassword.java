package ru.gavrilov;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class SimpleGeneratePassword {

    private static final String CHAR_LETTER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBER = "123456789";
    private static final String OTHER_CHAR = "!@#$%^&*()_+-=[]?";

    private static final String PASSWORD_LETTER_NUMBER_BASE = CHAR_LETTER + NUMBER;

    private static final String PASSWORD_ALLOW_BASE = CHAR_LETTER + NUMBER + OTHER_CHAR;

    private static final String PASSWORD_LETTER_NUMBER_BASE_SHUFFLE = shuffleString(PASSWORD_LETTER_NUMBER_BASE);

    private static final String PASSWORD_ALLOW_BASE_SHUFFLE = shuffleString(PASSWORD_ALLOW_BASE);

    private static Random random = new Random();


    public static String generatePassword(String selectedItem, int length, String description) {

        StringBuilder password = new StringBuilder(length);


        if (selectedItem.equals("Только цифры")) {
            for (int i = 0; i < length; i++) {
                password.append(NUMBER.charAt(random.nextInt(NUMBER.length())));
            }
        } else if (selectedItem.equals("Только буквы")) {
            for (int i = 0; i < length; i++) {
                password.append(CHAR_LETTER.charAt(random.nextInt(CHAR_LETTER.length())));
            }
        } else if (selectedItem.equals("Цифры и буквы")) {
            password.append(NUMBER.charAt(random.nextInt(NUMBER.length())));
            password.append(CHAR_LETTER.charAt(random.nextInt(CHAR_LETTER.length())));

            for (int i = 0; i < length - 2; i++) {
                password.append(PASSWORD_LETTER_NUMBER_BASE_SHUFFLE.charAt(random.nextInt(PASSWORD_LETTER_NUMBER_BASE_SHUFFLE.length())));
            }
        } else if (selectedItem.equals("Цифры, буквы и символы")) {
            password.append(NUMBER.charAt(random.nextInt(NUMBER.length())));
            password.append(CHAR_LETTER.charAt(random.nextInt(CHAR_LETTER.length())));
            password.append(OTHER_CHAR.charAt(random.nextInt(OTHER_CHAR.length())));

            for (int i = 0; i < length - 3; i++) {
                password.append(PASSWORD_ALLOW_BASE_SHUFFLE.charAt(random.nextInt(PASSWORD_ALLOW_BASE_SHUFFLE.length())));
            }
        }
        savePassword(String.valueOf(password), description);
        return password.toString();
    }


    private static void savePassword(String password, String description) {
        File file = new File("   ");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(password + " ");
            writer.write(" - " + description);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
        }
    }


    public static String shuffleString(String string) {
        String[] arr = string.split("");
        for (int i = 0; i < arr.length; i++) {
            int j = 0;
            if (random != null) {
                j = random.nextInt(arr.length);
            }
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return String.join("", arr);
    }
}
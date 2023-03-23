package ru.gavrilov;

import java.util.Random;

public class SimpleGeneratePassword {

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "123456789";
    private static final String OTHER_CHAR = "!@#$%^&*()_+-=[]?";

    private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;

    private static final String PASSWORD_ALLOW_BASE_SHUFFLE = shuffleString(PASSWORD_ALLOW_BASE);

    private static Random random = new Random();



    //public static void main(String[] args) {
      //  int length = 15;
        //System.out.println(generatePassword(length));
   // }

    public static String generatePassword(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("Длина не может быть менее 1-го");
        }
        StringBuilder password = new StringBuilder(length);

        password.append(CHAR_LOWER.charAt(random.nextInt(CHAR_LOWER.length())));
        password.append(CHAR_UPPER.charAt(random.nextInt(CHAR_UPPER.length())));
        password.append(NUMBER.charAt(random.nextInt(NUMBER.length())));
        password.append(OTHER_CHAR.charAt(random.nextInt(OTHER_CHAR.length())));

        for (int i = 0; i < length - 4; i++) {
            password.append(PASSWORD_ALLOW_BASE_SHUFFLE.charAt(random.nextInt(PASSWORD_ALLOW_BASE_SHUFFLE.length())));
        }
        return password.toString();
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
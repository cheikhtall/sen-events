package sn.dev.ct.core.utils;

import java.util.Random;

public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0 || str.trim().isEmpty();
    }

    public static String generateRandomString(int length) {
        String availableValues = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ@#$*";
        Random random = new Random();
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < length; i++) {
            value.append(availableValues.charAt(random.nextInt(availableValues.length())));
        }
        return value.toString();
    }
}

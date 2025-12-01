package com.example;

public class RutUtils {

    /**
     * Clean RUT string: remove dots, spaces and convert to upper case.
     */
    public static String clean(String rut) {
        if (rut == null) return null;
        return rut.replace(".", "").replace(" ", "").toUpperCase();
    }

    /**
     * Validate a Chilean RUT. Accepts formats like "12345678-5" or "12.345.678-5" or "123456785".
     */
    public static boolean validateRut(String rut) {
        if (rut == null) return false;
        String cleaned = clean(rut);
        // allow forms with or without dash
        String numberPart;
        char dvChar;
        if (cleaned.contains("-")) {
            String[] parts = cleaned.split("-");
            if (parts.length != 2) return false;
            numberPart = parts[0];
            dvChar = parts[1].charAt(0);
        } else {
            // assume last char is DV
            if (cleaned.length() < 2) return false;
            numberPart = cleaned.substring(0, cleaned.length()-1);
            dvChar = cleaned.charAt(cleaned.length()-1);
        }
        if (!numberPart.matches("\\d+")) return false;
        int number = Integer.parseInt(numberPart);
        char expected = computeDvChar(number);
        return expected == Character.toUpperCase(dvChar);
    }

    /**
     * Compute check digit character for a number part of RUT.
     */
    public static char computeDvChar(int number) {
        int m = 0, s = 1;
        int t = number;
        while (t != 0) {
            s = (s + (t % 10) * (9 - (m % 6))) % 11;
            t = t / 10;
            m++;
        }
        if (s == 0) return 'K';
        int r = s + 47; // maps to ascii digit or 'K'
        return (char) r;
    }
}
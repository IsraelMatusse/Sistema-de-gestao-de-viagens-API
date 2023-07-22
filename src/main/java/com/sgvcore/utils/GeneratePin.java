package com.sgvcore.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class GeneratePin {

    public static int generatePin() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        int pin = sr.nextInt(9000000) + 1000000;
        return pin;
    }

    public static String generateStringPin() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        int pin = sr.nextInt(9000000) + 1000000;
        return String.valueOf(pin);
    }
}

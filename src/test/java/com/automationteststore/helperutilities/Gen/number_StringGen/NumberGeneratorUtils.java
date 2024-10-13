package com.automationteststore.helperutilities.Gen.number_StringGen;

import java.util.Random;
import java.util.function.Supplier;

public class NumberGeneratorUtils {

    public static String generateNumberWithLength(int numberOfChars) {
        Random random = new Random();
        String number = "";
        for (int i = 0; i < numberOfChars; i++) {
            number += Integer.toString(random.nextInt(9));
        }
        return number;
    }

    public static Supplier<Integer> generateNumbers(int numberOfChars) {
        return () -> {
            Random random = new Random();
            return random.nextInt(numberOfChars);
        };
    }

}
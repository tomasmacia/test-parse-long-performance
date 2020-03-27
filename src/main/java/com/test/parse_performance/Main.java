package com.test.parse_performance;

import com.google.common.base.Strings;
import com.google.common.primitives.Longs;

import java.util.Arrays;
import java.util.Random;

public class Main {
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static Random random = new Random(42);

    public static Long getSafeLong(String input) {
        Long aLong = null;

        try {
            aLong = Long.parseLong(input);
        } catch (NumberFormatException var3) {
        }

        return aLong;
    }

    public static void resetRandom() {
        random = new Random(42);
    }


    public static Long method1(String input) {
        return Strings.isNullOrEmpty(input)? null : Longs.tryParse(input);
    }

    public static Long method2(String input) {
        return getSafeLong(input);
    }

    public static double randomGenerator() {
        return random.nextDouble();
    }

    public static String randomAlphaNumeric() {
        StringBuilder builder = new StringBuilder();
        int count = 1;

        while (count-- != 0) {
            int character = (int)(randomGenerator()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        for (int n : Arrays.asList(1000, 1000000, 100000000)) {
            int i;
            long l;

            resetRandom();

            System.out.println("Testing " + n + " iterations:");

            l = System.currentTimeMillis();
            for (i = 1; i < n; i++) {
                String input = randomAlphaNumeric();
                method1(input);
            }

            l = System.currentTimeMillis() - l;
            System.out.println("Longs.tryParse took " + l + " ms");

            resetRandom();

            l = System.currentTimeMillis();
            for (i = 1; i < n; i++) {
                String input = randomAlphaNumeric();
                method2(input);
            }
            l = System.currentTimeMillis() - l;
            System.out.println("getSafeLong took " + l + " ms");

            System.out.println("------------------------------");
        }
    }
}

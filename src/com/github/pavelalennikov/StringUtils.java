package com.github.pavelalennikov;

import java.util.regex.*;


public class StringUtils {

    private StringUtils() {

    }

    public static String summ(String firstString, String secondString) {
        final String notValidInputErrorMessage = "arg is not valid. Only ^0$|^[1-9][0-9]*$ string format allowed";
        if (firstString == null || secondString == null) {
            throw new NullPointerException("Null input string error");
        }
        if (!isValid(firstString)) {
            throw new IllegalArgumentException("First " + notValidInputErrorMessage);
        }
        if (!isValid(secondString)) {
            throw new IllegalArgumentException("Second " + notValidInputErrorMessage);
        }
        if (firstString.equals("0")) {
            return secondString;
        }
        if (secondString.equals("0")) {
            return firstString;
        }

        byte maxLength = (byte) Integer.max(firstString.length(), secondString.length());

        if (firstString.length() != maxLength) {
            String tmpString = firstString;
            firstString = secondString;
            secondString = tmpString;
        }

        byte[] result = new byte[add(maxLength, (byte) 1)];
        byte[] firstStringBytes = reverse(firstString.getBytes());
        byte[] secondStringBytes = reverse(secondString.getBytes());

        for (byte i = 0; i < maxLength; i = add(i, (byte) 1)) {
            if (i < secondStringBytes.length) {
                addTillSecondStringEnds(i, maxLength, firstStringBytes, secondStringBytes, result);
            } else {
                result[i] = add(result[i], (byte) 48);
                addAfterSecondStringEnds(i, maxLength, firstStringBytes, result);
            }
        }
        return new String(reverse(result)).trim();
    }

    private static boolean isValid(String numericalString) {
        Pattern validStringPattern = Pattern.compile("^0$|^[1-9][0-9]*$");
        Matcher matcher = validStringPattern.matcher(numericalString);
        return matcher.find();
    }

    private static void addTillSecondStringEnds(byte i, byte maxLength, byte[] firstStringBytes,
                                                byte[] secondStringBytes, byte[] result) {
        byte digitSum = add(add(firstStringBytes[i], secondStringBytes[i]), (byte) -48);

        if (digitSum < 57) {
            result[i] = add(result[i], digitSum);
        } else {
            if (add(i, (byte) 1) == maxLength) {
                result[add(i, (byte) 1)] = 49;
            } else {
                result[add(i, (byte) 1)] = add(result[add(i, (byte) 1)], (byte) 1);
            }
            result[i] = add(result[i], add(digitSum, (byte) -10));
        }
    }

    private static void addAfterSecondStringEnds(byte i, byte maxLength, byte[] firstStringBytes, byte[] result) {
        byte sum = add(add(firstStringBytes[i], result[i]), (byte) -48);

        if (sum < 57) {
            result[i] = sum;
        } else {
            if (add(i, (byte) 1) == maxLength) {
                result[add(i, (byte) 1)] = 49;
            } else {
                result[add(i, (byte) 1)] = add(result[add(i, (byte) 1)], (byte) 1);
            }
            result[i] = add(sum, (byte) -10);
        }
    }

    private static byte[] reverse(byte[] array) {
        for (byte i = 0; i < array.length >> 1; i = add(i, (byte) 1)) {
            byte temp = array[i];
            array[i] = array[add(add((byte) array.length, (byte) -i), (byte) -1)];
            array[add(add((byte) array.length, (byte) -i), (byte) -1)] = temp;
        }
        return array;
    }

    private static byte add(byte firstByte, byte secondByte) {
        if (secondByte == 0) return firstByte;
        if (firstByte == 0) return secondByte;
        byte sum = (byte) (firstByte ^ secondByte);
        byte carry = (byte) ((firstByte & secondByte) << 1);
        return add(sum, carry);
    }

    public static void getStringDigitsInfo() {
        for (byte i = 0; i < 10; i = add(i, (byte) 1)) {
            byte code = Integer.toString(i).getBytes()[0];
            System.out.println("digit = " + i + " code = " + code + " binaryCode = " + Integer.toBinaryString(code));
        }
    }
}

package com.github.pavelalennikov;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtilsNew {

    private StringUtilsNew() {

    }

    private static final List<String> DIGITS =
            new LinkedList<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));

    public static String sumNumbers(String firstNumber, String secondNumber) {
        if (firstNumber.length() < secondNumber.length()) {
            String cache = firstNumber;
            firstNumber = secondNumber;
            secondNumber = cache;
        }

        validate(firstNumber, secondNumber);

        return sum(firstNumber, secondNumber);

    }

    public static String sumNumbersByOne(String firstNumber, String secondNumber) {
        if (firstNumber.length() < secondNumber.length()) {
            String cache = firstNumber;
            firstNumber = secondNumber;
            secondNumber = cache;
        }

        validate(firstNumber, secondNumber);

        return sumByOne(firstNumber, secondNumber);
    }

    private static String sum(String firstNumber, String secondNumber) {
        if (secondNumber.equals("")) {
            return firstNumber;
        }

        if (firstNumber.equals("0")) {
            return secondNumber;
        }
        if (secondNumber.equals("0")) {
            return firstNumber;
        }

        String firstNumberUnits = getLastCharAsString(firstNumber);
        String firstNumberDecades = trimLastChar(firstNumber);

        String secondNumberUnits = getLastCharAsString(secondNumber);
        String secondNumberDecades = trimLastChar(secondNumber);

        String digitsSum = sumDigits(firstNumberUnits, secondNumberUnits);

        if (DIGITS.indexOf(digitsSum) < DIGITS.indexOf(firstNumberUnits)) {
            firstNumberDecades = plusOne(firstNumberDecades);
        }
        return sum(firstNumberDecades, secondNumberDecades) + digitsSum;
    }

    private static String sumByOne(String firstNumber, String secondNumber) {
        if (firstNumber.equals("0")) {
            return secondNumber;
        }
        if (secondNumber.equals("0")) {
            return firstNumber;
        }

        String result = firstNumber;

        while (!secondNumber.equals("")) {
            secondNumber = subOne(secondNumber);
            result = plusOne(result);
        }

        return result;
    }


    private static String next(String current) {
        ListIterator<String> stringListIterator = DIGITS.listIterator(DIGITS.indexOf(current));
        stringListIterator.next();
        if (stringListIterator.hasNext()) {
            return stringListIterator.next();
        } else {
            return DIGITS.get(0);
        }
    }

    private static String prev(String current) {
        ListIterator<String> stringListIterator = DIGITS.listIterator(DIGITS.indexOf(current));
        if (stringListIterator.hasPrevious()) {
            return stringListIterator.previous();
        } else {
            return DIGITS.get(DIGITS.size() - 1);
        }
    }

    private static String sumDigits(String firstDigit, String secondDigit) {
        Iterator<String> iterator = DIGITS.iterator();

        if (DIGITS.indexOf(firstDigit) > DIGITS.indexOf(secondDigit)) {
            String cache = firstDigit;
            firstDigit = secondDigit;
            secondDigit = cache;
        }

        while (iterator.hasNext() && !iterator.next().equals(firstDigit)) {
            secondDigit = next(secondDigit);
        }

        return secondDigit;
    }

    private static String plusOne(String number) {
        if (number.equals("")) {
            return "1";
        }

        String currentDigit = String.valueOf(number.charAt(number.length() - 1));
        String newDigit = next(currentDigit);
        String join = String.join("", trimLastChar(number));

        if (newDigit.equals("0")) {
            return plusOne(join) + newDigit;
        } else {
            return join + newDigit;
        }
    }

    private static String subOne(String number) {
        if (number.equals("1")) {
            return "";
        }
        String currentDigit = getLastCharAsString(number);
        String newDigit = prev(currentDigit);
        String join = String.join("", trimLastChar(number));
        if (newDigit.equals("9")) {
            return subOne(join) + newDigit;
        } else {
            return join + newDigit;
        }
    }

    private static String getLastCharAsString(String input) {
        return String.valueOf(input.charAt(input.length() - 1));
    }

    private static String trimLastChar(String input) {
        return input.substring(0, input.length() - 1);
    }

    private static boolean isNumber(String numericalString) {
        Pattern validStringPattern = Pattern.compile("^0$|^[1-9][0-9]*$");
        Matcher matcher = validStringPattern.matcher(numericalString);
        return matcher.find();
    }

    private static void validate(String firstNumber, String secondNumber) {
        final String notValidInputErrorMessage = " is not valid. Only ^0$|^[1-9][0-9]*$ string format allowed";
        if (firstNumber == null || secondNumber == null) {
            throw new NullPointerException("Null input string error");
        }
        if (!isNumber(firstNumber)) {
            throw new IllegalArgumentException("First arg=" + firstNumber + notValidInputErrorMessage);
        }
        if (!isNumber(secondNumber)) {
            throw new IllegalArgumentException("Second arg=" + secondNumber + notValidInputErrorMessage);
        }
    }
}

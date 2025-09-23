package Task1;

/*
Завдання № 1.
Перетворення заданого цілого числа у двійкову систему.
 */

import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {
        final int number = 47;
        final String binaryNumber = convertToBinary(number);
        displayResult(number, binaryNumber);
    }

    private static String convertToBinary(final int number) {
        return Integer.toBinaryString(number);
    }

    private static void displayResult(final int number,  final String result) {
        System.out.println(number + " in binary: " + result);
    }
}

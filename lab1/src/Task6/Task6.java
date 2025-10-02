package Task6;

/*
Завдання № 6.
    Створити конвертер валют для: гривня, американський долар, канадський
долар, євро. Введення даних у програму відбувається у вигляді “100 UAH
into USD”, або в іншому зручному форматі.
 */

import java.util.Scanner;

public class Task6 {

    public static void main(String[] args) {
        final String input = readInput();
        final ConvertCurrency converter = new ConvertCurrency();

        try {
            final double result = converter.convert(input);
            System.out.printf("Result: %.2f%n", result);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static String readInput() {
        System.out.print("Enter conversion (for example: 100 UAH into USD): ");
        return new Scanner(System.in).nextLine();
    }
}
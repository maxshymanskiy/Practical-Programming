package Task6;

import java.util.Scanner;

public class Task6 {

    public static void main(String[] args) {
        final String input = readInput();
        final ConvertCurrency converter = new ConvertCurrency();

        try {
            final double result = converter.convert(input);
            System.out.printf("Результат: %.2f%n", result);
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static String readInput() {
        System.out.print("Введіть конвертацію (наприклад: 100 UAH into USD): ");
        return new Scanner(System.in).nextLine();
    }
}
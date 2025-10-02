package Task2;

/*
Завдання № 2.
    Написати простий парсер виразів. Програма повинна вирішувати прості
вирази типу “2 + 4 = ?”, “2 + 4 = ”, “2 + 4”. Повинен підтримувати основні
операції (додавання, віднімання, множення, ділення). Необхідно обробити
виключні ситуації.
 */

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        final String input = readInput();
        try {
            final double result = calculate(input);
            printResult(result);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static double calculate(String input) {
        String[] parts = getStrings(input);

        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid expression format");
        }

        final double a = parseNumber(parts[0]);
        final String operator = parts[1];
        final double b = parseNumber(parts[2]);

        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
        };
    }

    private static String[] getStrings(String input) {
        input = input.trim();

        if (input.contains("=")) {
            int equalsIndex = input.indexOf("=");
            String expression = input.substring(0, equalsIndex);
            String afterEquals = input.substring(equalsIndex + 1);

            if (!afterEquals.isEmpty() && !afterEquals.equals("?")) {
                throw new IllegalArgumentException("After '=' only '?' or nothing is allowed");
            }

            input = expression;
        }

        char[] operators = {'+', '-', '*', '/'};

        String operator = null;
        int operatorIndex = -1;

        for (int i = 1; i < input.length(); i++) {
            char c = input.charAt(i);
            for (char op : operators) {
                if (c == op) {
                    operator = String.valueOf(c);
                    operatorIndex = i;
                    break;
                }
            }
        }

        if (operator == null) {
            throw new IllegalArgumentException("Operator not found");
        }

        String firstNumber = input.substring(0, operatorIndex);
        String secondNumber = input.substring(operatorIndex + 1);

        if (firstNumber.isEmpty() || secondNumber.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression format");
        }

        return new String[]{firstNumber, operator, secondNumber};
    }

    private static double parseNumber(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format: " + str);
        }
    }

    private static String readInput() {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter expression: ");
        final String input = scanner.nextLine();
        scanner.close();
        return input;
    }

    private static void printResult(double result) {
        System.out.println(result);
    }
}


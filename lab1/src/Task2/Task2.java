package Task2;

import java.util.Scanner;

/*
Завдання № 2.
Написати простий парсер виразів. Програма повинна вирішувати прості
вирази типу “2 + 4 = ?”, “2 + 4 = ”, “2 + 4”. Повинен підтримувати основні
операції (додавання, віднімання, множення, ділення). Необхідно обробити
виключні ситуації.
*/

public class Task2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть вираз: ");
        String input = scanner.nextLine().replaceAll("\\s", "");

        try {
            int operatorIndex = -1;
            char operator = '\0';

            for (char op : new char[]{'+', '-', '*', '/'}) {
                operatorIndex = input.indexOf(op);
                if (operatorIndex != -1) {
                    operator = op;
                    break;
                }
            }

            double result = getResult(operatorIndex, input, operator);
            System.out.println("Результат: " + result);

        } catch (NumberFormatException e) {
            System.out.println("Помилка: невірний формат числа");
        } catch (IllegalArgumentException | ArithmeticException e) {
            System.out.println("Помилка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    //TODO Change and understand how it works
    private static double getResult(int operatorIndex, String input, char operator) {
        if (operatorIndex == -1) {
            throw new IllegalArgumentException("Відсутній оператор");
        }

        double a = Double.parseDouble(input.substring(0, operatorIndex));
        double b = Double.parseDouble(input.substring(operatorIndex + 1).replaceAll("[=].*", ""));

        double result = switch (operator) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) throw new ArithmeticException("Ділення на нуль");
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Невідомий оператор");
        };
        return result;
    }
}

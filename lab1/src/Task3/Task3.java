package Task3;

/*
Завдання № 3. FizzBuzz.
    Вивести в консоль числа від 1 до 100. Замість чисел кратних 3 виводити
Fizz, замість чисел кратних 5 вивести Buzz. Замість чисел що кратні і 3, і 5
вивести FizzBuzz.
 */

public class Task3 {

    public static void main(String[] args) {
        final int start = 1;
        final int end = 100;

        for (int i = start; i <= end; i++) {
            final String result = findFizzBuzz(i);
            printResult(result);
        }
    }

    private static String findFizzBuzz(final int number) {
        final boolean isFizz = number % 3 == 0;
        final boolean isBuzz = number % 5 == 0;

        if (isFizz && isBuzz) {
            return "FizzBuzz";
        } else if (isFizz) {
            return "Fizz";
        } else if (isBuzz) {
            return "Buzz";
        } else {
            return String.valueOf(number);
        }
    }

    private static void printResult(final String result) {
        System.out.println(result);
    }
}
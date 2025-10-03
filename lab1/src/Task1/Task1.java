package Task1;

/*
Завдання № 1.
    Перетворення заданого цілого числа у двійкову систему.
 */

public class Task1 {

    public static void main(String[] args) {
        final int number = 41;
        final String binaryNumber = convertToBinary(number);
        displayResult(number, binaryNumber);
    }

    private static String convertToBinary(int n) {
        if (n == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % 2);
            n /= 2;
        }
        return sb.reverse().toString();
    }

    private static void displayResult(final int number, final String result) {
        System.out.println(number + " in binary: " + result);
    }
}

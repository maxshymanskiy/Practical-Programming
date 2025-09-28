package Task1;

/*
Завдання № 1.
    Перетворення заданого цілого числа у двійкову систему.
 */

public class Task1 {

    public static void main(String[] args) {
        final int number = 37;
        final String binaryNumber = convertToBinary(number);
        displayResult(number, binaryNumber);
    }

    private static String convertToBinary(int n) {
        String s = "";

        while (n > 0) {
            s = ((n % 2 ) == 0 ? "0" : "1") + s;
            n /= 2;
        }

        return s;
    }

    private static void displayResult(final int number,  final String result) {
        System.out.println(number + " in binary: " + result);
    }
}

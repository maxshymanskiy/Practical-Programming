package Task1;

/*
Завдання № 1.
    Перетворення заданого цілого числа у двійкову систему.
 */

public class Task1 {

    public static void main(String[] args) {
        final int number = 77;
        final String binaryNumber = convertToBinary(number);
        displayResult(number, binaryNumber);
    }

    private static String convertToBinary(int n) {
        StringBuilder sb = new StringBuilder(); // SonarQube suggests using StringBuilder for better performance
        if (n == 0) {
            return "0";
        }
        while (n > 0) {
            sb.insert(0, (n % 2));
            n /= 2;
        }
        return sb.toString();
    }

    private static void displayResult(final int number, final String result) {
        System.out.println(number + " in binary: " + result);
    }
}

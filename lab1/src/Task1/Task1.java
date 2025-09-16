package Task1;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {

        System.out.print("Enter number: ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        String binaryNumber = Integer.toBinaryString(number);
        System.out.println(binaryNumber);
    }
}

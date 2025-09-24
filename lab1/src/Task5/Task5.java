package Task5;

/*
Завдання № 5.
Знайти кількість входжень заданого підрядка (substring) у двовимірну
матрицю типу String*String.
*/

public class Task5 {

    public static void main(String[] args) {
        final String[][] matrix = {
                {"abc", "def", "ghi"},
                {"abc", "abc", "abc"},
                {"aaabc", "aabc", "abc"}
        };

        final String substring = "abc";
        final int count = countSubstringInMatrix(matrix, substring);
        printResult(substring, count);

    }

    public static int countSubstringInMatrix(final String[][] matrix, final String substring) {
        int total = 0;
        for (String[] row : matrix) {
            for (String cell : row) {
                if (cell.contains(substring)) {
                    total++;
                }
            }
        }

        return total;
    }

    public static void printResult(final String substring, final int count) {
        System.out.println("Number of occurrences of the substring \""
                + substring + "\" in the matrix: " + count);
    }
}

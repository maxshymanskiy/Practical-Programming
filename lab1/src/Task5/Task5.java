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
        final int count = countSubsringInMatrix(matrix, substring);
        printResult(substring, count);

    }

    public static int countSubsringInMatrix(final String[][] matrix, final String substring) {
        int totalCount = 0;

        for (final String[] row : matrix) {
            for (final String str : row) {
                totalCount += countSubsringInSting(str, substring);
            }
        }
        return totalCount;
    }

    public static int countSubsringInSting(final String str, final String substring) {
        int count = 0;
        int index = 0;

        while((index = str.indexOf(substring, index)) != -1) {
            count++;
            index += substring.length();
        }
        return count;
    }

    public static void printResult(final String substring, final int count) {
        System.out.println("Number of occurrences of the substring \""
                + substring + "\" in the matrix: " + count);
    }
}

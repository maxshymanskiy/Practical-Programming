package Task4;

/*
Завдання № 4.
    Вивести на екран кількість кожного символу у рядку. Виконати завдання
використовуючи масиви та створити клас, що представляє пару значень
(Символ, Кількість)
 */

import java.util.Arrays;

public class Task4 {

    public static void main(String[] args) {
        final String inputString = "Lorem ipsum dolor sit amet.";
        final CharCount[] charCounts = countCharacters(inputString);
        displayResults(charCounts, inputString);
    }

    private static CharCount[] countCharacters(String str) {
        CharCount[] tempResults = new CharCount[str.length()];
        int uniqueCharsCount = 0;

        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            boolean isFound = false;

            // O(n^2)
            for (int j = 0; j < uniqueCharsCount; j++) {
                if (tempResults[j].getCharacter() == currentChar) {
                    tempResults[j].incrementCount();
                    isFound = true;
                    break;
                }
            }

            if (!isFound) {
                tempResults[uniqueCharsCount] = new CharCount(currentChar, 1);
                uniqueCharsCount++;
            }
        }

        return Arrays.copyOf(tempResults, uniqueCharsCount);
    }

    private static void displayResults(CharCount[] results, String originalString) {
        System.out.println("Input string: \"" + originalString + "\"");
        System.out.println("Total number of characters: " + originalString.length());
        System.out.println("\nNumber of each character:");

        for (CharCount charCount : results) {
            System.out.println("  " + charCount);
        }
    }
}

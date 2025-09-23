package Task4;

/*
Завдання № 4.
Вивести на екран кількість кожного символу у рядку. Виконати завдання
використовуючи масиви та створити клас, що представляє пару значень
(Символ, Кількість)
*/

public class Task4 {

    public static void main(String[] args) {
        final String inputString = "Lorem ipsum dolor sit amet.";
        final CharCount[] charCounts = countCharacters(inputString);
        displayResults(charCounts, inputString);
    }

    private static CharCount[] countCharacters(String str) {
        final CharCount[] tempResults = new CharCount[256];
        int uniqueCharsCount = 0;

        //TODO Understand how this loops works
        for (int i = 0; i < str.length(); i++) {
            final char currentChar = str.charAt(i);
            boolean found = false;

            // Шукаємо чи вже є такий символ в масиві
            for (int j = 0; j < uniqueCharsCount; j++) {
                if (tempResults[j].getCharacter() == currentChar) {
                    tempResults[j].incrementCount();
                    found = true;
                    break;
                }
            }

            // Якщо символ новий, додаємо його
            if (!found) {
                tempResults[uniqueCharsCount] = new CharCount(currentChar, 1);
                uniqueCharsCount++;
            }
        }

        // Створюємо масив точного розміру
        final CharCount[] results = new CharCount[uniqueCharsCount];
        System.arraycopy(tempResults, 0, results, 0, uniqueCharsCount); // Better than loop
        return results;
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

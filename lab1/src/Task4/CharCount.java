package Task4;

public class CharCount {
    private final char character;
    private int count;

    public CharCount(char character, int count) {
        this.character = character;
        this.count = count;
    }

    public char getCharacter() {
        return character;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        this.count++;
    }

    @Override
    public String toString() {
        return "'" + character + "' - " + count + " time(s)";
    }
}


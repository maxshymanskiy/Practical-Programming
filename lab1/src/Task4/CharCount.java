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

    public void incrementCount() {
        this.count++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "'" + character + "' - " + getCount() + " time(s)";
    }
}


package Task7;

public class Bank {
    private final String name;

    public Bank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Bank other = (Bank) obj;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
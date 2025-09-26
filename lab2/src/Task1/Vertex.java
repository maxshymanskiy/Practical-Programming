package Task1;

public class Vertex {

    private final String name;
    private final int value;

    public Vertex(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}

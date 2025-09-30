package Task1;

public record Vertex(String name, int value) {

    @Override
    public String toString() {
        return name + "(" + value + ")";
    }
}

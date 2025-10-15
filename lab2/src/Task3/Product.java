package Task3;

public record Product(
        double length,
        double width,
        double height) {

    @Override
    public String toString() {
        return String.format(
                "Product(L: %.1f, W: %.1f, H: %.1f)",
                length, width, height);
    }
}
package Task2;

public class Circle implements TwoDimensionalShape {
    private final double radius;

    public Circle(final double radius) {
        this.radius = radius;
    }

    @Override
    public String getDescription() {
        return "This is a circle with radius " + radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}

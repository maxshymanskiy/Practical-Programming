package Task2;

public class Sphere implements IThreeDimensionalShape {
    private final double radius;

    public Sphere(final double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return 4 * Math.PI * radius * radius;
    }

    @Override
    public double getVolume() {
        return (4.0 / 3.0) * Math.PI * radius * radius * radius;
    }

    @Override
    public String getDescription() {
        return "Sphere with radius " + radius;
    }
}

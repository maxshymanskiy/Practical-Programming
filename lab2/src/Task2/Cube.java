package Task2;

public class Cube implements IThreeDimensionalShape {
    private final double side;

    public Cube(final double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return 6 * side * side;
    }

    @Override
    public double getVolume() {
        return side * side * side;
    }

    @Override
    public String getDescription() {
        return "Cube with side " + side;
    }
}

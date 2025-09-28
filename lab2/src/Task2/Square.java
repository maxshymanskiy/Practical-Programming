package Task2;

public class Square implements TwoDimensionalShape {
    private final double side;

    public Square(final double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public String getDescription() {
        return "Square with side " + side;
    }
}

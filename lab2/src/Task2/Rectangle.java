package Task2;

public class Rectangle implements TwoDimensionalShape {
    private final double height;
    private final double width;

    public Rectangle(final double length, final double width) {
        this.height = length;
        this.width = width;
    }

    @Override
    public double getArea() {
        return height * width;
    }

    @Override
    public String getDescription() {
        return "Rectangle with length " + height + " and width " + width;
    }
}

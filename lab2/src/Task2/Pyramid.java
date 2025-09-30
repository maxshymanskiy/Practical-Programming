package Task2;

public class Pyramid implements IThreeDimensionalShape {
    private final double baseLength;
    private final double baseWidth;
    private final double height;

    public Pyramid(final double baseLength, final double baseWidth, final double height) {
        this.baseLength = baseLength;
        this.baseWidth = baseWidth;
        this.height = height;
    }

    @Override
    public double getArea() {
        double baseArea = baseLength * baseWidth;
        double slantHeightLength = Math.sqrt((baseWidth / 2) * (baseWidth / 2) + height * height);
        double slantHeightWidth = Math.sqrt((baseLength / 2) * (baseLength / 2) + height * height);
        double lateralArea = (baseLength * slantHeightLength + baseWidth * slantHeightWidth);
        return baseArea + lateralArea;
    }

    @Override
    public double getVolume() {
        return (baseLength * baseWidth * height) / 3;
    }

    @Override
    public String getDescription() {
        return "Pyramid with base length " + baseLength + ", base width " + baseWidth + ", and height " + height;
    }
}

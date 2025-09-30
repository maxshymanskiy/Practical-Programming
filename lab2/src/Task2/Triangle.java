package Task2;

public class Triangle implements ITwoDimensionalShape {
    private final double sideA;
    private final double sideB;
    private final double sideC;

    public Triangle(final double sideA, final double sideB, final double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    @Override
    public double getArea() {
        final double s = (sideA + sideB + sideC) / 2;
        return Math.sqrt(s * ((s - sideA) * (s - sideB) * (s - sideC))); // Heron's formula
    }

    @Override
    public String getDescription() {
        return "Triangle with sides: " + sideA + ", " +  sideB + ", " +   sideC;
    }
}

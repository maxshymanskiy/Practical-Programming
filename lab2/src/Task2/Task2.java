package Task2;

/*
Завдання № 2
    Створити ієрархію геометричних фігур. Реалізувати методи розрахунку
площі та об’єму фігур. Фігури, які повинні бути в ієрархії:
трикутник, квадрат, прямокутник, куб, піраміда, коло, сфера.
 */

public class Task2 {

    public static void main(String[] args) {
        final Shape[] shapes = {
                new Circle(5),
                new Triangle(3, 4, 5),
                new Rectangle(4, 6),
                new Square(5),
                new Sphere(3),
                new Cube(4),
                new Pyramid(6, 4, 5)
        };

        for (final Shape shape : shapes) {
            System.out.println(shape.getDescription());

            if (shape instanceof TwoDimensionalShape twoDimensionalShape) {
                System.out.printf("Area: %.2f%n", twoDimensionalShape.getArea());
            } else if (shape instanceof ThreeDimensionalShape threeDimensionalShape) {
                System.out.printf("Surface area: %.2f%n", threeDimensionalShape.getArea());
                System.out.printf("Volume: %.2f%n", threeDimensionalShape.getVolume());
            }
            System.out.println();
        }
    }
}

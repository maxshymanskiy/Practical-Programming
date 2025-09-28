package Task2;

/*
Завдання № 2
    Створити ієрархію геометричних фігур. Реалізувати методи розрахунку
площі та об’єму фігур. Фігури, які повинні бути в ієрархії:
трикутник, квадрат, прямокутник, куб, піраміда, коло, сфера.
 */

public class Task2 {

    public static void main(String[] args) {
        Triangle triangle = new Triangle(1,2,6);
        System.out.println(triangle.getDescription());
    }

}

package Task3;

/*
Завдання No 3. Керування складом товарів.
    Написати програму, що відтворює процес отримання товарів на
склад. Кожен товар має мати відомості про розміри. Якщо один із вимірів
комірки > один із вимірів товару, то викинути помилку. Кожна комірка
може містити 5 одиниць товару, додавання більшої кількості товарів
повинні спричиняти виняткову ситуацію (можна використати звичайний
System.out.println(...)). Написати метод, що виведе у консоль відомості про
усі товари на складі.
 */

public class Task3 {
    public static void main(String[] args) {
        final Warehouse warehouse = new Warehouse();

        warehouse.addCell(new StorageCell(10.0, 10.0, 10.0));
        warehouse.addCell(new StorageCell(5.0, 5.0, 5.0));

        final Product p1 = new Product(8.0, 8.0, 8.0);
        final Product p2 = new Product(3.0, 3.0, 3.0);
        final Product p3 = new Product(12.0, 10.0, 10.0); // Too large

        System.out.println("=== Adding products to warehouse ===");

        warehouse.addProductToCell(0, p1);
        warehouse.addProductToCell(1, p2);

        try {
            warehouse.addProductToCell(0, p3);
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding product: " + e.getMessage());
        }

        System.out.println("\n=== Testing cell capacity ===");
        for (int i = 0; i < 6; i++) {
            final Product smallProduct = new Product(1.0, 1.0, 1.0);
            warehouse.addProductToCell(1, smallProduct);
        }

        warehouse.printAllProducts();

        System.out.println("\nTotal products: " + warehouse.getTotalProducts());
        System.out.println("Total capacity: " + warehouse.getTotalCapacity());
    }
}
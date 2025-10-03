package Task3;

public class Task3 {
    public static void main(String[] args) {
        final Warehouse warehouse = new Warehouse();

        // Create storage cells
        warehouse.addCell(new StorageCell(10.0, 10.0, 10.0));
        warehouse.addCell(new StorageCell(5.0, 5.0, 5.0));

        // Create products
        final Product p1 = new Product(8.0, 8.0, 8.0);
        final Product p2 = new Product(3.0, 3.0, 3.0);
        final Product p3 = new Product(12.0, 10.0, 10.0); // Too large

        System.out.println("=== Adding products to warehouse ===");

        // Add products
        warehouse.addProductToCell(0, p1);
        warehouse.addProductToCell(1, p2);

        // Try to add product that's too large
        try {
            warehouse.addProductToCell(0, p3);
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding product: " + e.getMessage());
        }

        // Test cell capacity
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
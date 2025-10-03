package Task3;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private final List<StorageCell> cells = new ArrayList<>();

    public void addCell(final StorageCell cell) {
        cells.add(cell);
    }

    public void addProductToCell(final int cellIndex, final Product product) {
        if (cellIndex < 0 || cellIndex >= cells.size()) {
            System.out.println("Invalid cell index!");
            return;
        }

        final StorageCell cell = cells.get(cellIndex);

        try {
            cell.addProduct(product);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void printAllProducts() {
        System.out.println("\n=== All products in warehouse ===");

        for (int i = 0; i < cells.size(); i++) {
            final StorageCell cell = cells.get(i);
            System.out.println("Cell " + i + " (" + cell.getCurrentCount() + "/" + cell.getCapacity() + "):");

            final List<Product> products = cell.getProducts();
            if (products.isEmpty()) {
                System.out.println("  Empty");
            } else {
                for (final Product p : products) {
                    System.out.println("  " + p);
                }
            }
        }
    }

    public int findSuitableCell(final Product product) {
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).canAddProduct(product)) {
                return i;
            }
        }
        return -1;
    }

    public int getTotalProducts() {
        int total = 0;
        for (StorageCell cell : cells) {
            total += cell.getCurrentCount();
        }
        return total;
    }

    public int getTotalCapacity() {
        int total = 0;
        for (StorageCell cell : cells) {
            total += cell.getCapacity();
        }
        return total;
    }
}
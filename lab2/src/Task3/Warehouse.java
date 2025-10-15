package Task3;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    private final List<StorageCell> cells = new ArrayList<>();

    public void addCell(final StorageCell cell) {
        cells.add(cell);
    }

    public void addProductToCell(final int cellIndex, final Product product) {
        if (!isValidCellIndex(cellIndex)) {
            System.out.println("Invalid cell index: " + cellIndex + ". Available cells: 0-" + (cells.size() - 1));
            return;
        }

        final StorageCell cell = cells.get(cellIndex);

        try {
            cell.addProduct(product);
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding product to cell " + cellIndex + ": " + e.getMessage());
        }
    }

    public boolean isValidCellIndex(final int cellIndex) {
        return cellIndex >= 0 && cellIndex < cells.size();
    }

    public int getTotalProducts() {
        int total = 0;
        for (final StorageCell cell : cells) {
            total += cell.getCurrentCount();
        }
        return total;
    }

    public int getTotalCapacity() {
        int total = 0;
        for (final StorageCell cell : cells) {
            total += cell.getCapacity();
        }
        return total;
    }

    /* Display methods */
    public void printAllProducts() {
        System.out.println("\n=== All products in warehouse ===");

        if (cells.isEmpty()) {
            System.out.println("Warehouse is empty - no cells available");
            return;
        }

        for (int i = 0; i < cells.size(); i++) {
            printCellContents(i, cells.get(i));
        }

        printWarehouseSummary();
    }

    private void printCellContents(final int cellIndex, final StorageCell cell) {
        System.out.println("Cell " + cellIndex + " (" + cell.getCurrentCount() + "/" + cell.getCapacity() + "):");

        final List<Product> products = cell.getProducts();
        if (products.isEmpty()) {
            System.out.println("  Empty");
        } else {
            for (final Product product : products) {
                System.out.println("  " + product);
            }
        }
    }

    private void printWarehouseSummary() {
        final int totalProducts = getTotalProducts();
        final int totalCapacity = getTotalCapacity();
        System.out.println("\nWarehouse summary: " + getTotalProducts() + "/" + getTotalCapacity() +
                " products (" + (totalCapacity - totalProducts) + " slots available)");
    }
}
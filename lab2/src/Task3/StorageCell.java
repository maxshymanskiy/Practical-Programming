package Task3;

import java.util.ArrayList;
import java.util.List;

public class StorageCell {
    private final double maxLength;
    private final double maxWidth;
    private final double maxHeight;
    private final List<Product> products = new ArrayList<>();
    private static final int CAPACITY = 5;

    public StorageCell(final double maxLength, final double maxWidth, final double maxHeight) {
        this.maxLength = maxLength;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    public boolean canAddProduct(final Product product) {
        if (products.size() >= CAPACITY) {
            return false;
        }

        return product.length() <= maxLength &&
                product.width() <= maxWidth &&
                product.height() <= maxHeight;
    }

    public void addProduct(final Product product) {
        if (products.size() >= CAPACITY) {
            System.out.println("Error: Cell is full! Maximum " + CAPACITY + " products allowed.");
            return;
        }

        if (product.length() > maxLength ||
                product.width() > maxWidth ||
                product.height() > maxHeight) {
            throw new IllegalArgumentException("Product dimensions exceed cell dimensions");
        }

        products.add(product);
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public int getCurrentCount() {
        return products.size();
    }

    public int getCapacity() {
        return CAPACITY;
    }

    public double getMaxLength() { return maxLength; }
    public double getMaxWidth() { return maxWidth; }
    public double getMaxHeight() { return maxHeight; }

    public boolean isFull() {
        return products.size() >= CAPACITY;
    }
}